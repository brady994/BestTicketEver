package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.AnchestorEventCategory;
import siw.model.EventCategory;
import siw.persistence.DAOUtility;
import siw.persistence.dao.EventCategoryDAO;

public class EventCategoryDaoJDBC implements EventCategoryDAO {
    HikariDataSource datasource;

    public EventCategoryDaoJDBC(HikariDataSource datasource) {
	this.datasource = datasource;
    }

    @Override
    public boolean create(EventCategory modelobject) {
	Connection connection = null;
	String query = null;
	PreparedStatement statement = null;
	try {
	    connection = datasource.getConnection();
	    query = "insert into EventCategory(Name,anchestorcategory) values(?,?)";
	    statement = connection.prepareStatement(query);
	    statement.setString(1, modelobject.getName());
	    if (modelobject.getAnchestor() != null) {
		statement.setInt(2, modelobject.getAnchestor().getId());
	    } else {
		statement.setNull(2, Types.INTEGER);
	    }
	    return (statement.executeUpdate() > 0);

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    DAOUtility.close(connection);
	    DAOUtility.close(statement);
	}
	return false;
    }

    @Override
    public EventCategory findById(Integer id) {
	
    	
		Connection connection=null;
		String query=null;
		EventCategory eventCategory=null;
		PreparedStatement statement=null;
		ResultSet result=null;
		
		try{
			connection=datasource.getConnection();
			query="Select EC.idEventCategory";
			query+= "FROM  EventCategory as EC";
			query+= "WHERE EC.idEventCategory LIKE ?";
			statement=connection.prepareStatement(query);
			statement.setLong(1, id);
			 result = statement.executeQuery();
			 while(result.next())
			 {
				 eventCategory= new EventCategory();
				 eventCategory.setId(result.getInt("idEventCategory"));
				
			 }
					
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		
		
		return eventCategory;
    }

    @Override
    public EventCategory findByName(String name) 
    {
    	
    	Connection connection=null;
		String query=null;
		EventCategory eventCategory=null;
		PreparedStatement statement=null;
		ResultSet result=null;
		
		try{
			connection=datasource.getConnection();
			query="Select EC.idEventCategory,EC.Name";
			query+= "FROM  EventCategory as EC";
			query+= "WHERE EC.Name LIKE ?";
			statement=connection.prepareStatement(query);
			statement.setString(1, name);
			 result = statement.executeQuery();
			 while(result.next())
			 {
				 eventCategory= new EventCategory();
				 eventCategory.setId(result.getInt("idEventCategory"));
				 eventCategory.setName(result.getString("EC.Name"));
				
			 }
					
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		
		
		return eventCategory;
    }
    	
    


    @Override
    public boolean update(EventCategory modelobject) {

		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		
		try 
		{
			connection=datasource.getConnection();
			query=" Update Gift SET idEventCategory=?,Name=?";
		    statement=connection.prepareStatement(query);
		    statement.setInt(1, modelobject.getId());
		    statement.setString(2, modelobject.getName());
		    
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
	return true;
    }

    @Override
    public boolean delete(EventCategory modelobject) {

		Connection connection=null;
		String query=null;
		PreparedStatement statement=null;
		
		
		try 
		{
			connection=datasource.getConnection();
			query=" DELETE FROM Gift WHERE idEventCategory=?";
		    statement=connection.prepareStatement(query);
		    statement.setInt(1, modelobject.getId());
		    statement.setString(2, modelobject.getName());
		    
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
	return true;

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
