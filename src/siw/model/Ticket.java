package siw.model;

public class Ticket {
	private int id;
	private float price;
	private TicketCategory category;
	private Event event;
	private int remainTicket;
	private int soldTicket;


	public Ticket() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public TicketCategory getCategory() {
		return category;
	}

	public void setCategory(TicketCategory category) {
		this.category = category;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getRemainTicket() {
		return remainTicket;
	}

	public void setRemainTicket(int remainTicket) {
		this.remainTicket = remainTicket;
	}

	public int getSoldTicket() {
		return soldTicket;
	}

	public void setSoldTicket(int soldTicket) {
		this.soldTicket = soldTicket;
	}

	

	

}
