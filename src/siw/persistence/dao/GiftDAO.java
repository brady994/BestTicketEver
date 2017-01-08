package siw.persistence.dao;

import java.util.Date;
import java.util.Map;

import siw.model.Gift;
import siw.model.Ticket;
import siw.model.User;

public interface GiftDAO 
{
	public void create (Gift modelObject);
	
	public void update(Gift g);
	
	public void delete (Gift g);
	
	public Map<Integer,Gift> findByName(String name);
	
	
	
	
	
}
