package siw.persistence.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.AnchestorEventCategory;
import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Guest;
import siw.model.User;
import siw.persistence.DAOUtility;
import siw.persistence.dao.EventDAO;

public class EventDaoJDBC implements EventDAO {
	HikariDataSource datasource;

	public EventDaoJDBC(HikariDataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public Integer create(Event modelObject) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		Integer id = null;
		try {
			connection = datasource.getConnection();
			query = "insert into event(name,location,date,category_id,organizer_id,image,description,suspended) values(?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, modelObject.getName());
			statement.setString(2, modelObject.getLocation());
			long time = modelObject.getDate().getTime();
			statement.setDate(3, new java.sql.Date(time));
			statement.setInt(4, modelObject.getCategory().getId());
			statement.setInt(5, modelObject.getOrganizer().getId());
			statement.setString(6, modelObject.getImage());
			statement.setString(7, modelObject.getDescription());
			statement.setBoolean(8, false);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		}
		return id;

	}

	@Override
	public Event findById(Integer id) {
		Event event = new Event();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
			query = "SELECT e.name as nameE, e.idevent, e.date, e.description, e.location, e.image, e.suspended, g.name, g.idguest, g.image AS imguest FROM Event AS e INNER JOIN event_has_guest AS eg ON eg.event_idevent=e.idevent INNER JOIN guest AS g ON g.idguest=eg.guest_idguest where idevent=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				event.setId(result.getInt("idevent"));
				event.setDate(result.getDate("date"));
				event.setLocation(result.getString("location"));
				event.setName(result.getString("nameE"));
				event.setDescription(result.getString("description"));
				event.setImage(result.getString("image"));
				event.setSuspended(result.getBoolean("suspended"));
				Guest guest = new Guest();
				guest.setId(result.getInt("idguest"));
				guest.setName(result.getString("name"));
				guest.setImage(result.getString("imguest"));
				event.getGuests().put(guest.getId(), guest);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return event;
	}

	@Override
	public Map<Integer, Event> findByName(String name) {
		Map<Integer, Event> events = new HashMap<Integer, Event>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
			query = "Select E.idevent,E.name,E.location,E.description,E.date,E.suspended,EC.ideventcategory,EC.name as categoryname,U.iduser,U.username,E.image ";
			query += "FROM event as E ";
			query += "INNER JOIN eventcategory as EC ON E.category_id=EC.ideventcategory ";
			query += "INNER JOIN users as U ON E.organizer_id=U.iduser ";
			query += "WHERE LOWER(E.name)= LOWER(?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			result = statement.executeQuery();
			while (result.next()) {
				Event event = new Event();
				event.setId(result.getInt("idevent"));
				event.setName(result.getString("name"));
				event.setLocation(result.getString("location"));
				event.setDescription(result.getString("description"));
				event.setImage(result.getString("image"));
				long time = result.getDate("date").getTime();
				event.setDate(new Date(time));
				event.setSuspended(result.getBoolean("suspended"));
				EventCategory category = new EventCategory();
				category.setId(result.getInt("ideventcategory"));
				category.setName(result.getString("categoryname"));
				event.setCategory(category);
				User organizer = new User();
				organizer.setId(result.getInt("iduser"));
				organizer.setUsername(result.getString("username"));
				event.setOrganizer(organizer);
				events.put(event.getId(), event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return events;
	}

	@Override
	public Map<Integer, Event> findByDate(Date date) {
		Map<Integer, Event> events = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
			query = "Select E.idEvent,E.Name,E.Location,E.Date,E.Suspended,EC.ideventcategory,EC.Name,U.iduser,U.Username,E.Image ";
			query += "FROM Event as E ";
			query += "INNER JOIN Eventcategory as EC ON E.Category_id=EC.ideventcategory ";
			query += "INNER JOIN Users as U ON E.Organizer_id=U.iduser ";
			query += "WHERE E.Date= ?";
			statement = connection.prepareStatement(query);
			statement.setDate(1, new java.sql.Date(date.getTime()));
			result = statement.executeQuery();
			while (result.next()) {
				Event event = new Event();
				event.setId(result.getInt("idevent"));
				event.setName(result.getString("Name"));
				event.setLocation(result.getString("Location"));
				long time = result.getDate("Date").getTime();
				event.setDate(new java.sql.Date(time));
				event.setImage(result.getString("image"));
				event.setSuspended(result.getBoolean("Suspended"));
				EventCategory category = new EventCategory();
				category.setId(result.getInt("ideventcategory"));
				category.setName(result.getString("Name"));
				event.setCategory(category);
				User organizer = new User();
				organizer.setId(result.getInt("iduser"));
				organizer.setUsername(result.getString("Username"));
				event.setOrganizer(organizer);
				events.put(event.getId(), event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return events;
	}

	@Override
    public Map<Integer, Event> findByPrice(float max) {
  Map<Integer, Event> events = new HashMap<Integer,Event>();
  Connection connection = null;
  String query = null;
  PreparedStatement statement = null;
  ResultSet result = null;
  try {
      connection = datasource.getConnection();
      query = "Select E.idevent,E.Name,E.Location,E.Date,E.Suspended,E.image,EC.ideventCategory,EC.name,U.iduser,U.username,E.Image,S.Price ";
      query += "FROM Event as E ";
      query += "INNER JOIN Ticket as T ON E.idevent=T.event_id ";
      query += "INNER JOIN usersellticket as S ON T.idticket=S.ticket_id ";
      query += "INNER JOIN eventcategory as EC ON E.category_id=EC.ideventcategory ";
      query += "INNER JOIN users as U ON E.organizer_id=U.iduser ";
      query += "WHERE S.Price <= ? ";
      statement = connection.prepareStatement(query);
      statement.setFloat(1, max);
      result = statement.executeQuery();
      while (result.next()) {
    Event event = new Event();
    event.setId(result.getInt("idevent"));
    event.setName(result.getString("name"));
    event.setLocation(result.getString("location"));
    event.setImage(result.getString("image"));
    long time = result.getDate("date").getTime();
    event.setDate(new java.sql.Date(time));
    event.setSuspended(result.getBoolean("suspended"));
    EventCategory category = new EventCategory();
    category.setId(result.getInt("ideventcategory"));
    category.setName(result.getString("name"));
    event.setCategory(category);
    User organizer = new User();
    organizer.setId(result.getInt("iduser"));
    organizer.setUsername(result.getString("username"));
    event.setOrganizer(organizer);
    events.put(event.getId(), event);
      }
  } catch (SQLException e) {
      e.printStackTrace();
  } finally {
      DAOUtility.close(connection);
      DAOUtility.close(statement);
      DAOUtility.close(result);
  }
  return events;
    }

	@Override
	public Map<Integer, Event> findByLocation(String location) {
		Map<Integer, Event> events = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
			query = "Select E.idevent,E.name,E.location,E.date,E.suspended,EC.ideventcategory,EC.name,U.iduser,U.username,E.image ";
			query += "FROM Event as E ";
			query += "INNER JOIN Eventcategory as EC ON E.category_id=EC.ideventcategory ";
			query += "INNER JOIN Users as U ON E.Organizer_id=U.iduser ";
			query += "LOWER(E.location) = LOWER(?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, location);
			result = statement.executeQuery();
			while (result.next()) {
				Event event = new Event();
				event.setId(result.getInt("idevent"));
				event.setName(result.getString("name"));
				event.setLocation(result.getString("location"));
				event.setImage(result.getString("image"));
				long time = result.getDate("date").getTime();
				event.setDate(new java.sql.Date(time));
				event.setSuspended(result.getBoolean("suspended"));
				EventCategory category = new EventCategory();
				category.setId(result.getInt("ideventcategory"));
				category.setName(result.getString("name"));
				event.setCategory(category);
				User organizer = new User();
				organizer.setId(result.getInt("iduser"));
				organizer.setUsername(result.getString("username"));
				event.setOrganizer(organizer);
				events.put(event.getId(), event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return events;
	}

	@Override
	public Map<Integer, Event> findByGuest(String guest) {
		Map<Integer, Event> events = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
			query = "Select E.idevent,E.Name,E.Location,E.Date,E.Suspended,EC.ideventcategory,EC.Name,U.iduser,U.Username,E.Image ";
			query += "FROM Event as E ";
			query += "INNER JOIN Eventcategory as EC ON E.Category_id=EC.ideventcategory ";
			query += "INNER JOIN Users as U ON E.Organizer_id=U.iduser ";
			query += "INNER JOIN Event_has_guest as HG ON E.idevent=HG.Event_idevent ";
			query += "INNER JOIN Guest as G ON HG.guest_idguest=G.idguest ";
			query += "WHERE LOWER(G.Name)=LOWER("+"?"+")";
			statement = connection.prepareStatement(query);
			statement.setString(1, guest);
			result = statement.executeQuery();
			while (result.next()) {
				Event event = new Event();
				event.setId(result.getInt("idevent"));
				event.setName(result.getString("Name"));
				event.setLocation(result.getString("Location"));
				long time = result.getDate("Date").getTime();
				event.setDate(new java.sql.Date(time));
				event.setSuspended(result.getBoolean("Suspended"));
				event.setImage(result.getString("image"));
				EventCategory category = new EventCategory();
				category.setId(result.getInt("ideventcategory"));
				category.setName(result.getString("Name"));
				event.setCategory(category);
				User organizer = new User();
				organizer.setId(result.getInt("iduser"));
				organizer.setUsername(result.getString("username"));
				event.setOrganizer(organizer);
				events.put(event.getId(), event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return events;
	}

	@Override
	public Map<Integer, Event> findByCategory(EventCategory category) {
		Map<Integer, Event> events = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
			query = "SELECT e.idevent,e.name,e.location,e.date,e.image,e.suspended FROM Event AS e,eventcategory AS ec where  e.suspended=false AND ec.ideventcategory=e.category_id AND ec.name=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, category.getName());
			result = statement.executeQuery();
			while (result.next()) {
				Event event = new Event();
				event.setId(result.getInt("idevent"));
				event.setName(result.getString("Name"));
				event.setLocation(result.getString("Location"));
				event.setImage(result.getString("image"));
				long time = result.getDate("Date").getTime();
				event.setDate(new java.sql.Date(time));
				event.setSuspended(result.getBoolean("Suspended"));
				event.setCategory(category);
				events.put(event.getId(), event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return events;
	}

	@Override
	public int update(Event modelObject) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			connection = datasource.getConnection();
			query = "Update Event SET Name=?, Location=?,Date=?,Category_id=?,Image=? WHERE idEvent = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, modelObject.getName());
			statement.setString(2, modelObject.getLocation());
			statement.setDate(3, new java.sql.Date(modelObject.getDate().getTime()));
			statement.setInt(4, modelObject.getCategory().getId());
			statement.setString(5, modelObject.getImage());
			statement.setInt(6, modelObject.getId());
			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		}

	}

	@Override
	public int delete(Event modelObject) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			connection = datasource.getConnection();
			query = "Delete From Event WHERE idevent = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, modelObject.getId());
			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		}

	}

	
	@Override
	public Integer suspendEvent(Event event) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			connection = datasource.getConnection();
			query = "Update Event SET suspended='true' WHERE idEvent = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, event.getId());
			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		}
	}
	@Override
	    public Map<Integer,Event> eventHomePage()
	    {
	    	Map<Integer,Event> events = new HashMap<>();
	    	Connection connection =null;
	    	String query =null;
	    	PreparedStatement statement =null;
	    	ResultSet result=null;
	    	try
	    	{
	    		connection=datasource.getConnection();
	    		query = "Select E.idevent,E.name,E.location,E.description,E.date,E.suspended,EC.ideventcategory,EC.name as categoryname,U.iduser,U.username,E.image ";
	    	    query += "FROM event as E ";
	    	    query += "INNER JOIN eventcategory as EC ON E.category_id=EC.ideventcategory ";
	    	    query += "INNER JOIN users as U ON E.organizer_id=U.iduser ";
	    	    query += "WHERE E.Date > Now() AND E.Date <= Now()+INTERVAL '14' DAY AND E.suspended=false";
	    	    statement = connection.prepareStatement(query);
	    	    result = statement.executeQuery();
	    	    while (result.next())
	    	    {
	    			Event event = new Event();
	    			event.setId(result.getInt("idevent"));
	    			event.setName(result.getString("name"));
	    			event.setLocation(result.getString("location"));
	    			event.setDescription(result.getString("description"));
	    			event.setImage(result.getString("image"));
	    			long time = result.getDate("date").getTime();
	    			event.setDate(new java.sql.Date(time));
	    			event.setSuspended(result.getBoolean("suspended"));
	    			EventCategory category = new EventCategory();
	    			category.setId(result.getInt("ideventcategory"));
	    			category.setName(result.getString("categoryname"));
	    			event.setCategory(category);
	    			User organizer = new User();
	    			organizer.setId(result.getInt("iduser"));
	    			organizer.setUsername(result.getString("username"));
	    			event.setOrganizer(organizer);
	    			events.put(event.getId(), event);
	    	    }

	    	}catch(SQLException e)
	    	{
	    		e.printStackTrace();
	    	} finally {
	    	    DAOUtility.close(connection);
	    	    DAOUtility.close(statement);
	    	    DAOUtility.close(result);
	    	}
	    	return events;

	    }
		 public Map<Integer,Event> allEventHomePage()
		    {
		    	Map<Integer,Event> events = new HashMap<>();
		    	Connection connection =null;
		    	String query =null;
		    	PreparedStatement statement =null;
		    	ResultSet result=null;
		    	try
		    	{
		    		connection=datasource.getConnection();
		    		query = "Select E.idevent,E.name,E.location,E.description,E.date,E.suspended,EC.ideventcategory,EC.name as categoryname,U.iduser,U.username,E.image ";
		    	    query += "FROM event as E ";
		    	    query += "INNER JOIN eventcategory as EC ON E.category_id=EC.ideventcategory ";
		    	    query += "INNER JOIN users as U ON E.organizer_id=U.iduser ";
		    	    query += "WHERE E.Date > Now()+INTERVAL '14' DAY AND E.suspended=false";
		    	    statement = connection.prepareStatement(query);
		    	    result = statement.executeQuery();
		    	    while (result.next())
		    	    {
		    			Event event = new Event();
		    			event.setId(result.getInt("idevent"));
		    			event.setName(result.getString("name"));
		    			event.setLocation(result.getString("location"));
		    			event.setDescription(result.getString("description"));
		    			event.setImage(result.getString("image"));
		    			long time = result.getDate("date").getTime();
		    			event.setDate(new java.sql.Date(time));
		    			event.setSuspended(result.getBoolean("suspended"));
		    			EventCategory category = new EventCategory();
		    			category.setId(result.getInt("ideventcategory"));
		    			category.setName(result.getString("categoryname"));
		    			event.setCategory(category);
		    			User organizer = new User();
		    			organizer.setId(result.getInt("iduser"));
		    			organizer.setUsername(result.getString("username"));
		    			event.setOrganizer(organizer);
		    			events.put(event.getId(), event);
		    	    }

		    	}catch(SQLException e)
		    	{
		    		e.printStackTrace();
		    	} finally {
		    	    DAOUtility.close(connection);
		    	    DAOUtility.close(statement);
		    	    DAOUtility.close(result);
		    	}
		    	return events;

		    }

		@Override
		public Map<Integer, AnchestorEventCategory> showCategory() {
			    Map<Integer, AnchestorEventCategory> category = new HashMap<Integer, AnchestorEventCategory>();
			    PreparedStatement statement = null;
			    ResultSet result = null;
			    Connection connection = null;
			    String query = "";
			    try {
			      connection = datasource.getConnection();
			      query += "select ac.idanchestor,ac.name as nameanchestor,ec.ideventcategory,ec.name from anchestorcategory as ac, eventcategory as ec where ac.idanchestor=ec.anchestorcategory_id";
			      statement = connection.prepareStatement(query);
			      result = statement.executeQuery();
			      while (result.next()) {
			        AnchestorEventCategory anchestor = new AnchestorEventCategory();
			        anchestor.setId(result.getInt("idanchestor"));
			        anchestor.setName(result.getString("nameanchestor"));
			        EventCategory child = new EventCategory();
			        child.setId(result.getInt("ideventcategory"));
			        child.setName(result.getString("name"));
			        anchestor.getChildren().put(child.getId(), child);
			        if (!category.containsKey(anchestor.getId())) {
			          category.put(anchestor.getId(), anchestor);
			        } else {
			          category.get(anchestor.getId()).getChildren().put(child.getId(), child);
			        }

			      }

			    } catch (SQLException e) {
			      e.printStackTrace();
			    }
			    return category;

			  }
}


