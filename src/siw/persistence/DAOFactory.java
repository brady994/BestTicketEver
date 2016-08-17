package siw.persistence;

import siw.persistence.dao.EventCategoryDAO;
import siw.persistence.dao.EventDAO;
import siw.persistence.dao.OrderDAO;
<<<<<<< HEAD
import siw.persistence.dao.ReviewDAO;
import siw.persistence.dao.SellDAO;
import siw.persistence.dao.TicketDAO;
import siw.persistence.dao.UserDAO;
import siw.persistence.dao.WishlistDAO;

public abstract class DAOFactory {

    public static final int POSTGRES = 1;

    public static DAOFactory getDaoFactory(int wichFactory) {
	switch (wichFactory) {
	case 1:
	    return PostgresDAOFactory.getInstance();
	default:
	    return null;
	}
    }

    public abstract EventDAO getEventDAO();

    public abstract EventCategoryDAO getEventCategoryDAO();

    public abstract UserDAO getUserDAO();

    public abstract WishlistDAO getWishlistDAO();

    public abstract OrderDAO getOrderDAO();
    
    public abstract TicketDAO getTicketDao();
    
    public abstract ReviewDAO getReviewDao();

	public abstract SellDAO getSellDAO();
=======
import siw.persistence.dao.UserDAO;
import siw.persistence.dao.WishlistDAO;

public abstract class DAOFactory {

    public static final int POSTGRES = 1;

    public static DAOFactory getDaoFactory(int wichFactory) {
	switch (wichFactory) {
	case 1:
	    return PostgresDAOFactory.getInstance();
	default:
	    return null;
	}
    }

    public abstract EventDAO getEventDAO();

    public abstract EventCategoryDAO getEventCategoryDAO();

    public abstract UserDAO getUserDAO();

    public abstract WishlistDAO getWishlistDAO();

    public abstract OrderDAO getOrderDAO();
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git

}
