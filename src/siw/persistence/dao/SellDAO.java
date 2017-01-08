package siw.persistence.dao;

import java.sql.Date;
import java.util.Map;

import siw.model.Gift;
import siw.model.Order;
import siw.model.Sell;
import siw.model.Ticket;
import siw.model.User;

public interface SellDAO {
    public void create(Sell modelObject);

    public void delete(Sell s);

    public void update(Sell s);

}