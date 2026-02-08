package repository;

import model.Customer;

import java.util.List;

public interface CrudRepository<T,ID> extends SuperRepository{
    boolean create(T t);
    boolean update(T t);
    boolean deleteById(ID id);
    T getById(ID id);
    List<T> getAll();
}
