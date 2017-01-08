package siw.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private User user;
    private Map<Integer, Ticket> tickets;
    private float total;

    public Cart() {
	user = null;
	tickets = new HashMap<Integer, Ticket>();
	total = 0;
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

    public float getTotal() {
	return total;
    }

    public void setTotal(float total) {
	this.total = total;
    }
    
    public void sumTotal(float f)
    {
    	this.total+=f;
    }
    
    public void decrementTotal(float f)
    {
    	this.total-=f;
    }
}
