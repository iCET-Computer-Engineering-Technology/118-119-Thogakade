package repository.custom;

import model.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailRepository {
    boolean insertOrderDetail(List<OrderDetails> orderDetailsList) throws SQLException;
}
