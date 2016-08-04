package siw.persistence;

import com.zaxxer.hikari.HikariDataSource;

import siw.persistence.dao.EventCategoryDAO;
import siw.persistence.dao.EventDAO;
import siw.persistence.dao.OrderDAO;
import siw.persistence.dao.UserDAO;
import siw.persistence.dao.WishlistDAO;
import siw.persistence.dao.implementation.EventCategoryDaoJDBC;
import siw.persistence.dao.implementation.EventDaoJDBC;
import siw.persistence.dao.implementation.OrderDAOJDBC;
import siw.persistence.dao.implementation.UserDaoJDBC;
import siw.persistence.dao.implementation.WishlistDAOJDBC;

/**
 * Concrete Postgres DAO factory implementation
 */
public class PostgresDAOFactory extends DAOFactory {

    public static final String DRIVER = "org.postgresql.Driver";

    private static PostgresDAOFactory postgres;

    private static String DBURL = "jdbc:postgresql://52.39.164.176:5432/db_161874";
    private static String USERNAME = "u_161874";
    private static String PASSWORD = "p@wd_161874";

    private static HikariDataSource datasource;

    public static PostgresDAOFactory getInstance() {
	if (postgres == null) {
	    postgres = new PostgresDAOFactory();
	}
	return postgres;
    }

    private PostgresDAOFactory() {
	try {
	    Class.forName(DRIVER).newInstance();
	    datasource = new HikariDataSource();
	    datasource.setJdbcUrl(DBURL);
	    datasource.setUsername(USERNAME);
	    datasource.setPassword(PASSWORD);
	    datasource.setMaxLifetime(1800000L);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
	    System.err.println("PostgresDAOFactory.class: failed to load JDBC driver\n" + e);
	    e.printStackTrace();
	}
    }

    @Override
    public EventDAO getEventDAO() {
	return new EventDaoJDBC(datasource);
    }

    @Override
    public UserDAO getUserDAO() {
	return new UserDaoJDBC(datasource);
    }

    @Override
    public EventCategoryDAO getEventCategoryDAO() {
	return new EventCategoryDaoJDBC(datasource);
    }

    @Override
    public WishlistDAO getWishlistDAO() {
	return new WishlistDAOJDBC(datasource);
    }

    @Override
    public OrderDAO getOrderDAO() {
	return new OrderDAOJDBC(datasource);
    }
}
