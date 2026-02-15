package repository.custom;

import model.Item;
import model.OrderDetails;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String> {
    List<String> getItemCodes() throws SQLException;
    boolean updateStock(List<OrderDetails> orderDetailsList) throws SQLException;

}
