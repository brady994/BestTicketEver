package siw.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Guest;
import siw.model.Ticket;
import siw.model.User;
import siw.persistence.DAOFactory;
import siw.persistence.dao.EventDAO;

public class SearchService {

	public SearchService() {
	}

	/***/
	public void getEventById(String data, JsonObject result) {
		if (data == null)
			return;

		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		Gson gson = new Gson();
		Event event = gson.fromJson(data, Event.class);
		if (eventDAO.findById(event.getId()) != null) {
			result.addProperty("result", "SUCCESS");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "This id event not exist ");
		}

	}

	/***/

	public Map<Integer, Event> getEventFindByName(String data, JsonObject result) {

		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		Gson gson = new Gson();
		Event event = gson.fromJson(data, Event.class);
		Map<Integer, Event> resultSearch = eventDAO.findByName(event.getName());
		if (!resultSearch.isEmpty()) {

			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "result find with succes! will be redirective soon");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, this event doesn't exist ");
		}
		return resultSearch;

	}
	// public void getfindByName(String data,JsonObject result)
	// {
	// if(data == null)
	// return;
	//
	// DAOFactory factory=DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
	// EventDAO eventDAO= factory.getEventDAO();
	// Gson gson= new Gson();
	// Event event=gson.fromJson(data, Event.class);
	// if(eventDAO.findByName(event.getName(), event.get, limit))
	// {
	// result.addProperty("result", "SUCCESS");
	// }
	// else
	// {
	// result.addProperty("result", "FAIL");
	// result.addProperty("reason", "This id event not exist ");
	// }
	//
	// }

	public Map<Integer, Event> getEventFindByDate(String data, JsonObject result) {
		DAOFactory postGres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = postGres.getEventDAO();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		JsonObject object = element.getAsJsonObject();
		String date = object.get("name").getAsString();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = format.parse(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Map<Integer, Event> resultEvent = new HashMap<>();
		resultEvent = eventDAO.findByDate(d);

		if (!resultEvent.isEmpty()) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "result find with succes! will be redirective soon");

		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry,for this data doen't exist events ");
		}
		return resultEvent;
	}

	// public Map<Integer, Event> findByDate(String data,JsonObject result)
	// {
	// if(data == null)
	// return;
	//
	// DAOFactory factory=DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
	// EventDAO eventDAO= factory.getEventDAO();
	// Gson gson= new Gson();
	// Event event=gson.fromJson(data, Event.class);
	// if(eventDAO.findByName(event.getName())!=null)
	// {
	// result.addProperty("result", "SUCCESS");
	// }
	// else
	// {
	// result.addProperty("result", "FAIL");
	// result.addProperty("reason", "This name's event not exist ");
	// }
	// }

	public Map<Integer, Event> getEventFindByPrice(String data, JsonObject result) {

		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		JsonObject object = element.getAsJsonObject();
		Ticket ticket = new Ticket();
		ticket.setPrice(object.get("name").getAsFloat());
		Map<Integer, Event> resultSearch = eventDAO.findByPrice(ticket.getPrice());

		if (!resultSearch.isEmpty()) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "result find with succes! will be redirective soon");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, for this price don't exist events ");
		}
		return resultSearch;
	}

	// public Map<Integer, Event> findByPrice(Long min, Long max, Integer
	// offset, Integer limit)
	// {
	// EventDAO eventDAO=
	// DAOFactory.getDaoFactory(DAOFactory.POSTGRES).getEventDAO();
	// return eventDAO.findByPrice(min, max,offset,limit);
	// }

	public Map<Integer, Event> getEventFindByLocation(String data, JsonObject result) {
		if (data == null)
			return null;
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		JsonObject object = element.getAsJsonObject();
		Event event = new Event();
		event.setLocation(object.get("name").getAsString());
		Map<Integer, Event> resultSearch = eventDAO.findByLocation(event.getLocation());
		if (!resultSearch.isEmpty()) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "result find with succes! will be redirective soon");

		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "" + event.getLocation() + "This location doesn't exist ");
		}
		return resultSearch;
	}

	// public Map<Integer, Event> findByLocation(String location, Integer
	// offset, Integer limit)
	// {
	// EventDAO eventDAO=
	// DAOFactory.getDaoFactory(DAOFactory.POSTGRES).getEventDAO();
	// return eventDAO.findByLocation(location,offset,limit);
	// }

	public Map<Integer, Event> getEventFindByGuest(String data, JsonObject result) {

		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);
		JsonObject object = element.getAsJsonObject();
		Guest guest = new Guest();
		guest.setName(object.get("name").getAsString());
		Map<Integer, Event> resultSearch = new HashMap<>();
		resultSearch = eventDAO.findByGuest(guest.getName());
		if (!resultSearch.isEmpty()) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "result find with succes! will be redirective soon");

		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "Sorry, This guest doesn't exist ");
		}
		return resultSearch;
	}

	// public Map<Integer, Event> findByGuest(String guest, Integer offset,
	// Integer limit)
	// {
	// EventDAO eventDAO=
	// DAOFactory.getDaoFactory(DAOFactory.POSTGRES).getEventDAO();
	// return eventDAO.findByGuest(guest,offset,limit);
	//
	// }

	public Map<Integer, Event> getEventFindByCategory(String data, JsonObject result) {

		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		Gson gson = new Gson();
		EventCategory category = new EventCategory();
		category = gson.fromJson(data, EventCategory.class);
		Map<Integer, Event> resultSearch = eventDAO.findByCategory(category);

		if (!resultSearch.isEmpty()) {
			result.addProperty("result", "SUCCESS");
			result.addProperty("message", "result find with succes! will be redirective soon");

		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "This category doesn't exist ");
		}
		return resultSearch;

	}

	// public Map<Integer, Event> findByCategory(String category, Integer
	// offset, Integer limit)
	// {
	// EventDAO eventDAO=
	// DAOFactory.getDaoFactory(DAOFactory.POSTGRES).getEventDAO();
	// return eventDAO.findByCategory(category, offset, limit);
	// }
	public Map<Integer, Event> findEventHomePage(JsonObject result) {
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		Gson gson = new Gson();
		gson = new GsonBuilder().setDateFormat("aaa/MM/dd HH:mm").create();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
		java.util.Date date;
		Map<Integer, Event> resultEvent = new HashMap<>();
		resultEvent = eventDAO.eventHomePage();
		if (!resultEvent.isEmpty()) {
			result.addProperty("result", "SUCCESS");
		} else {
			result.addProperty("result", "FAIL");
			result.addProperty("reason", "This data event not exist ");
		}
		return resultEvent;
	}

	public Map<Integer, Event> findallEventHomePage(JsonObject result) {
		DAOFactory factory = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
		EventDAO eventDAO = factory.getEventDAO();
		Gson gson = new Gson();
		gson = new GsonBuilder().setDateFormat("aaa/MM/dd HH:mm").create();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
		java.util.Date date;
		Map<Integer, Event> resultEvent = new HashMap<>();
		resultEvent = eventDAO.allEventHomePage();
		return resultEvent;
	}
}
