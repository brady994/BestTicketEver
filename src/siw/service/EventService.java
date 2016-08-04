package siw.service;

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

}
