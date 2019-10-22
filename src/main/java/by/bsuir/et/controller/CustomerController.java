package by.bsuir.et.controller;

import by.bsuir.et.model.beans.Customer;

import java.util.List;

public interface CustomerController {
    Customer getCustomer(String id);
    List<Customer> getCustomersList();
    List<Customer> getSortedCustomersList();
    void saveCustomer(Customer customer);
    boolean deleteCustomer(String id);
}
