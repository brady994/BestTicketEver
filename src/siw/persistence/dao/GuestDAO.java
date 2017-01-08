package siw.persistence.dao;

import java.util.Map;

import siw.model.Event;
import siw.model.Guest;

public interface GuestDAO 
{
	public Integer create (Guest modelObject);
	
	public Integer update(Guest g);
	
	public Integer delete(Guest g);
	
	public Map<Integer,Guest> findByName(String name);
	
	public Guest findById(Long id);
	
	public void insertIntoEvent(Guest guest,Event event);
	
	public Map<Integer, Guest> findByEvent(Event e);
	
	
}
