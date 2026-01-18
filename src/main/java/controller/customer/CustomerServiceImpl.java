package controller.customer;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import model.tm.CustomerTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean addCustomer(Customer customer) {

        try {

            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");

            psTm.setString(1, customer.getId());
            psTm.setString(2, customer.getTitle());
            psTm.setString(3, customer.getName());
            psTm.setObject(4, customer.getDobValue());
            psTm.setDouble(5, customer.getSalary());
            psTm.setString(6, customer.getAddress());
            psTm.setString(7, customer.getCity());
            psTm.setString(8, customer.getProvince());
            psTm.setString(9, customer.getPostalCode());

            return psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement("DELETE FROM customer WHERE CustID=?");
            psTm.setString(1,id);

           return psTm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomerById(String id) {
        try {

            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement psTM = connection.prepareStatement("SELECT * FROM customer WHERE CustID = ?");

            psTM.setString(1,id);
            ResultSet resultSet = psTM.executeQuery();

            resultSet.next();

            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        try {

            Connection connection = DbConnection.getInstance().getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROm customer");

            ArrayList<Customer> customerArrayList = new ArrayList<>();

            while (resultSet.next()){
                customerArrayList.add(
                        new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDate(4).toLocalDate(),
                                resultSet.getDouble(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9)
                        )
                );
            }

            return customerArrayList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
