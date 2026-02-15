package service.custom.impl;

import model.Order;
import repository.RepositoryFactory;
import repository.custom.OrderRepository;
import service.custom.OrderService;
import util.RepositoryType;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

    OrderRepository repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ORDER);

    @Override
    public boolean placeOrder(Order order) throws SQLException {
       return repository.placeOrder(order);
    }
}
