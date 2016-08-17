package siw.Test;

import java.util.Date;

import com.google.gson.Gson;

import siw.model.Event;
import siw.model.EventCategory;
import siw.model.Type;
import siw.model.User;
import siw.persistence.DAOFactory;
import siw.persistence.dao.EventDAO;
import siw.persistence.dao.UserDAO;
import siw.persistence.dao.implementation.EventCategoryDaoJDBC;
import siw.persistence.dao.implementation.TicketCategoryDAOJDBC;

public class Test {
    public static void main(String[] args) {
	DAOFactory postgres = DAOFactory.getDaoFactory(DAOFactory.POSTGRES);

	// test user insert
	User user = new User();
	user.setUsername("Elyyyyyyyfgg");
	user.setPassword("1234");
	user.setName("Eliana");
	user.setSurname("Palermiti");
	user.setEmail("eliana.palermiti@gmail.com");
	user.setCoins((long) 0);
	user.setType(Type.Customer);
	Gson gson= new Gson();
	String sj= gson.toJson(user,User.class);
	User u= gson.fromJson(sj, User.class);
	System.out.println(u);
	UserDAO dao= postgres.getUserDAO();
	User o=dao.findById(5);
	dao.delete(o);
	
	
//
//	User user2 = new User();
//	user2.setName("Marco");
//	user2.setSurname("Mancuso");
//
//	Gson gson = new Gson();
//
//	String userjson = gson.toJson(user2, User.class);
//	User user3 = gson.fromJson(userjson, User.class);
//	UserDAO userdao = postgres.getUserDAO();
//	userdao.create(user3);
//
//	UserDAO dao = postgres.getUserDAO();
//	// FIND USER
//	User u = dao.findById(5);
	// crea categorie
//	EventCategory rock = new EventCategory();
//	rock.setName("Rock and Pop");
//	EventCategoryDaoJDBC category = postgres.getEventCategoryDAO();
//	
//	
//	
//
//	Event event = new Event();
//	event.setName("Rock in Roma");
//	event.setLocation("Circus Maximus , Rome , Italy");
//	event.setImage("Not Found");
//	event.setDescription("LoRem Ipso , Bello bellissimo ! ");
//	event.setOrganizer(u);
//	event.setSuspended(Boolean.FALSE);
//	event.setCategory(rock);
//	event.setDate(new Date(System.currentTimeMillis()));
//	EventDAO eventdao = postgres.getEventDAO();
//	eventdao.create(event);
//	System.out.println(event);

    }

}