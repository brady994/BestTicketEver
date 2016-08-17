package siw.service;

<<<<<<< HEAD
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import siw.model.Event;
import siw.model.Review;
import siw.model.Ticket;
import siw.model.User;
import siw.persistence.DAOFactory;
import siw.persistence.dao.EventDAO;
import siw.persistence.dao.ReviewDAO;
import siw.persistence.dao.TicketDAO;
import siw.persistence.dao.implementation.TicketDAOJDBC;

public class EventService {
    Gson gson;
    String json;
    Event event;

    public EventService(String json) {
	this.json = json;
	this.gson = new Gson();
	event = gson.fromJson(json, Event.class);
    }
    public EventService()
    {
    	
    }

    public boolean createEvent() 
    {
	// create the dao that save event on the db
    	EventDAO eventDAO = DAOFactory.getDaoFactory(DAOFactory.POSTGRES).getEventDAO();
    	eventDAO.create(event);
    	return true;
    }
    public void updateEvent(String data)
    {
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	EventDAO eventDAO =factory.getEventDAO();
    	Gson gson= new Gson();
    	Event event=gson.fromJson(data, Event.class);
    	eventDAO.update(event);
    }
    public Map<Integer, Event> showEvent(String data)
    {
    	
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	EventDAO eventDAO =factory.getEventDAO();
    	return eventDAO.findByName(data);
    	
    	
    }
    public Event showSingleEvent(Integer id)
    {
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	EventDAO eventDAO =factory.getEventDAO();
    	return eventDAO.findById(id);
    }
    public Map<String,ArrayList<Ticket>> getTicketsEvent(Event e)
    {
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	TicketDAO ticketDao=factory.getTicketDao();
    	return ticketDao.findByEvent(e);
    	
    }
    public JsonObject showReview(Event event)
    {
    	JsonObject result = new JsonObject();
    	Gson gson = new Gson();
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	ReviewDAO reviewDAO =factory.getReviewDao();
    	Map<Integer, Review> reviews= reviewDAO.findByEvent(event);
    	if (reviews.size()>0)
    	{
    		Type type = new TypeToken<Map<Integer, Review>>() {
    	    }.getType();
    	    JsonElement element=gson.toJsonTree(reviews,type);
    	    result=element.getAsJsonObject();
    	}else{
    		result.addProperty("result", "FAIL");
    		result.addProperty("reason", "We are sorry, no reviews yet, add new one");
    		
    	}
    		
    	return result;
    	
    }
    
    public JsonObject addReview(User user, Event event, String json)
    {
    	JsonObject result = new JsonObject();
    	
    	if (user == null)
    	{
    		result.addProperty("result", "FAIL");
    		result.addProperty("reason", "Sorry, you are't loggin, please sign-in to continue");
    		return result;
    	}
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	ReviewDAO reviewDAO =factory.getReviewDao();
    	Gson gson = new Gson();
    	Review review = gson.fromJson(json, Review.class);
    	review.setUser(user);
    	review.setEvent(event);
    	Integer id =reviewDAO.create(review);
    	if (id > 0)
    	{
    		result.addProperty("result", "SUCCESS");
    	    result.addProperty("message", "The Review has been added !");
    	}else{
    		 result.addProperty("result", "FAIL");
    		 result.addProperty("reason", "Something weird happened, please try again later  !");
    	}
    	return result;
    	
    }
    
=======
import java.util.Map;

import com.google.gson.Gson;

import siw.model.Event;
import siw.persistence.DAOFactory;
import siw.persistence.dao.EventDAO;

public class EventService {
    Gson gson;
    String json;
    Event event;

    public EventService(String json) {
	this.json = json;
	this.gson = new Gson();
	event = gson.fromJson(json, Event.class);
    }

    public boolean createEvent() 
    {
	// create the dao that save event on the db
    	EventDAO eventDAO = DAOFactory.getDaoFactory(DAOFactory.POSTGRES).getEventDAO();
    	eventDAO.create(event);
    	return true;
    }
    public void updateEvent(String data)
    {
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	EventDAO eventDAO =factory.getEventDAO();
    	Gson gson= new Gson();
    	Event event=gson.fromJson(data, Event.class);
    	eventDAO.update(event);
    }
    public Map<Integer, Event> showEvent(String data)
    {
    	
    	DAOFactory factory= DAOFactory.getDaoFactory(DAOFactory.POSTGRES);
    	EventDAO eventDAO =factory.getEventDAO();
    	return eventDAO.findByName(data);
    	
    	
    }

>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
}
