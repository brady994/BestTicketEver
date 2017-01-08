package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.Event;
import siw.model.Guest;
import siw.persistence.DAOUtility;
import siw.persistence.dao.GuestDAO;

public class GuestDAOJDBC implements GuestDAO {
	HikariDataSource dataSource;

	public GuestDAOJDBC(HikariDataSource datasource) {
		this.dataSource = datasource;
	}

	@Override
	public Integer create(Guest modelObject) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		Integer id=0;

		try {
			connection = dataSource.getConnection();

			query = "insert into guest(Name,Image) values(?,?)";
		    statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, modelObject.getName());
			statement.setString(2, modelObject.getImage());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
		        if(rs.next())
		        {
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
	public Integer update(Guest g) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			query = "Update Guest SET name=?,image=? Where idguest=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, g.getName());
			statement.setString(2, g.getImage());
			statement.setInt(3, g.getId());
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
	public Integer delete(Guest g) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			query = "Delete From Guest WHERE idGuest = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, g.getId());
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
	public Map<Integer, Guest> findByName(String name) {
		Connection connection = null;
		String query = null;
		Map<Integer, Guest> guests = new HashMap<>();
		Guest guest = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			query = "Select G.idGuest,G.Name";
			query += "FROM  Guest as G";
			query += "WHERE G.Name LIKE ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest();
				guest.setId(result.getInt("idGuest"));
				guest.setName(result.getString("Name"));
				guests.put(guest.getId(), guest);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}

		return guests;
	}

	@Override
	public Guest findById(Long id) {
		Connection connection = null;
		String query = null;
		Map<Integer, Guest> guests = new HashMap<>();
		Guest guest = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			query = "Select G.idGuest,G.Name";
			query += "FROM  Guest as G";
			query += "WHERE G.idGuest LIKE ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest();
				guest.setId(result.getInt("idGuest"));
				guest.setName(result.getString("Name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}

		return guest;

	}

	@Override
	public void insertIntoEvent(Guest guest, Event event) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();
			query = "insert into event_has_guest(event_idevent,guest_idguest) values(?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, event.getId());
			statement.setInt(2, guest.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
		}

	}

	@Override
	public Map<Integer,Guest> findByEvent(Event e) {
		Connection connection = null;
		String query = null;
		Map<Integer, Guest> guests = new HashMap<Integer,Guest>();
		Guest guest = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			query = "Select G.idguest,G.name,G.image ";
			query += "FROM  guest as G,event_has_guest as EG ";
			query += "WHERE G.idguest=EG.guest_idguest AND EG.event_idevent= ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, e.getId());
			result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest();
				guest.setId(result.getInt("idguest"));
				guest.setName(result.getString("name"));
				guest.setImage(result.getString("image"));
				guests.put(guest.getId(), guest);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}

		return guests;
		
	}

}
