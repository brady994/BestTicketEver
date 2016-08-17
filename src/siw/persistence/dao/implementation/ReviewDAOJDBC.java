package siw.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.postgresql.util.HStoreConverter;

import com.zaxxer.hikari.HikariDataSource;

import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Review;
import siw.model.User;
import siw.persistence.DAOUtility;
import siw.persistence.dao.ReviewDAO;

public class ReviewDAOJDBC implements ReviewDAO {
	HikariDataSource dataSource;

	public ReviewDAOJDBC(HikariDataSource datasource) {
		this.dataSource = datasource;

	}

	@Override
	public Integer create(Review modelObject)
	{
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();

			query = "insert into Review(User_id,Event_id,Title,Text,feedback) values(?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, modelObject.getUser().getId());
			statement.setInt(2, modelObject.getEvent().getId());
			statement.setString(3, modelObject.getTitle());
			statement.setString(4, modelObject.getText());
			statement.setInt(5, modelObject.getFeedback());
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
	public void delete(Review r) {

		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			query = "Delete From Review WHERE idReview=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, r.getId());
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
	public void update(Review r) {
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			query = "Update User SET idReview=?, User_id=?,Event_id=?,Title=?,Text=?,Feedback=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, r.getId());
			statement.setInt(2, r.getUser().getId());
			statement.setInt(3, r.getEvent().getId());
			statement.setString(4, r.getTitle());
			statement.setString(5, r.getText());
			statement.setInt(6, r.getFeedback());
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
	public Map<Integer, Review> findById(Long id) {
		Map<Integer, Review> reviews = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = dataSource.getConnection();
			query = "Select R.idReview,R.User_id,R.Event_id,R.Title,R.Text,R.Feedback";
			query += "FROM Review as R";
			query += "WHERE R.idReview LIKE ?";
			statement = connection.prepareStatement(query);

			result = statement.executeQuery();
			while (result.next()) {
				Review review = new Review();
				review.setId(result.getInt("R.idReview"));
				reviews.put(review.getId(), review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return reviews;
	}

	@Override
	public Map<Integer, Review> findByUser(User name) {
		Map<Integer, Review> reviews = new HashMap<Integer, Review>();
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "";
		ResultSet result = null;

		try {
			connection= dataSource.getConnection();
			query = "Select idreview,title,text,feedback,E.idevent,E.name,E.image ";
			query += "FROM Review as R ";
			query += "INNER JOIN Event as E ON R.event_id=E.idevent ";
			query += "WHERE R.user_id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, name.getId());
			result = statement.executeQuery();
			while (result.next()) {
				Review review = new Review();
				review.setId(result.getInt("idreview"));
				review.setTitle(result.getString("title"));
				review.setText(result.getString("text"));
				review.setFeedback(result.getInt("feedback"));
				Event event = new Event();
				event.setId(result.getInt("idevent"));
				event.setName(result.getString("name"));
				event.setImage(result.getString("image"));
				review.setUser(name);
				review.setEvent(event);
				reviews.put(review.getId(), review);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

	@Override
	public Map<Integer, Review> findByTitle(String name) {
		Map<Integer, Review> reviews = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = dataSource.getConnection();
			query = "Select R.idReview,R.User_id,R.Event_id,R.Title,R.Text,R.Feedback";
			query += "FROM Review as R";
			query += "WHERE R.idReview LIKE ?";
			statement = connection.prepareStatement(query);

			result = statement.executeQuery();
			while (result.next()) {
				Review review = new Review();
				review.setId(result.getInt("R.idReview"));
				review.setTitle(result.getString("R.Title"));
				reviews.put(review.getId(), review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return reviews;

	}

	@Override
	public Map<Integer, Review> findByEvent(Event name) {
		Map<Integer, Review> reviews = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = dataSource.getConnection();
			query = "Select R.idreview, R.user_id, R.event_id, R.title, R.text, R.feedback, u.username ";
			query += "FROM Review as R INNER JOIN users as u ON u.iduser=R.user_id ";
			query += "WHERE R.event_id= ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, name.getId());
			result = statement.executeQuery();
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				Review review = new Review();
				review.setTitle(result.getString("title"));
				review.setText(result.getString("text"));
				review.setFeedback(result.getInt("feedback"));
				review.setId(result.getInt("idreview"));
				Event event = new Event();
				event.setId(result.getInt("event_id"));
				review.setEvent(event);
				review.setUser(user);
				reviews.put(review.getId(), review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return reviews;

	}

	@Override
	public Map<Integer, Review> findByFeedback(Long feedback) {
		Map<Integer, Review> reviews = new HashMap<>();
		Connection connection = null;
		String query = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = dataSource.getConnection();
			query = "Select R.idReview,R.User_id,R.Event_id,R.Title,R.Text,R.Feedback";
			query += "FROM Review as R";
			query += "WHERE R.idReview LIKE ?";
			statement = connection.prepareStatement(query);

			result = statement.executeQuery();
			while (result.next()) {
				Review review = new Review();
				review.setId(result.getInt("R.idReview"));
				review.setFeedback(result.getInt("R.Feedback"));
				reviews.put(review.getId(), review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtility.close(connection);
			DAOUtility.close(statement);
			DAOUtility.close(result);
		}
		return reviews;

	}

}
