package siw.control;
<<<<<<< HEAD
import java.lang.reflect.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import siw.model.Cart;
import siw.model.User;
import siw.service.CartService;
import siw.service.UserService;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// create the session to handle the cart
	HttpSession session = request.getSession();
	Cart cart = (Cart) session.getAttribute("cart");
	User user = (User) session.getAttribute("user");

	BufferedReader br = new BufferedReader(request.getReader());
	String json = null;
	if (br != null) {
	    json = br.readLine();
	}
	String action = request.getParameter("action");
	JsonObject result = new JsonObject();
	switch (action) {
	case "add":
		CartService service= new CartService();
		
		cart=service.addItem(json, cart, result);
		session.setAttribute("cart", cart);
	    break;
	case "remove":
	    new CartService().removeItem(json, cart, result);
	    session.setAttribute("cart", cart);
	    break;
	case "buy":
		System.out.println(json);
	    new CartService().buy(cart, result,user,json);
	    session.setAttribute("cart", cart);
	    break;
	case "clear":
	    new CartService().clear(cart, result);
	    session.setAttribute("cart", cart);
	    break;
	case "view":
	    RequestDispatcher dispatcher = request.getRequestDispatcher("home?action=cart");
	    dispatcher.forward(request, response);
	    break;
	default:
	    break;
	}
	response.getWriter().write(result.toString());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
=======

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import siw.model.Cart;
import siw.service.CartService;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// create the session to handle the cart
	HttpSession session = request.getSession();
	Cart cart = (Cart) session.getAttribute("cart");
	if (cart == null) {
	    cart = new Cart();
	    session.setAttribute("cart", cart);
	}
	BufferedReader br = new BufferedReader(request.getReader());
	String json = null;
	if (br != null) {
	    json = br.readLine();
	}
	String action = request.getParameter("action");
	JsonObject result = new JsonObject();
	switch (action) {
	case "add":
	    new CartService().addItem(json, cart, result);
	    break;
	case "remove":
	    new CartService().removeItem(json, cart, result);
	    session.setAttribute("cart", cart);
	    break;
	case "buy":
	    new CartService().buy(cart, result);
	    session.setAttribute("cart", cart);
	    break;
	case "clear":
	    new CartService().clear(cart, result);
	    session.setAttribute("cart", cart);
	    break;
	case "view":
	    RequestDispatcher dispatcher = request.getRequestDispatcher("home?action=cart");
	    dispatcher.forward(request, response);
	    break;
	default:
	    break;
	}
	response.getWriter().write(result.toString());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git
	doGet(request, response);
    }

}
