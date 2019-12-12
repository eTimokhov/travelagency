package by.bsuir.et.model.dao;

import by.bsuir.et.model.beans.Customer;

import java.util.List;

public interface AgencyDao {
    Customer getCustomer(int id);
    List<Customer> getCustomersList();
    void saveCustomer(Customer customer);
    boolean deleteCustomer(int id);
}
