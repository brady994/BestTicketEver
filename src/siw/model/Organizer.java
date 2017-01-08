package siw.model;

import java.util.HashMap;
import java.util.Map;

public class Organizer extends User {
    private Map<Integer, Event> events;

    public Organizer() 
    {
    	events=new HashMap<Integer, Event>();
    }

    public Map<Integer, Event> getEvents() {
	return events;
    }

    public void setEvents(Map<Integer, Event> events) {
	this.events = events;
    }
}
