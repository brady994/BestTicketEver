package siw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Event {

    private int id;
    private String name;
    private String location;
    private String image;
    private String description;
    private Date date;
    private EventCategory category;
    private User organizer;
    private Boolean suspended;
    private Map<String, ArrayList<Ticket>> tickets_category; //map based on ticket category
    private Map<String,Ticket> tickets; 
    private Map<Integer, Review> reviews;
    private Map<Integer, Guest> guests;

    public Event() {
    	this.tickets_category=new HashMap<String, ArrayList<Ticket>>();
    	this.guests= new HashMap<Integer, Guest>();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public EventCategory getCategory() {
	return category;
    }

    public void setCategory(EventCategory category) {
	this.category = category;
    }

    public User getOrganizer() {
	return organizer;
    }

    public void setOrganizer(User organizer) {
	this.organizer = organizer;
    }

    public Boolean getSuspended() {
	return suspended;
    }

    public void setSuspended(Boolean sospeso) {
	this.suspended = sospeso;
    }

    public Map<String, Ticket> getTicket() {
	return tickets;
    }

    public void setTicket(Map<String, Ticket> ticket) {
	this.tickets = ticket;
    }

    public Map<Integer, Review> getReviews() {
	return reviews;
    }

    public void setReviews(Map<Integer, Review> reviews) {
	this.reviews = reviews;
    }

    public Map<Integer, Guest> getGuests() {
	return guests;
    }

    public void setGuests(Map<Integer, Guest> guests) {
	this.guests = guests;
    }

	public Map<String, ArrayList<Ticket>> getTickets_category() {
		return tickets_category;
	}

	public void setTickets_category(Map<String, ArrayList<Ticket>> tickets_category) {
		this.tickets_category = tickets_category;
	}

}
