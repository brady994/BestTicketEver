package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Ticket;
import siw.model.TicketCategory;
import siw.persistence.DAOUtility;
import siw.persistence.dao.TicketDAO;

public class TicketDAOJDBC implements TicketDAO
{	HikariDataSource dataSource;

	public  TicketDAOJDBC(HikariDataSource dataSource) 
	{
		this.dataSource=dataSource;
	}

	@Override
	public void create(Ticket modelObject) 
	{
			

		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		try 	
		{
			connection=dataSource.getConnection();
			query="insert into TIcket(idTicket,Price,Sector,Row,Seat,TicketCategory,Event_id,Empty) values(?,?,?,?,?,?,?,?)";
			statement=connection.prepareStatement(query);
			
			statement.setInt(1, modelObject.getId());
			statement.setFloat(2, modelObject.getPrice());
			statement.setString(3, modelObject.getSector());
			statement.setString(4, modelObject.getRow());
			statement.setString(5, modelObject.getSeat());
			statement.setInt(6, modelObject.getCategory().getId());
			statement.setInt(7, modelObject.getEvent().getId());
			statement.setBoolean(8, modelObject.isEmpty());
			
			
			
			statement.executeUpdate();
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
	public void delete(Ticket t) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
		    connection = dataSource.getConnection();
		    query = "Delete From Ticket WHERE idTicket = ?";
		    statement = connection.prepareStatement(query);
		    statement.setInt(1, t.getId());
		    statement.executeUpdate();

		} 
		catch (SQLException e) 
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
	public void update(Ticket t) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
		    connection = dataSource.getConnection();
		    query = "Update Ticket SET idTicket=?, Price=?,Sector=?,Row=?,Seat=?,TicketCategory=?,Event_id=?,Empty=?";
		    
		    statement = connection.prepareStatement(query);
		    statement.setInt(1, t.getId());
		    statement.setFloat(2, t.getPrice());
		    statement.setString(3, t.getSector());
		    statement.setString(4, t.getRow());
		    statement.setString(5, t.getSeat());
		    statement.setInt(6, t.getCategory().getId());
		    statement.setInt(7,t.getEvent().getId());
		    statement.setBoolean(8, t.isEmpty());
		    statement.executeUpdate();

		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		}
		
		
	}

	@Override
	public Ticket findById(Integer id) {
	
		Connection connection = null;
		String query = null;
		Ticket ticket = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
		    connection = dataSource.getConnection();
		    query = "Select T.idTicket, T.price, e.idevent, e.name, e.location, e.date, e.image, e.description, tc.name AS nametc, tc.idticketcategory ";
		    query += "FROM Ticket as T INNER JOIN event as e ON e.idevent=T.event_id INNER JOIN ticketcategory as tc ON t.ticketcategory_id=tc.idticketcategory ";
		    query += "WHERE T.idTicket=?";
		    statement = connection.prepareStatement(query);
		    statement.setInt(1, id);
		    result = statement.executeQuery();
		    while (result.next()) {
		    ticket = new Ticket();
		    ticket.setPrice(result.getFloat("price"));
		    ticket.setId(result.getInt("idticket"));
		    TicketCategory category = new TicketCategory();
		    category.setId(result.getInt("idticketcategory"));
		    category.setName(result.getString("nametc"));
		    ticket.setCategory(category);
		    Event event = new Event();
		    event.setId(result.getInt("idevent"));
		    event.setName(result.getString("name"));
		    event.setLocation(result.getString("location"));
		    event.setDate(result.getDate("date"));
		    event.setImage(result.getString("image"));
		    event.setDescription(result.getString("description"));
		    ticket.setEvent(event);
		    
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		    DAOUtility.close(result);
		}
		return ticket;
		
	}

	@Override
	public Map<Integer, Ticket> findByPrice(float price) {
		Map<Integer, Ticket> tickets = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
		    connection = dataSource.getConnection();
		    query = "Select T.idTicket,T.Price";
		    query += "FROM Ticket as T";
		    query += "WHERE T.Price LIKE ?";
		    statement = connection.prepareStatement(query);
		    
		    result = statement.executeQuery();
		    while (result.next()) {
		    Ticket ticket = new Ticket();
		    ticket.setId(result.getInt("T.idTicket"));
			ticket.setPrice(result.getFloat("T.Price"));
			tickets.put(ticket.getId(), ticket);
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		    DAOUtility.close(result);
		}
		return tickets;
		
	}

	@Override
	public Map<Integer, Ticket> findBySector(Long Sector) {
		Map<Integer, Ticket> tickets = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
		    connection = dataSource.getConnection();
		    query = "Select T.idTicket,T.Sector";
		    query += "FROM Ticket as T";
		    query += "WHERE T.Sector LIKE ?";
		    statement = connection.prepareStatement(query);
		    result = statement.executeQuery();
		    while (result.next()) {
		    Ticket ticket = new Ticket();
		    ticket.setId(result.getInt("T.idTicket"));
			ticket.setSector(result.getString("T.Sector"));
			tickets.put(ticket.getId(), ticket);
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		    DAOUtility.close(result);
		}
		return tickets;
	
	}

	@Override
	public Map<Integer, Ticket> findByTicketCategory(TicketCategory tc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ArrayList<Ticket>> findByEvent(Event e) {
		Map<String, ArrayList<Ticket>> tickets = new HashMap<String, ArrayList<Ticket>>();
		Ticket ticket=null;
		TicketCategory tc=null;
		Connection connection = null;
		String query =null;
		PreparedStatement statement = null;
		ResultSet result= null;
		try {
			connection = dataSource.getConnection();
			query = "select t.idticket, t.price, tc.name, tc.idticketcategory from Ticket as t INNER JOIN Event as e ON e.idevent=t.event_id INNER JOIN Ticketcategory as tc ON tc.idticketcategory=t.ticketcategory_id WHERE e.idevent=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, e.getId());
			result= statement.executeQuery();
			while(result.next())
			{
				ticket = new Ticket();
				tc= new TicketCategory();
				tc.setId(result.getInt("idticketcategory"));
				tc.setName(result.getString("name"));
				ticket.setId(result.getInt("idticket"));
				ticket.setPrice(result.getFloat("price"));
				ticket.setEvent(e);
				String category = tc.getName();
				if(!e.getTickets_category().containsKey(category))
				{
					ArrayList<Ticket> tickets_category = new ArrayList<Ticket>();
					tickets_category.add(ticket);
					e.getTickets_category().put(category, tickets_category);
					
				}else{
					e.getTickets_category().get(category).add(ticket);
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			DAOUtility.close(connection);
		    DAOUtility.close(statement);
		    DAOUtility.close(result);
		}
		return tickets;
	    }
	

	@Override
	public Map<Integer, Ticket> findByEmpty(boolean empty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEmpty(boolean e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Ticket> findByEventAndCategory(Event idevent, TicketCategory idCategory, Integer maxRow) {
		
		Map<Integer, Ticket> tickets = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
		    connection = dataSource.getConnection();
		    query = "Select t.idticket, t.price ";
		    query += "FROM Ticket as t ";
		    query += "WHERE t.ticketcategory_id=? AND t.event_id=? AND ";
		    query +="NOT EXISTS( SELECT * FROM usersellticket AS ut WHERE ut.ticket_id=t.idticket )";
		    System.out.println(query);
		    statement = connection.prepareStatement(query);
		    statement.setInt(1, idCategory.getId());
		    statement.setInt(2, idevent.getId());
		    statement.setMaxRows(maxRow);
		    result = statement.executeQuery();
		    while (result.next()) {
		    Ticket ticket = new Ticket();
		    ticket.setId(result.getInt("idticket"));
			ticket.setPrice(result.getFloat("price"));
			ticket.setCategory(idCategory);
			ticket.setEvent(idevent);
			tickets.put(ticket.getId(), ticket);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DAOUtility.close(connection);
		    DAOUtility.close(statement);
		    DAOUtility.close(result);
		}
		return tickets;
	}
	

}
