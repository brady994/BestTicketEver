package siw.persistence.dao;

import java.util.Date;
import java.util.Map;

import siw.model.AnchestorEventCategory;
import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Ticket;
import siw.model.User;

public interface EventDAO {

	public Integer create(Event modelObject);

	public Event findById(Integer id);

	public Map<Integer, Event> findByName(String name);

	public Map<Integer, Event> findByDate(Date date);

	public Map<Integer, Event> findByLocation(String location);

	public Map<Integer, Event> findByCategory(EventCategory category);

	public int update(Event e);

	public int delete(Event e);

	public Map<Integer, Event> findByPrice(float max);

	public Integer suspendEvent(Event event);

	public Map<Integer, Event> eventHomePage();

	public Map<Integer, Event> allEventHomePage();

	public Map<Integer, AnchestorEventCategory> showCategory();

	public Map<Integer, Event> findByGuest(String guest);
}
