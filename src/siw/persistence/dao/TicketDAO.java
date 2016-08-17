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
	
	public Map<Integer,Ticket> findBySector(Long Sector);
	
	public Map<Integer,Ticket> findByTicketCategory(TicketCategory tc);
	
	public Map<String, ArrayList<Ticket>> findByEvent(Event e);
	
	public Map<Integer,Ticket> findByEmpty(boolean empty);
	
	public void updateEmpty(boolean e);
	
	public Map<Integer,Ticket> findByEventAndCategory(Event idevent,TicketCategory idCategory,Integer maxRow);

}
