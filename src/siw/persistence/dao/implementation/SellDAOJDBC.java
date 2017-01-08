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

    public SellDAOJDBC(HikariDataSource datasource) {
    	this.dataSource=datasource;
	}

	@Override
    public void create(Sell modelObject) {
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
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}

    }

}