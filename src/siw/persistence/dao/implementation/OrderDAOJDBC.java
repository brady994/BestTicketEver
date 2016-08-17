package siw.persistence.dao.implementation;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.Event;
import siw.model.Order;
import siw.model.Sell;
import siw.model.Ticket;
import siw.model.TicketCategory;
import siw.model.User;
import siw.persistence.DAOUtility;
import siw.persistence.dao.OrderDAO;

public class OrderDAOJDBC implements OrderDAO {
    HikariDataSource dataSource;

    public OrderDAOJDBC(HikariDataSource datasource) {
	this.dataSource = datasource;
    }

    @Override
    public Integer create(Order modelObject) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	Integer id=null;

	try {
	    connection = dataSource.getConnection();
	    query = " INSERT INTO orders( date, user_id, totalcost) VALUES (?, ?, ?)";
	    statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setDate(1, new java.sql.Date(modelObject.getDate().getTime()));
	    statement.setInt(2, modelObject.getUser().getId());
	    statement.setFloat(3, modelObject.getTotal());
	    statement.executeUpdate();
	    ResultSet rs = statement.getGeneratedKeys();
        if(rs.next())
        {
             id = rs.getInt(1);
        }
	    System.out.println(id);
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}
	return id;
    }

    @Override
    public void delete(Order o) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Delete From Order WHERE idOrder = ?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, o.getId());
	    statement.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}

    }

    @Override
    public void update(Order o) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Update orders SET date=?, user_id=?, totalcost=? WHERE idorder=? ";
	    statement = connection.prepareStatement(query);
	    statement.setDate(1, new java.sql.Date(o.getDate().getTime()));
	    statement.setInt(2, o.getUser().getId());
	    statement.setFloat(3, o.getTotal());
	    statement.setInt(4, o.getId());
	    statement.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}

	
    }

    @Override
    public Map<Integer, Order> findByUser(Integer id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Order findById(Integer id) {
	Order order = null;
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Select T.idticket,TC.name as t_cat,E.idevent, E.name,E.date,E.location, E.image, U.iduser, U.username, s.price, s.idsell ";
	    query += "FROM orders as O ";
	    query += "INNER JOIN usersellticket as S ON O.idorder = S.order_id ";
	    query += "INNER JOIN ticket as T ON S.ticket_id = T.idticket ";
	    query += "INNER JOIN ticketcategory as TC ON T.ticketcategory_id = TC.idticketcategory ";
	    query += "INNER JOIN event as E ON T.event_id = E.idevent ";
	    query += "INNER JOIN users as U on S.user_id = U.iduser ";
	    query += "WHERE O.idorder=? ";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, id);
	    result = statement.executeQuery();
	    if (result.isBeforeFirst()) {
		order = new Order();
		order.setSells(new HashMap<Integer, Sell>());
		while (result.next()) {
		    Sell sell = new Sell();
		    sell.setId(result.getInt("idsell"));
		    sell.setPrice(result.getLong("price"));
		    User user = new User();
		    user.setId(result.getInt("iduser"));
		    user.setUsername(result.getString("username"));
		    sell.setSeller(user);
		    Ticket ticket = new Ticket();
		    ticket.setId(result.getInt("idticket"));
		    TicketCategory category = new TicketCategory();
		    category.setName(result.getString("t_cat"));
		    ticket.setCategory(category);
		    sell.setTicket(ticket);
		    Event event = new Event();
		    event.setId(result.getInt("idevent"));
		    event.setName(result.getString("name"));
		    long time = result.getDate("date").getTime();
		    event.setDate(new java.util.Date(time));
		    event.setLocation(result.getString("location"));
		    event.setImage(result.getString("image"));
		    ticket.setEvent(event);
		    order.getSells().put(sell.getId(), sell);
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
	return order;
    }

    @Override
    public Map<Integer, Order> findByUser(Integer id, Integer offset, Integer limit) {
	HashMap<Integer, Order> orders = null;
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Select o.idorder, o.date, totalcost,count(idsell) as items ";
	    query += "FROM orders as o ";
	    query += "INNER JOIN usersellticket as S ON o.idorder = s.order_id ";
	    query += "WHERE o.user_id = ? ";
	    query += "GROUP BY idorder ";
	    query += "OFFSET ? LIMIT ? ";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, id);
	    statement.setInt(2, offset);
	    statement.setInt(3, limit);
	    result = statement.executeQuery();
	    if (result.isBeforeFirst()) {
		orders = new HashMap<>();
		while (result.next()) {
		    Order order = new Order();
		    order.setId(result.getInt("idorder"));
		    long time = result.getDate("date").getTime();
		    order.setDate(new java.util.Date(time));
		    order.setTotal(result.getLong("totalcost"));
		    orders.put(order.getId(), order);
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
	return orders;
    }

    @Override
    public Integer findLastId(User u) {
	return null;
    }

}
