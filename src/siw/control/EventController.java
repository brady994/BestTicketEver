package siw.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import siw.model.Event;
import siw.model.Organizer;
import siw.model.Review;
import siw.model.Ticket;
import siw.model.User;
import siw.service.EventService;

/**
 * Servlet implementation class EventController
 */
@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String json = ""; // parse request in json format
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		if (br != null) {
			json = br.readLine();
		}
		String action = request.getParameter("action");
		JsonObject result = new JsonObject();
		switch (action) {
		case "CREATE": {
			Organizer organizer = (Organizer) session.getAttribute("organizer");
			EventService eventService = new EventService(json);
			result = eventService.createEvent(organizer, json);
			break;
		}
		case "UPDATE": {
			EventService eventService1 = new EventService(json);
			result = eventService1.updateEvent();
			break;
		}
		case "DELETE":
		{
			EventService eventservice = new EventService(json);
			result=eventservice.deleteEvent();
			break;
		}
		case "SUSPEND":
		{
			EventService eventservice = new EventService(json);
			result=eventservice.suspendEvent();
			break;
		}
		case "showCategory": {
			EventService eventService2 = new EventService(json);
			result = eventService2.showCategory();
			break;
		}
		case "showTicketCategory": {
			EventService service = new EventService(json);
			result = service.showTicketCategory();
			break;
		}
		case "showReviews": {
			Event event = (Event) session.getAttribute("idevent");
			EventService service = new EventService();
			result = service.showReview(event);
			break;
		}
		case "addReview": {
			Event event = (Event) session.getAttribute("idevent");
			User user = (User) session.getAttribute("user");
			EventService service = new EventService();
			result = service.addReview(user, event, json);
			break;
		}
		case "showTicket": {
			EventService eventservice = new EventService(json);
			result = eventservice.showTicketOrganizer();			
			break;

		}
		case "showGuest":{
			EventService eventService= new EventService(json);
			result=eventService.showGuest();
			break;
			
		}
		case "deleteguest":
		{
			EventService eventService= new EventService();		
			result=eventService.deleteGuest(json);
			break;
		}
		case "editguest":{
			EventService eventservice = new EventService();
			result = eventservice.editGuest(json);
			break;
		}
		case "updatePrice":
		{
			EventService eventservice= new EventService();
			result=eventservice.updatePriceTicketCategory(json);
			break;
		}

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
		doGet(request, response);
	}

}
