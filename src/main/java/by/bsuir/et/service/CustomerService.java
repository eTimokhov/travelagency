package by.bsuir.et.service;

import by.bsuir.et.model.beans.Customer;

import java.util.Comparator;
import java.util.List;

public interface CustomerService {
    Customer getCustomer(String id);
    List<Customer> getCustomersList();
    List<Customer> getSortedCustomersList();
    List<Customer> getSortedCustomersList(Comparator<Customer> comparator);
    void saveCustomer(Customer customer);
    boolean deleteCustomer(String id);
}
