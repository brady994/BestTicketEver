package siw.persistence;

import siw.persistence.dao.EventCategoryDAO;
import siw.persistence.dao.EventDAO;
import siw.persistence.dao.OrderDAO;
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

}
