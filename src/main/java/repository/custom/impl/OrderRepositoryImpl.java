package repository.custom.impl;

import db.DbConnection;
import model.Order;
import repository.custom.ItemRepository;
import repository.custom.OrderDetailRepository;
import repository.custom.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderRepositoryImpl implements OrderRepository {
    OrderDetailRepository detailRepository = new OrderDetailRepositoryImpl();
    ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    public boolean placeOrder(Order order) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement psTM = connection.prepareStatement("INSERT INTO orders values(?,?,?)");
            psTM.setString(1, order.getOrderId());
            psTM.setObject(2, order.getOrderDate());
            psTM.setString(3, order.getCustomerId());
            boolean isOrderInsert = psTM.executeUpdate() > 0;
            if (isOrderInsert) {
                boolean isOrderDetailsInsert = detailRepository.insertOrderDetail(order.getOrderDetailsList());
                if (isOrderDetailsInsert){
                    boolean isStockUpdate = itemRepository.updateStock(order.getOrderDetailsList());
                    if(isStockUpdate){
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }

    }
}
