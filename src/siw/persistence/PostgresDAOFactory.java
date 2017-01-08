package siw.persistence;

import com.zaxxer.hikari.HikariDataSource;

import siw.persistence.dao.EventCategoryDAO;
import siw.persistence.dao.EventDAO;
import siw.persistence.dao.GuestDAO;
import siw.persistence.dao.OrderDAO;
import siw.persistence.dao.OrganizerDAO;
import siw.persistence.dao.ReviewDAO;
import siw.persistence.dao.SellDAO;
import siw.persistence.dao.TicketCategoryDAO;
import siw.persistence.dao.TicketDAO;
import siw.persistence.dao.UserDAO;
import siw.persistence.dao.WishlistDAO;
import siw.persistence.dao.implementation.EventCategoryDaoJDBC;
import siw.persistence.dao.implementation.EventDaoJDBC;
import siw.persistence.dao.implementation.GuestDAOJDBC;
import siw.persistence.dao.implementation.OrderDAOJDBC;
import siw.persistence.dao.implementation.OrganizerDAOJDBC;
import siw.persistence.dao.implementation.ReviewDAOJDBC;
import siw.persistence.dao.implementation.SellDAOJDBC;
import siw.persistence.dao.implementation.TicketCategoryDAOJDBC;
import siw.persistence.dao.implementation.TicketDAOJDBC;
import siw.persistence.dao.implementation.UserDaoJDBC;
import siw.persistence.dao.implementation.WishlistDAOJDBC;

/**
 * Concrete Postgres DAO factory implementation
 */
public class PostgresDAOFactory extends DAOFactory {

    public static final String DRIVER = "org.postgresql.Driver";

    private static PostgresDAOFactory postgres;

    private static String DBURL = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";

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

	@Override
	public TicketDAO getTicketDao() {
		return new TicketDAOJDBC(datasource);
	}

	@Override
	public ReviewDAO getReviewDao() {
		return new ReviewDAOJDBC(datasource);
	}

	@Override
	public SellDAO getSellDAO() {
		return new SellDAOJDBC(datasource);
	}

	@Override
	public OrganizerDAO getOrganizerDao() {
		
		return new OrganizerDAOJDBC(datasource);
	}

	@Override
	public TicketCategoryDAO getTicketCategoryDao() {
		return new TicketCategoryDAOJDBC(datasource);
	}

	@Override
	public GuestDAO getGuestDAO() {
		return new GuestDAOJDBC(datasource);
	}
}
