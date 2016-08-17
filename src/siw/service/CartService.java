package siw.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import siw.model.Cart;
import siw.model.Order;
import siw.model.Sell;
import siw.model.Ticket;
import siw.model.User;
import siw.persistence.DAOFactory;
import siw.persistence.dao.OrderDAO;
import siw.persistence.dao.SellDAO;
import siw.persistence.dao.TicketDAO;

public class CartService {


	public CartService() {

	}

	public Cart addItem(String json, Cart cart, JsonObject result) {
		if (json == null) {
			return null;
		}
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		TicketDAO ticketdao = postgres.getTicketDao();
		Gson gson = new Gson();
		Ticket item = gson.fromJson(json, Ticket.class);
		if (cart.getTickets().containsKey(item.getId())) {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, you have already this ticket in your cart !");
			return cart;
		}
		Ticket ticket = ticketdao.findById(item.getId());
		if (ticket != null) {
			cart.sumTotal(ticket.getPrice());
			cart.getTickets().put(ticket.getId(), ticket);
			result.addProperty("result", "SUCCESS");
			result.addProperty("message",
					"Successfully added " + ticket.getEvent().getName() + " ticket to the cart !");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something went wrong !");
		}
		return cart;
	}

	public void removeItem(String json, Cart cart, JsonObject result) {
		Gson gson = new Gson();
		Ticket item = gson.fromJson(json, Ticket.class);
		float price = cart.getTickets().get(item.getId()).getPrice();
		if (cart.getTickets().remove(item.getId()) != null) {
			cart.decrementTotal(price);
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Successfully removed ticket from the cart !");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something went wrong !");
		}
	}

	public void buy(Cart cart, JsonObject result, User user, String json) {
		if (user == null) {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "We are sorry, please login or register to buy your tickets!");
			return;
		}
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject object = element.getAsJsonObject();
		Type listType = new TypeToken<ArrayList<String>>() {
		}.getType();
		ArrayList<String> quantity = new Gson().fromJson(object.get("quantity"), listType);
		float total = object.get("total").getAsFloat();
		float tmptotall = 0;


		if (!cart.getTickets().isEmpty()) {
			DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
			OrderDAO orderdao = postgres.getOrderDAO();
			SellDAO selldao = postgres.getSellDAO();

			TicketDAO ticketDao = postgres.getTicketDao();
			
				Order order = new Order();
				order.setUser(user);
				order.setTotal(total);
				long time = new java.util.Date().getTime();
				order.setDate(new java.util.Date(time));
				int idorder = orderdao.create(order);
				order.setId(idorder);
			
			int count = 0;
			ArrayList<Integer> delete = new ArrayList<>();
			for (Ticket entry : cart.getTickets().values()) {
				int numbreticket = Integer.parseInt(quantity.get(count));
				Map<Integer, Ticket> sellT = ticketDao.findByEventAndCategory(entry.getEvent(), entry.getCategory(),
						numbreticket);
				if (sellT.size() < numbreticket) {
					String name = entry.getEvent().getName();
					String category = entry.getCategory().getName();
					if (!delete.isEmpty()) {
						for (int i = 0; i < delete.size(); i++) {
							cart.decrementTotal(cart.getTickets().get(delete.get(i)).getPrice());
							cart.getTickets().remove(delete.get(i));

						}
					}
					order.setTotal(tmptotall);
					orderdao.update(order);
					result.addProperty("result", "FAIL");
					result.addProperty("reason",
							"There aren't any tickets for the event " + name + " with category " + category + "!");
					return;

				}
				for (Ticket tickets : sellT.values()) {
					Sell sel = new Sell();
					long timeS = new java.util.Date().getTime();
					sel.setDate(new java.util.Date(timeS));
					sel.setOrder(order);
					sel.setPrice(tickets.getPrice());
					sel.setSeller(user);
					sel.setTicket(tickets);
					selldao.create(sel);
					delete.add(entry.getId());
					tmptotall += tickets.getPrice();

				}
				count++;

			}
			
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", " Congratulations, your order has successfully been registered !");
			cart.getTickets().clear();
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "something were wrong !");
		}

	}

	public void clear(Cart cart, JsonObject result) {
		if (!cart.getTickets().isEmpty()) {
			cart.getTickets().clear();
			cart.setTotal(0.0F);
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", " Cart cleared !");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", " Your cart is empty !");
		}
	}
}
