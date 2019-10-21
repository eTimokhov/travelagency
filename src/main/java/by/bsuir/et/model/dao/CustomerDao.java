package by.bsuir.et.model.dao;

import by.bsuir.et.model.beans.Customer;

import java.util.List;

public interface CustomerDao {
    Customer getCustomer(String id);
    List<Customer> getCustomersList();
    void saveCustomer(Customer customer);
    boolean deleteCustomer(String id);
}
