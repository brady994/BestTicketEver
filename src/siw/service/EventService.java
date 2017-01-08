package siw.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import siw.model.AnchestorEventCategory;
import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Guest;
import siw.model.Organizer;
import siw.model.Review;
import siw.model.Ticket;
import siw.model.TicketCategory;
import siw.model.User;
import siw.model.Wishlist;
import siw.persistence.DAOFactory;
import siw.persistence.dao.EventCategoryDAO;
import siw.persistence.dao.EventDAO;
import siw.persistence.dao.GuestDAO;
import siw.persistence.dao.OrganizerDAO;
import siw.persistence.dao.ReviewDAO;
import siw.persistence.dao.TicketCategoryDAO;
import siw.persistence.dao.TicketDAO;
import siw.persistence.dao.UserDAO;
import siw.persistence.dao.implementation.TicketDAOJDBC;

public class EventService {
	Gson gson;
	String json;
	Event event;

	public EventService(String json) {
		this.json = json;
		this.gson = new GsonBuilder().setDateFormat("dd/MM/aaaa HH:mm").create();
		event = gson.fromJson(json, Event.class);
	}

	public EventService() {
	}

	public Map<Integer, AnchestorEventCategory> showAllCategory()
	{
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		Map<Integer, AnchestorEventCategory> category =eventDAO.showCategory();
		return category;
	}
	public JsonObject createEvent(Organizer organizer, String json) {
		JsonObject result = new JsonObject();
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		GuestDAO guestDAO = factory.getGuestDAO();
		TicketDAO ticketdao = factory.getTicketDao();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject object = element.getAsJsonObject();
		Type nameGuestType = new TypeToken<ArrayList<String>>() {
		}.getType();
		Type photoGuestType = new TypeToken<ArrayList<String>>() {
		}.getType();

		EventCategory child = new EventCategory();
		child = gson.fromJson(json, EventCategory.class);
		event.setOrganizer(organizer);
		event.setCategory(child);
		Integer id = eventDAO.create(event);
		Integer idguest = 0;
		event.setId(id);

		if (object.get("nameguest").isJsonArray()) {
			ArrayList<String> nameGuest = gson.fromJson(object.get("nameguest"), nameGuestType);
			ArrayList<String> photoGuest = gson.fromJson(object.get("photo"), photoGuestType);
			for (int i = 0; i < nameGuest.size(); i++) { // insert guest and
															// into event
				Guest modelObject = new Guest();
				modelObject.setName(nameGuest.get(i));
				modelObject.setImage(photoGuest.get(i));
				idguest = guestDAO.create(modelObject);
				modelObject.setId(idguest);
				guestDAO.insertIntoEvent(modelObject, event);

			}
		} else {
			Guest guest = new Guest();
			guest = gson.fromJson(json, Guest.class);
			idguest = guestDAO.create(guest);
			guest.setId(idguest);
			guestDAO.insertIntoEvent(guest, event);

		}
		if (!object.get("quantity").isJsonArray()) {
			Integer quantity = gson.fromJson(object.get("quantity"), Integer.class);
			Float price = gson.fromJson(object.get("price"), Float.class);
			Integer idticketc = gson.fromJson(object.get("idcategoryt"), Integer.class);
			TicketCategory ct = new TicketCategory();
			ct.setId(idticketc);
			if (quantity != 0) {
				for (int i = 0; i < quantity; i++) {
					Ticket ticket = new Ticket();
					ticket.setEvent(event);
					ticket.setPrice(price);
					ticket.setCategory(ct);
					ticketdao.create(ticket);

				}
			}
		} else {
			Type quantityType = new TypeToken<ArrayList<Integer>>() {
			}.getType();
			Type categorytType = new TypeToken<ArrayList<Integer>>() {
			}.getType();
			Type priceType = new TypeToken<ArrayList<Float>>() {
			}.getType();
			ArrayList<Integer> quantity = gson.fromJson(object.get("quantity"), quantityType);
			ArrayList<Float> price = gson.fromJson(object.get("price"), priceType);
			ArrayList<Integer> idticketc = gson.fromJson(object.get("idcategoryt"), categorytType);
			for (int i = 0; i < quantity.size(); i++) {
				TicketCategory ct = new TicketCategory();
				ct.setId(idticketc.get(i));
				for (int j = 0; j < quantity.get(i); j++) {
					Ticket ticket = new Ticket();
					ticket.setEvent(event);
					ticket.setPrice(price.get(i));
					ticket.setCategory(ct);
					ticketdao.create(ticket);

				}

			}

		}

		if (id != null && idguest != null) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Event has been addes with success");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "We are sorry, something went wrong, try again");
		}
		return result;
	}

	public JsonObject updateEvent() {
		JsonObject result = new JsonObject();
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		Gson gson = new Gson();
		EventCategory category = gson.fromJson(json, EventCategory.class);
		event.setCategory(category);
		if (eventDAO.update(event) > 0) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Update event with success!");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later  !");
		}

		return result;
	}

	public Map<Integer, Event> showEventOrganizer(Organizer organizer) {

		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		OrganizerDAO organizerDAO = factory.getOrganizerDao();
		return organizerDAO.getEvents(organizer);

	}

	public Event showSingleEvent(Event event) {
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		ReviewDAO reviewdao = factory.getReviewDao();
		int feedback = reviewdao.getAVGbyEvent(event.getId());
		event = eventDAO.findById(event.getId());
		event.setAvgFeedback(feedback);
		return event;
	}

	public Map<String, ArrayList<Ticket>> getTicketsEvent(Event e) {
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		TicketDAO ticketDao = factory.getTicketDao();
		Map<String, ArrayList<Ticket>> tickets = new HashMap<String, ArrayList<Ticket>>();
		tickets = ticketDao.findByEvent(e);

		for (String entry : tickets.keySet()) {
			TicketCategory category = new TicketCategory();
			category.setName(entry);
			category.setId(tickets.get(entry).get(0).getCategory().getId());
			Map<Integer, Ticket> sellT = ticketDao.findByEventAndCategory(e, category, 0);
			tickets.get(entry).get(0).setRemainTicket(sellT.size());
		}
		return tickets;

	}

	public JsonObject showTicketOrganizer() {
		JsonObject result = new JsonObject();
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		TicketDAO ticketDao = factory.getTicketDao();
		Map<String, Ticket> t = ticketDao.findByEvent1(event);
		for (String entry : t.keySet()) {
			TicketCategory category = new TicketCategory();
			category.setName(entry);
			category.setId(t.get(entry).getCategory().getId());
			Map<Integer, Ticket> sellT = ticketDao.findByEventAndCategory(event, category, 0);
			Integer sold = ticketDao.soldTicket(event, category);
			t.get(entry).setRemainTicket(sellT.size());
			t.get(entry).setSoldTicket(sold);
		}

		if (t.size() > 0) {

			Type type = new TypeToken<Map<String, Ticket>>() {
			}.getType();
			JsonElement element = gson.toJsonTree(t, type);
			result = element.getAsJsonObject();
		}
		return result;

	}

	public JsonObject showReview(Event event) {
		JsonObject result = new JsonObject();
		Gson gson = new Gson();
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		ReviewDAO reviewDAO = factory.getReviewDao();
		Map<Integer, Review> reviews = reviewDAO.findByEvent(event);
		if (reviews.size() > 0) {
			Type type = new TypeToken<Map<Integer, Review>>() {
			}.getType();
			JsonElement element = gson.toJsonTree(reviews, type);
			result = element.getAsJsonObject();
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "We are sorry, no reviews yet, add new one");

		}

		return result;

	}

	public JsonObject addReview(User user, Event event, String json) {
		JsonObject result = new JsonObject();

		if (user == null) {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, you are't loggin, please sign-in to continue");
			return result;
		}
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		ReviewDAO reviewDAO = factory.getReviewDao();
		UserDAO userdao = factory.getUserDAO();
		Gson gson = new Gson();
		Review review = gson.fromJson(json, Review.class);
		review.setUser(user);
		review.setEvent(event);
		if (userdao.checkSellTicket(user, event) > 0) {
			Integer id = reviewDAO.create(review);
			if (id > 0) {
				result.addProperty("result", "SUCCESS");
				result.addProperty("message", "The Review has been added !");
			} else {
				result.addProperty("result", "FAIL");
				result.addProperty("reason", "Something weird happened, please try again later  !");
			}
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, you cant't review this event!");
		}
		return result;

	}

	public JsonObject showCategory() {
		JsonObject result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventCategoryDAO categorydao = postgres.getEventCategoryDAO();
		Map<Integer, AnchestorEventCategory> category = categorydao.showCategory();
		Type type = new TypeToken<Map<Integer, AnchestorEventCategory>>() {
		}.getType();
		JsonElement element = gson.toJsonTree(category, type);
		result = element.getAsJsonObject();
		return result;

	}

	public JsonObject showTicketCategory() {
		JsonObject result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		TicketCategoryDAO categorydao = postgres.getTicketCategoryDao();
		Map<Integer, TicketCategory> category = categorydao.showCategory();
		Type type = new TypeToken<Map<Integer, TicketCategory>>() {
		}.getType();
		JsonElement element = gson.toJsonTree(category, type);
		result = element.getAsJsonObject();
		return result;

	}

	public JsonObject showGuest() {
		JsonObject result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		GuestDAO guestdao = postgres.getGuestDAO();
		Map<Integer, Guest> guests = guestdao.findByEvent(event);
		Type type = new TypeToken<Map<Integer, Guest>>() {
		}.getType();
		JsonElement element = gson.toJsonTree(guests, type);
		result = element.getAsJsonObject();
		return result;

	}

	public JsonObject deleteGuest(String json2) {
		JsonObject result = new JsonObject();
		Gson gson = new Gson();
		Guest guest = gson.fromJson(json2, Guest.class);
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		GuestDAO guestdao = postgres.getGuestDAO();
		if (guestdao.delete(guest) > 0) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "The Guest has been deleted !");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later  !");

		}

		return result;
	}

	public JsonObject editGuest(String json2) {
		JsonObject result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		GuestDAO guestdao = postgres.getGuestDAO();
		Gson Gson = new Gson();
		Guest guest = Gson.fromJson(json2, Guest.class);
		if (guestdao.update(guest) > 0) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "The Guest has been update !");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later  !");

		}

		return result;
	}

	public JsonObject deleteEvent() {
		JsonObject result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventdao = postgres.getEventDAO();
		if (eventdao.delete(event) > 0) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "The Event has been deleted !");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later  !");

		}

		return result;
	}

	public JsonObject suspendEvent() {
		JsonObject result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventdao = postgres.getEventDAO();
		if (eventdao.suspendEvent(event) > 0) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "The Event has been suspended !");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later  !");

		}
		return result;
	}

	public JsonObject updatePriceTicketCategory(String json2) {
		JsonObject result = new JsonObject();
		DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		TicketDAO ticketdao = postgres.getTicketDao();
		Gson gson1 = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json2);
		JsonObject object = element.getAsJsonObject();
		Ticket ticket = gson1.fromJson(json2, Ticket.class);
		int idevent = object.get("idevent").getAsInt();
		Event event1 = new Event();
		event1.setId(idevent);
		TicketCategory tc = new TicketCategory();
		int idtc = object.get("idcategory").getAsInt();
		tc.setId(idtc);
		ticket.setCategory(tc);
		ticket.setEvent(event1);
		if (ticketdao.updatePriceTicket(ticket) > 0) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "Ticket category has been updated");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Something weird happened, please try again later!");
		}

		return result;
	}

}
