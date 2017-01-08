package siw.persistence.dao;

import java.util.Map;

import siw.model.Event;
import siw.model.Organizer;

public interface OrganizerDAO 
{
	public void create(Organizer modelObject);
	
	public void delete(Organizer o);
	
	public void update(Organizer o);
	
	public Map<Integer,Event> getEvents(Organizer o);
	
	
}
