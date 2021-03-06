
package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.Event;
import siw.model.Ticket;
import siw.model.TicketCategory;
import siw.model.User;
import siw.model.Wishlist;
import siw.persistence.DAOUtility;
import siw.persistence.dao.WishlistDAO;

public class WishlistDAOJDBC implements WishlistDAO {
    HikariDataSource dataSource;

    public WishlistDAOJDBC(HikariDataSource datasource) {
	this.dataSource = datasource;
    }

    @Override
    public int create(Wishlist modelObject) {

	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;

	try {
	    connection = dataSource.getConnection();
	    query = "insert into Wishlist(Title,User_idUser)  values(?,?)";
	    statement = connection.prepareStatement(query);
	    statement.setString(1, modelObject.getTitle());
	    statement.setInt(2, modelObject.getUser().getId());

	    return statement.executeUpdate();

	} catch (

	SQLException e) {
	    e.printStackTrace();
	    return 0;
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}
    }

    @Override
    public int delete(Wishlist wishlist) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;

	try {
	    connection = dataSource.getConnection();
	    query = "Delete From wishlist Where idwishlist=?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, wishlist.getId());
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
    public void update(Wishlist wishlist) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Update Wishlist SET idWishlist=?, Title=?,User_idUser=?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, wishlist.getId());
	    statement.setString(2, wishlist.getTitle());
	    statement.setInt(3, wishlist.getUser().getId());
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}
    }

    @Override
    public Wishlist findById(Integer id) {
	Wishlist wishlist = null;
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Select E.idevent, E.name, E.date, E.location ,E.Image,t.idticket, t.price, t.ticketcategory_id, tc.name as nameC ";
	    query += "FROM Wishlist as W ";
	    query += "INNER JOIN wishlist_has_tickets as WHE ON W.idwishlist = WHE.wishlist_idwishlist ";
	    query+="INNER JOIN ticket as t ON t.idticket=WHE.ticket_idticket ";
	    query += "INNER JOIN event as E ON E.idevent=t.event_id ";
	    query+="INNER JOIN ticketcategory as tc ON tc.idticketcategory=t.ticketcategory_id ";
	    query += "WHERE W.idwishlist=? ";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, id);
	    result = statement.executeQuery();
	    if (result.isBeforeFirst()) {
		wishlist = new Wishlist();
		while (result.next()) {
		    Ticket ticket = new Ticket();
		    Event event = new Event();
		    event.setId(result.getInt("idevent"));
		    event.setName(result.getString("name"));
		    long time = result.getDate("date").getTime();
		    event.setDate(new Date(time));
		    event.setLocation(result.getString("location"));
		    event.setImage(result.getString("image"));
		    ticket.setEvent(event);
		    ticket.setId(result.getInt("idticket"));
		    ticket.setPrice(result.getInt("price"));
		    TicketCategory category = new TicketCategory();
		    category.setId(result.getInt("ticketcategory_id"));
		    category.setName(result.getString("nameC"));
		    ticket.setCategory(category);
		    wishlist.getTickets().put(ticket.getId(), ticket);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    return null;
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	    DAOUtility.close(result);
	}
	return wishlist;
    }

 

    

    @Override
    public Map<Integer, Wishlist> findByUser(Integer id, Integer offset, Integer limit) {
	HashMap<Integer, Wishlist> wishlists = null;
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Select idwishlist, title ";
	    query += "FROM Wishlist ";
	    query += "WHERE user_iduser=? ";
	    query += "OFFSET ? LIMIT ?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, id);
	    statement.setInt(2, offset);
	    statement.setInt(3, limit);
	    result = statement.executeQuery();
	    if (result.isBeforeFirst()) {
		wishlists = new HashMap<>();
		while (result.next()) {
		    Wishlist wishlist = new Wishlist();
		    wishlist.setId(result.getInt("idwishlist"));
		    wishlist.setTitle(result.getString("Title"));
		    wishlists.put(wishlist.getId(), wishlist);
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    return null;
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	    DAOUtility.close(result);
	}
	return wishlists;
    }

    @Override
    public boolean updateWishTicket(Wishlist wishlist, Ticket ticket) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Insert into wishlist_has_tickets(wishlist_idwishlist,ticket_idticket) values(?,?)";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, wishlist.getId());
	    statement.setInt(2, ticket.getId());
	    return (statement.executeUpdate() > 0) ? true : false;

	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}
    }

}
