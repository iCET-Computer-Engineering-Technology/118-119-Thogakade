package service.custom;

import model.Customer;
import service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService{
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
    Customer searchCustomerById(String id);
    List<Customer> getAll();
    List<String> getAllCustomerIDs();
}
