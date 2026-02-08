package repository.custom.impl;

import model.Item;
import repository.custom.ItemRepository;

import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public boolean create(Item item) {
        return false;
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
    public Item getById(String s) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return List.of();
    }
}
