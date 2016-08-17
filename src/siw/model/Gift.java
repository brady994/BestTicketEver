package siw.model;

import java.util.Date;

public class Gift {

    private Integer id;
    private User receiver;
    private User sender;
    private Ticket ticket;
    private Date date;

    public Gift() {
    	id=0;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public User getDestination() {
	return receiver;
    }

    public void setDestination(User destination) {
	this.receiver = destination;
    }

    public User getSource() {
	return sender;
    }

    public void setSource(User source) {
	this.sender = source;
    }

    public Ticket getTicket() {
	return ticket;
    }

    public void setTicket(Ticket ticket) {
	this.ticket = ticket;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

}
