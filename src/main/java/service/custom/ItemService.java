package service.custom;

import model.Item;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ItemService extends SuperService {
    boolean addItem(Item item) throws SQLException;
    boolean updateItem(Item item);
    boolean deleteItem(Item item);
    List<Item> getAllItems() throws SQLException;
    List<String> getItemCodes() throws SQLException;
    Item getItemByCode(String code) throws SQLException;
}
