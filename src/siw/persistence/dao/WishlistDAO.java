package siw.persistence.dao;

import java.util.Map;

import siw.model.Event;
import siw.model.User;
import siw.model.Wishlist;

public interface WishlistDAO {
    public int create(Wishlist modelObject);

    public void delete(Wishlist wishlist);

    public void update(Wishlist wishlist);

    public Wishlist findById(Integer id);

    public Integer findLastId(User u);

    public Map<Integer, Wishlist> findByTitle(String title);

    public Map<Integer, Wishlist> findByTitle(String title, Integer offset, Integer limit);

    public Map<Integer, Wishlist> findByUser(User name);

    public Map<Integer, Wishlist> findByUser(Integer id, Integer offset, Integer limit);

    public boolean updateEvent(Wishlist wishlist, Event event);
}
