package siw.model;

import java.util.HashMap;
import java.util.Map;

public class Guest {
    private int id;
    private String nameguest;
    private String photo;
    private Map<Integer, Event> events;

    public Guest() {
    	this.events= new HashMap<Integer, Event>();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return nameguest;
    }

    public void setName(String name) {
	this.nameguest = name;
    }

    public String getImage() {
	return photo;
    }

    public void setImage(String image) {
	this.photo = image;
    }

    public Map<Integer, Event> getEvents() {
	return events;
    }

    public void setEvents(Map<Integer, Event> events) {
	this.events = events;
    }
}
