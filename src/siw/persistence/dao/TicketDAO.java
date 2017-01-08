package siw.persistence.dao;

import java.util.ArrayList;
import java.util.Map;

import siw.model.Event;
import siw.model.Ticket;
import siw.model.TicketCategory;

public interface TicketDAO 
{
	public void create(Ticket modelObject);
	
	public void delete(Ticket t);
	
	public void update (Ticket t);
	
	public Ticket findById(Integer id);
	
	public Map<Integer,Ticket> findByPrice(float price);
	
	public Map<String, ArrayList<Ticket>> findByEvent(Event e);
	
	public Map<Integer,Ticket> findByEventAndCategory(Event idevent,TicketCategory idCategory,Integer maxRow);

	public Integer soldTicket(Event e, TicketCategory idCategory);
	public Map<String, Ticket> findByEvent1(Event e);

	public int updatePriceTicket(Ticket ticket);


}
