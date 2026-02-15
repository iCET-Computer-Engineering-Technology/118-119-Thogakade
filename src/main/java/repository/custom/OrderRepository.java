package repository.custom;

import model.Order;
import repository.SuperRepository;

import java.sql.SQLException;

public interface OrderRepository extends SuperRepository {
    boolean placeOrder(Order order) throws SQLException;
}
