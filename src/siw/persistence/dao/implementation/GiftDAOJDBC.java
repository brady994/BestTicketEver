package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.Gift;
import siw.model.Ticket;
import siw.model.User;
import siw.persistence.DAOUtility;
import siw.persistence.dao.GiftDAO;

public class GiftDAOJDBC  implements GiftDAO{
	HikariDataSource dataSource;

	@Override
	public void create(Gift modelObject) 
	{
		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		
		try 
		{
			connection=dataSource.getConnection();
			query=" insert into Gift(IdGift,User_id) values(?,?)";
		    statement=connection.prepareStatement(query);
		    statement.setInt(1, modelObject.getId());
		    statement.setInt(2, modelObject.getDestination().getId());
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
	public void update(Gift g) 
	{
		
		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		
		try 
		{
			connection=dataSource.getConnection();
			query=" Update Gift SET idGift=?,User_idUser=?";
		    statement=connection.prepareStatement(query);
		    statement.setInt(1, g.getId());
		    statement.setInt(2, g.getDestination().getId());
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
	public void delete(Gift g) 
	{
		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		
		try 
		{
			connection=dataSource.getConnection();
			query=" Delete From Gift WHERE idGift=?";
		    statement=connection.prepareStatement(query);
		    statement.setInt(1, g.getId());
		    statement.setInt(2, g.getDestination().getId());
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
	public Map<Integer, Gift> findByName(String name) {
		Map<Integer,Gift> gifts=new HashMap<>();
		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		String result=null;

		
		
		return gifts;
	}



}
