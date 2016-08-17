package siw.service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import siw.model.Cart;
import siw.model.Event;
import siw.model.Order;
import siw.model.Review;
import siw.model.Sell;
import siw.model.Ticket;
import siw.model.User;
import siw.model.Wishlist;
import siw.persistence.DAOFactory;
import siw.persistence.dao.OrderDAO;
import siw.persistence.dao.ReviewDAO;
import siw.persistence.dao.TicketDAO;
import siw.persistence.dao.UserDAO;
import siw.persistence.dao.WishlistDAO;

/**
 * <pre>
 * What action this service allow:
 *  1. Update User profile
 *  2. Add a new user wishlist
 *  3. Show all the wishlist
 *  4. Show target wishlist events
 *  5. Add an item to the target wishlist
 *  6. Show User orders
 *  7. Show target order items
 * </pre>
 */
public class UserService {

	public UserService() {
	}

	public User updateUser(String json, Integer id, JsonObject result) {
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		UserDAO userdao = postgres.getUserDAO();
		Gson gson = new Gson();
		User user = gson.fromJson(json, User.class);
		user.setId(id);
		System.out.println(user);
		if (userdao.update(user)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Profile updated with success !");
			return user;
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later");
			return null;
		}

	}

	public void addWishlist(String json, User user, JsonObject result) {
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		WishlistDAO wishdao = postgres.getWishlistDAO();
		Gson gson = new Gson();
		Wishlist wishlist = gson.fromJson(json, Wishlist.class);
		wishlist.setUser(user);
		Integer id = wishdao.create(wishlist);
		if (id > 0) {
			wishlist.setId(id);
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "The Wishlist has been added !");
			result.addProperty("idWishlist", id);
			result.addProperty("titleWishlist", wishlist.getTitle());
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later  !");
		}
	}

	public Map<Integer, Wishlist> showWishlist(Integer id, Integer offset, Integer limit) {
		JsonObject object = new JsonObject();
		return showWishlist(id, offset, limit, object);
	}

	public Map<Integer, Wishlist> showWishlist(Integer id, Integer offset, Integer limit, JsonObject result) {
		result = new JsonObject();
		if (offset == null) {
			offset = 0;
		}
		if (limit == null) {
			limit = 5;
		}
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		WishlistDAO wishlistdao = postgres.getWishlistDAO();
		Gson gson = new Gson();
		Map<Integer, Wishlist> wishlists = wishlistdao.findByUser(id, offset, limit);
		if (wishlists != null) {
			Type type = new TypeToken<Map<Integer, Wishlist>>() {
			}.getType();
			JsonElement element = gson.toJsonTree(wishlists, type);
			result = element.getAsJsonObject();
		} else {
			result.addProperty("result", "FAIL");
			if (offset > 0) {
				result.addProperty("reason", "No more Wishlists !");
			} else {
				result.addProperty("reason", "No Wishlists yet !");
			}
		}
		return wishlists;
	}

	public JsonObject s(User id, Integer offset, Integer limit, JsonObject result) {
		if (id == null) {
			result.addProperty("result", "NOUSER");
			result.addProperty("reason", "We are sorry, please login to show your wishlist");
			return result;
		}
		result = new JsonObject();
		if (offset == null) {
			offset = 0;
		}
		if (limit == null) {
			limit = 5;
		}
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		WishlistDAO wishlistdao = postgres.getWishlistDAO();
		Gson gson = new Gson();
		Map<Integer, Wishlist> wishlists = wishlistdao.findByUser(id.getId(), offset, limit);
		if (wishlists != null) {
			Type type = new TypeToken<Map<Integer, Wishlist>>() {
			}.getType();
			JsonElement element = gson.toJsonTree(wishlists, type);
			result = element.getAsJsonObject();
		} else {
			result.addProperty("result", "FAIL");
			if (offset > 0) {
				result.addProperty("reason", "No more Wishlists !");
			} else {
				result.addProperty("reason", "No Wishlists yet !");
			}
		}
		return result;
	}

	public JsonObject showWishlistTickets(String json, JsonObject result) {
		result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		WishlistDAO wishlistdao = postgres.getWishlistDAO();
		Gson gson = new Gson();
		Wishlist source = gson.fromJson(json, Wishlist.class);
		Wishlist tmp = wishlistdao.findById(source.getId());
		if (tmp != null) {
			Type type = new TypeToken<Map<Integer, Ticket>>() {
			}.getType();
			JsonElement element = gson.toJsonTree(tmp.getTickets(), type);
			result = element.getAsJsonObject();
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "We are sorry, no tickets yet, add new one");
		}
		return result;
	}

	public JsonObject addTicketWishlist(String json, JsonObject result) {
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		WishlistDAO wishlistdao = postgres.getWishlistDAO();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject object = element.getAsJsonObject();
		Wishlist wishlist = new Wishlist();
		wishlist.setId(object.get("idwish").getAsInt());
		Ticket ticket = new Ticket();
		ticket.setId(object.get("idticket").getAsInt());
		if (wishlistdao.updateWishTicket(wishlist, ticket)) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "The Ticket has been added to the wishlist!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "We are sorry, you canno't add this ticket to the selected wishlist");
		}
		return result;
	}
	public Map<Integer, Review> showReviews(User user1) {
		return showReviews(user1, new JsonObject());
	}
	public Map<Integer, Review> showReviews(User user, JsonObject result) {
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		ReviewDAO reviewdao= postgres.getReviewDao();
		Gson gson = new Gson();
		Map<Integer, Review> reviews=reviewdao.findByUser(user);
		if (reviews.size() !=0) {
			Type type = new TypeToken<Map<Integer,Review>> (){}.getType();
			JsonElement element = gson.toJsonTree(reviews,type);
			result=element.getAsJsonObject();
		}else{
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "No review yet");
		}
		return reviews;

	}

	public Map<Integer, Order> showOrders(Integer id, Integer offset, Integer limit) {
		return showOrders(id, offset, limit, new JsonObject());
	}

	public Map<Integer, Order> showOrders(Integer id, Integer offset, Integer limit, JsonObject result) {
		if (offset == null) {
			offset = 0;
		}
		if (limit == null) {
			limit = 5;
		}
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		OrderDAO orderdao = postgres.getOrderDAO();
		Gson gson = new Gson();
		Map<Integer, Order> orders = orderdao.findByUser(id, offset, limit);
		if (orders != null) {
			Type type = new TypeToken<Map<Integer, Order>>() {
			}.getType();
			JsonElement element = gson.toJsonTree(orders, type);
			result = element.getAsJsonObject();
		} else {
			result.addProperty("result", "FAIL");
			if (offset > 0) {
				result.addProperty("reason", "No more Orders !");
			} else {
				result.addProperty("reason", "No Orders yet !");
			}
		}
		return orders;
	}
	

	public JsonObject showOrderItems(String json, JsonObject result) {
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		OrderDAO orderdao = postgres.getOrderDAO();
		Gson gson = new Gson();
		Order source = gson.fromJson(json, Order.class);
		Order tmp = orderdao.findById(source.getId());
		if (tmp != null) {
			Type type = new TypeToken<Map<Integer, Sell>>() {
			}.getType();
			JsonElement element = gson.toJsonTree(tmp.getSells(), type);
			result = element.getAsJsonObject();
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happend, we are sorry");
		}
		return result;
	}

	



}
