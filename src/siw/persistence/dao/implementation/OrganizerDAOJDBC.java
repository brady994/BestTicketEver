package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.AnchestorEventCategory;
import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Organizer;
import siw.persistence.DAOUtility;
import siw.persistence.dao.OrganizerDAO;

public class OrganizerDAOJDBC implements OrganizerDAO
{
	HikariDataSource dataSource;

	public OrganizerDAOJDBC(HikariDataSource datasource) {
		this.dataSource = datasource;
	}

	@Override
	public void create(Organizer modelObject) {
		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		try {
			connection=dataSource.getConnection();
			 query = "insert into User(idUser,Username,Password,Email,Name,Surname,Type,Coins) values(?,?,?,?,?,?,?,?)";
			    statement = connection.prepareStatement(query);
			    statement.setInt(1, modelObject.getId());
			    statement.setString(2, modelObject.getUsername());
			    statement.setString(3, modelObject.getPassword());
			    statement.setString(4, modelObject.getEmail());
			    statement.setString(5, modelObject.getName());
			    statement.setString(6, modelObject.getSurname());
			    statement.setString(7, modelObject.getType().name());
			    statement.setLong(8, modelObject.getCoins());
			    statement.executeUpdate();
			    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		
		}
		
	}

	@Override
	public void delete(Organizer o) {
		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		try 
		{
			connection=dataSource.getConnection();
			 query = "Delete From User WHERE idUser=?";
			 statement=connection.prepareStatement(query);
			 statement.setInt(1, o.getId());
	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		}
	}

	@Override
	public void update(Organizer o) 
	{
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
		    connection = dataSource.getConnection();
		    query = "Update User SET Username=?, Password=?,Email=?,Name=?,Surname=?,Type=?,Coins=? WHERE idUser = ?";
		    statement = connection.prepareStatement(query);
		    statement.setString(1, o.getUsername());
		    statement.setString(2, o.getPassword());
		    statement.setString(3, o.getEmail());
		    statement.setString(4, o.getName());
		    statement.setString(5, o.getSurname());
		    statement.setString(6, o.getType().name());
		    statement.setFloat(7, o.getCoins());
		    statement.setInt(8, o.getId());
		    statement.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		}
		
	}


	@Override
	public Map<Integer, Event> getEvents(Organizer o) {
		Map<Integer,Event> events = new HashMap<Integer, Event>();
		Connection connection = null;
		PreparedStatement statement= null;
		ResultSet result= null;
		String query = "";
		try {
			connection = dataSource.getConnection();
			 query = "Select E.idevent,E.name,E.location,E.date,E.suspended,EC.ideventcategory,EC.name as categoryname,E.image,AC.name as anchestorname,AC.idanchestor ";
			    query += "FROM event as E ";
			    query += "INNER JOIN eventcategory as EC ON E.category_id=EC.ideventcategory ";
			    query += "INNER JOIN anchestorcategory as AC ON AC.idanchestor=EC.anchestorcategory_id ";
			    query += "WHERE E.organizer_id= ?";
			    statement = connection.prepareStatement(query);
			    statement.setInt(1, o.getId());
			    result= statement.executeQuery();
			    while(result.next())
			    {
			    	Event event = new Event();
			    	event.setId(result.getInt("idevent"));
					event.setDate(result.getDate("date"));
					event.setLocation(result.getString("location"));
					event.setName(result.getString("name"));
					event.setImage(result.getString("image"));
					event.setSuspended(result.getBoolean("suspended"));
					AnchestorEventCategory anchestorcategory= new AnchestorEventCategory();
					anchestorcategory.setId(result.getInt("idanchestor"));
					anchestorcategory.setName(result.getString("anchestorname"));
					EventCategory category = new EventCategory();
					category.setId(result.getInt("ideventcategory"));
					category.setName(result.getString("categoryname"));
					category.setAnchestor(anchestorcategory);
					event.setCategory(category);
					events.put(event.getId(), event);
					
			    	
			    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o.setEvents(events);
		return events;
	}

}
