package siw.persistence;

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

	public abstract OrganizerDAO getOrganizerDao();

	public abstract TicketCategoryDAO getTicketCategoryDao();

	public abstract GuestDAO getGuestDAO();
	

}
