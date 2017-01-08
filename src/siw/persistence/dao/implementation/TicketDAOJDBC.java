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

public class TicketDAOJDBC implements TicketDAO {
	HikariDataSource dataSource;

	public TicketDAOJDBC(HikariDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(Ticket modelObject) {

		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();
			query = "insert into ticket(price,ticketcategory_id,event_id) values(?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setFloat(1, modelObject.getPrice());
			statement.setInt(2, modelObject.getCategory().getId());
			statement.setInt(3, modelObject.getEvent().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			query = "Update Ticket SET idTicket=?, Price=?,TicketCategory=?,Event_id=?";

			statement = connection.prepareStatement(query);
			statement.setInt(1, t.getId());
			statement.setFloat(2, t.getPrice());
			statement.setInt(6, t.getCategory().getId());
			statement.setInt(7, t.getEvent().getId());
			statement.executeUpdate();

		} catch (SQLException e) {
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
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return tickets;

	}


	@Override
	public Map<String, ArrayList<Ticket>> findByEvent(Event e) {
		Ticket ticket = null;
		TicketCategory tc = null;
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = dataSource.getConnection();
			query = "select t.idticket, t.price, tc.name, tc.idticketcategory from Ticket as t INNER JOIN Event as e ON e.idevent=t.event_id INNER JOIN Ticketcategory as tc ON tc.idticketcategory=t.ticketcategory_id WHERE e.idevent=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, e.getId());
			result = statement.executeQuery();

			while (result.next()) {
				ticket = new Ticket();
				tc = new TicketCategory();
				tc.setId(result.getInt("idticketcategory"));
				tc.setName(result.getString("name"));
				ticket.setId(result.getInt("idticket"));
				ticket.setPrice(result.getFloat("price"));
				ticket.setEvent(e);
				ticket.setCategory(tc);
				String category = tc.getName();
				if (!e.getTickets_category().containsKey(category)) {
					ArrayList<Ticket> tickets_category = new ArrayList<Ticket>();
					tickets_category.add(ticket);
					e.getTickets_category().put(category, tickets_category);

				} else {
					e.getTickets_category().get(category).add(ticket);
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return e.getTickets_category();
	}
@Override
	public Map<String, Ticket> findByEvent1(Event e) {
		Map<String, Ticket> tickets = new HashMap<String, Ticket>();
		Ticket ticket = null;
		TicketCategory tc = null;
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = dataSource.getConnection();
			query = "select t.idticket, t.price, tc.name, tc.idticketcategory from Ticket as t INNER JOIN Event as e ON e.idevent=t.event_id INNER JOIN Ticketcategory as tc ON tc.idticketcategory=t.ticketcategory_id WHERE e.idevent=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, e.getId());
			result = statement.executeQuery();
			while (result.next()) {
				ticket = new Ticket();
				tc = new TicketCategory();
				tc.setId(result.getInt("idticketcategory"));
				tc.setName(result.getString("name"));
				ticket.setId(result.getInt("idticket"));
				ticket.setPrice(result.getFloat("price"));
				ticket.setEvent(e);
				ticket.setCategory(tc);
				String category = tc.getName();
				if (!tickets.containsKey(category)) {
					tickets.put(category, ticket);

				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return tickets;
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
			query += "NOT EXISTS( SELECT * FROM usersellticket AS ut WHERE ut.ticket_id=t.idticket )";
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

	@Override
	public Integer soldTicket(Event e, TicketCategory idCategory) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Integer sold = null;
		try {
			connection = dataSource.getConnection();
			query = "Select count(t.idticket) AS sold ";
			query += "FROM Ticket as t ";
			query += "WHERE t.ticketcategory_id=? AND t.event_id=? AND ";
			query += " EXISTS( SELECT * FROM usersellticket AS ut WHERE ut.ticket_id=t.idticket )";
			statement = connection.prepareStatement(query);
			statement.setInt(1, idCategory.getId());
			statement.setInt(2, e.getId());
			result = statement.executeQuery();
			while (result.next()) {
				sold = result.getInt("sold");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return sold;
	}

	@Override
	public int updatePriceTicket(Ticket ticket) {
		Connection connection = null;
		String query = " ";
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			query += "UPDATE ticket SET price=" + ticket.getPrice() + " WHERE ticketcategory_id="
					+ ticket.getCategory().getId() + " AND event_id=" + ticket.getEvent().getId();
			statement = connection.prepareStatement(query);
			return statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		}

	}


}
