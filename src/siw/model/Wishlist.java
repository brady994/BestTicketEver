package siw.model;

import java.util.HashMap;
import java.util.Map;

public class Wishlist {
    private int id;
    private String title;
    private User user;
    private Map<Integer, Ticket> tickets;

    public Wishlist() {
    	this.tickets=new HashMap<Integer,Ticket>();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

	public Map<Integer, Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Map<Integer, Ticket> tickets) {
		this.tickets = tickets;
	}


}
