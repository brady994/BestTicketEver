package siw.control;

import java.io.IOException;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import siw.model.Cart;
import siw.model.Event;
import siw.model.Order;
import siw.model.Review;
import siw.model.Ticket;
import siw.model.Type;
import siw.model.User;
import siw.model.Wishlist;
import siw.service.EventService;
import siw.service.UserService;

/**
 * Servlet implementation class homeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		String action = request.getParameter("action");

		action = (action == null) ? "" : action;
		String page;
		switch (action) {
		case "index":
			page = "index";
			break;
		case "reviews":
			User user1 = (User) session.getAttribute("user");
			UserService service1 = new UserService();
			Map<Integer, Review> reviews = service1.showReviews(user1);
			System.out.println(reviews.size());
			request.setAttribute("reviews", reviews);
			page = "review";
			break;
		case "login":
			page = "registration";
			break;
		case "search":
			page = "search";
			break;
		case "profile":
			page = "profile";
			break;
		case "wishlist": {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				page = "registration";
			} else {
				UserService service = new UserService();
				Map<Integer, Wishlist> wishlists = service.showWishlist(user.getId(), 0, 10);
				request.setAttribute("wishlists", wishlists);
				page = "wishlist";
			}
			break;
		}

		case "orders":
			User user = (User) session.getAttribute("user");
			if (user == null) {
				page = "registration";
			} else {
				UserService service = new UserService();
				Map<Integer, Order> orders = service.showOrders(user.getId(), 0, 10);
				request.setAttribute("orders", orders);
				page = "order";
			}
			break;
		case "registration":
			page = "registration";
			break;
		case "createevent":
			page = "createevent";
			break;
		case "singleevent":
			String id_event = request.getParameter("id");
			Event event = new Event();
			event.setId(Integer.parseInt(id_event));
			session.setAttribute("idevent", event);
			EventService service = new EventService();
			event = service.showSingleEvent(Integer.parseInt(id_event));
			service.getTicketsEvent(event);
			request.setAttribute("event", event);
			page = "ticket";
			break;
		case "cart":
			page = "cart";
			break;
		case "organizer": 
			page = "organizer";	
		break;
		default:
			page = "index";

		}
		request.setAttribute("page", "content/" + page + ".jsp");
		// this servlet has to forward only to the home.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
=======
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import siw.model.Order;
import siw.model.User;
import siw.model.Wishlist;
import siw.service.UserService;

/**
 * Servlet implementation class homeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession();
	String action = request.getParameter("action");

	action = (action == null) ? "" : action;
	String page;

	switch (action) {
	case "index":
	    page = "index";
	    break;
	case "reviews":
	    page = "review";
	    break;
	case "login":
	    page = "registration";
	    break;
	case "search":
	    page = "search";
	    break;
	case "profile":
	    page = "profile";
	    break;
	case "wishlist": {
	    User user = (User) session.getAttribute("user");
	    if (user == null) {
		page = "registration";
	    } else {
		UserService service = new UserService();
		Map<Integer, Wishlist> wishlists = service.showWishlist(user.getId(), 0, 10);
		request.setAttribute("wishlists", wishlists);
		page = "wishlist";
	    }
	    break;
	}

	case "orders":
	    User user = (User) session.getAttribute("user");
	    if (user == null) {
		page = "registration";
	    } else {
		UserService service = new UserService();
		Map<Integer, Order> orders = service.showOrders(user.getId(), 0, 10);
		request.setAttribute("orders", orders);
		page = "order";
	    }
	    break;
	case "registration":
	    page = "registration";
	    break;
	case "createevent":
	    page = "createevent";
	    break;
	case "singleevent":
	    page = "ticket";
	    break;
	case "cart":
	    page = "cart";
	    break;
	default:
	    page = "index";

	}

	request.setAttribute("page", "content/" + page + ".jsp");
	// this servlet has to forward only to the home.jsp
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/home.jsp");
	dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git

}
