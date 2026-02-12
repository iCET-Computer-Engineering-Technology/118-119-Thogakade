package repository.custom.impl;

import model.Item;
import repository.custom.ItemRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public boolean create(Item item) throws SQLException {
        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?,?)",
                item.getCode(),
                item.getDescription(),
                item.getPackSize(),
                item.getUnitPrice(),
                item.getStock()
                );
    }

    @Override
    public boolean update(Item item) {
        return false;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }

    @Override
    public Item getById(String code) throws SQLException {

        ResultSet resultSet =CrudUtil.execute("SELECT * FROM item WHERE ItemCode = ?",code);

        resultSet.next();
        Item item = new Item();
        item.setCode(resultSet.getString(1));
        item.setDescription(resultSet.getString(2));
        item.setPackSize(resultSet.getString(3));
        item.setUnitPrice(resultSet.getDouble(4));

        return item;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item");
        ArrayList<Item> items = new ArrayList<>();

        while (resultSet.next()) {
            Item item = new Item();
            item.setCode(resultSet.getString(1));
            item.setDescription(resultSet.getString(2));
            item.setPackSize(resultSet.getString(3));
            item.setUnitPrice(resultSet.getDouble(4));
            items.add(item);
        }
        return items;
    }

    @Override
    public List<String> getItemCodes() throws SQLException {
        ArrayList<String> itemCodeList = new ArrayList<>();

        List<Item> all = getAll();

        all.forEach(item -> itemCodeList.add(item.getCode()));

        return itemCodeList;
    }
}
