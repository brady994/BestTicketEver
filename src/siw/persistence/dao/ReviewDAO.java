package siw.persistence.dao;

import java.util.Map;

import siw.model.Event;
import siw.model.Review;
import siw.model.User;

public interface ReviewDAO 
{
<<<<<<< HEAD
	public Integer create(Review modelObject);
=======
	public void create(Review modelObject);
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
	
	public void delete(Review r);
	
	public void update(Review r);
	
	public Map<Integer,Review> findById(Long id);
	
	public Map<Integer,Review> findByUser(User name);
	
	public Map<Integer,Review> findByTitle(String name);
	
	public Map<Integer,Review> findByEvent(Event name);
	
	public Map<Integer,Review> findByFeedback(Long feedback);
	
	
}

