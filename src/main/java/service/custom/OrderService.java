package service.custom;

import model.Order;
import service.SuperService;

import java.sql.SQLException;

public interface OrderService extends SuperService {
    boolean placeOrder(Order order) throws SQLException;
}
