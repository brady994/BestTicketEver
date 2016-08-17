package siw.persistence.dao;

import java.util.Map;

import siw.model.Order;
import siw.model.User;

public interface OrderDAO {

<<<<<<< HEAD
    public Integer create(Order modelObject);
=======
    public void create(Order modelObject);
>>>>>>> branch 'master' of https://github.com/brady994/TicketsBest.git

    public void delete(Order o);

    public void update(Order o);

    public Map<Integer, Order> findByUser(Integer id);

    public Map<Integer, Order> findByUser(Integer id, Integer offset, Integer limit);

    public Order findById(Integer id);

    public Integer findLastId(User u);

}
