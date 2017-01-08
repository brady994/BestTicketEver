package siw.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

import siw.model.Event;
import siw.model.EventCategory;
import siw.service.SearchService;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BufferedReader br = new BufferedReader(request.getReader());
		String json = null;
		JsonObject result = new JsonObject();
		if (br != null) {
			json = br.readLine();
		}
		String action = request.getParameter("action");
		Map<Integer, Event> resultSearch = new HashMap<Integer, Event>();

		if (action != null) {
			SearchService searchservice = new SearchService();
			resultSearch = searchservice.getEventFindByCategory(json, result);

		} else {

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(json);

			JsonObject object = element.getAsJsonObject();
			String filter = object.get("filter").getAsString();

			SearchService searchServices = new SearchService();
			if (filter.equals("")) {

				resultSearch = searchServices.getEventFindByName(json, result);
			} else {
				switch (filter) {
				case "Date": {
					SearchService searchService = new SearchService();
					resultSearch = searchService.getEventFindByDate(json, result);
					break;
				}
				case "Price": {
					SearchService searchService = new SearchService();
					resultSearch = searchService.getEventFindByPrice(json, result);
					break;
				}
				case "Artist": {
					SearchService searchService = new SearchService();
					resultSearch = searchService.getEventFindByGuest(json, result);
					break;
				}
				case "Place": {
					SearchService searchService = new SearchService();
					resultSearch = searchService.getEventFindByLocation(json, result);
					break;
				}
				default:
					break;

				}
			}
		}
		session.setAttribute("event", resultSearch);
		response.getWriter().write(result.toString());
	}
}
