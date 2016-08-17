package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.Gift;
import siw.model.Order;
import siw.model.Sell;
import siw.model.Ticket;
import siw.model.User;
import siw.persistence.DAOUtility;
import siw.persistence.dao.SellDAO;

public class SellDAOJDBC implements SellDAO {
    HikariDataSource dataSource;

<<<<<<< HEAD
    public SellDAOJDBC(HikariDataSource datasource) {
    	this.dataSource=datasource;
	}

	@Override
    public void create(Sell modelObject) { //TODO create with gift
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;

	try {
	    connection = dataSource.getConnection();
	    query = "insert into UserSellTicket(User_id,Ticket_id,Date,Price,Order_id) values(?,?,?,?,?)";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, modelObject.getSeller().getId());
	    statement.setInt(2, modelObject.getTicket().getId());
	    statement.setDate(3, new java.sql.Date(modelObject.getDate().getTime()));
	    statement.setFloat(4, modelObject.getPrice());
	    statement.setInt(5, modelObject.getOrder().getId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}

    }

    @Override
    public void delete(Sell s) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Delete From UserSellTicket WHERE idSell = ?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, s.getId());
	    statement.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}
    }

    @Override
    public void update(Sell s) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Update UserSellTicket SET idSell=?, User_id=?,Ticket_id=?, Date=?, Price=?,Order_id=?,Gift_id=?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, s.getId());
	    statement.setInt(2, s.getSeller().getId());
	    statement.setInt(3, s.getTicket().getId());
	    statement.setDate(4, (Date) s.getDate());
	    statement.setFloat(5, s.getPrice());
	    statement.setInt(6, s.getOrder().getId());
	    statement.setInt(7, s.getGift().getId());

	    statement.executeUpdate();

	} catch (SQLException e) {
=======
    @Override
    public void create(Sell modelObject) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;

	try {
	    connection = dataSource.getConnection();
	    query = "insert into UserSellTicket(IdSell,User_id,Ticket_id,Date,Price,Order_id,Gift_id) values(?,?,?,?,?,?)";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, modelObject.getId());
	    statement.setInt(2, modelObject.getSeller().getId());
	    statement.setInt(3, modelObject.getTicket().getId());
	    statement.setDate(4, (Date) modelObject.getDate());
	    statement.setFloat(5, modelObject.getPrice());
	    statement.setInt(6, modelObject.getOrder().getId());
	    statement.setInt(7, modelObject.getGift().getId());
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
    public void delete(Sell s) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Delete From UserSellTicket WHERE idSell = ?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, s.getId());
	    statement.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}
    }

    @Override
    public void update(Sell s) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = dataSource.getConnection();
	    query = "Update UserSellTicket SET idSell=?, User_id=?,Ticket_id=?, Date=?, Price=?,Order_id=?,Gift_id=?";
	    statement = connection.prepareStatement(query);
	    statement.setInt(1, s.getId());
	    statement.setInt(2, s.getSeller().getId());
	    statement.setInt(3, s.getTicket().getId());
	    statement.setDate(4, (Date) s.getDate());
	    statement.setFloat(5, s.getPrice());
	    statement.setInt(6, s.getOrder().getId());
	    statement.setInt(7, s.getGift().getId());

	    statement.executeUpdate();

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}

    }

    @Override
    public Map<Integer, Sell> findByUser(User u) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByTicket(Ticket t) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByDate(Date d) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByPrice(float price) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByOrder(Order o) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByGift(Gift g) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Integer findLastId(Order o) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Sell findById(Long Id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByUser(User u, Integer offset, Integer limit) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByTicket(Ticket t, Integer offset, Integer limit) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByDate(Date d, Integer offset, Integer limit) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByPrice(float price, Integer offset, Integer limit) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByOrder(Order o, Integer offset, Integer limit) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Map<Integer, Sell> findByGift(Gift g, Integer offset, Integer limit) {
	// TODO Auto-generated method stub
	return null;
    }

}
