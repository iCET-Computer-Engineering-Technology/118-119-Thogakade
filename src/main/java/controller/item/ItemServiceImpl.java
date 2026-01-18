package controller.item;

import db.DbConnection;
import javafx.collections.FXCollections;
import model.Item;
import model.tm.ItemTM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService{
    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }

    @Override
    public Item searchById(String id) {
        return null;
    }

    @Override
    public List<Item> getAll() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROm item");

            ArrayList<Item> itemArrayList = new ArrayList<>();

            while (resultSet.next()){
                Item itemTM = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );
                itemArrayList.add(itemTM);
            }

            return  itemArrayList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
