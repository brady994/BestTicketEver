package siw.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import siw.model.Event;
import siw.model.Review;
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
	case "CREATE":{
	    EventService eventService = new EventService(json);
	    if (eventService.createEvent()) {
		response.getWriter().append("GOOD JOB");
		System.out.println("Event created!");
	    }
	    break;
	}
	case "UPDATE":{
		EventService eventService1 =new EventService(json);
		response.getWriter().append("GOOD JOB");
		eventService1.updateEvent(action);
	    break;
	}
	case "SHOW":{
		EventService eventService2 = new EventService(json);
		response.getWriter().append("GOOD JOB");
		eventService2.showEvent(json);
	    break;
	}
	case "showReviews":{
		Event event=(Event) session.getAttribute("idevent");
		EventService service = new EventService();
		result=service.showReview(event);
		break;
	}
	case "addReview":{
		Event event=(Event) session.getAttribute("idevent");
		User user = (User) session.getAttribute("user");
		EventService service = new EventService();
		result = service.addReview(user, event, json);
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
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}
