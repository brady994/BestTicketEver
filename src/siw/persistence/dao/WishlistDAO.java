package siw.persistence.dao;

import java.util.Map;

import siw.model.Event;
import siw.model.Ticket;
import siw.model.User;
import siw.model.Wishlist;

public interface WishlistDAO {
    public int create(Wishlist modelObject);

    public int delete(Wishlist wishlist);

    public void update(Wishlist wishlist);

    public Wishlist findById(Integer id);

    public Map<Integer, Wishlist> findByUser(Integer id, Integer offset, Integer limit);

    public boolean updateWishTicket(Wishlist wishlist, Ticket ticket);
}
