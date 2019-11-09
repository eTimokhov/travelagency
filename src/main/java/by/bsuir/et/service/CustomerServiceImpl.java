package by.bsuir.et.service;

import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.dao.CustomerDao;
import by.bsuir.et.model.dao.XmlCustomerDao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;
    //hardcoded path
    private static final String DATA_FILE_PATH = "C:\\Users\\Evgeny\\IdeaProjects\\travelagency\\data.xml";

    public CustomerServiceImpl() {
        customerDao = new XmlCustomerDao(DATA_FILE_PATH);
    }

    @Override
    public Customer getCustomer(String id) {
        return customerDao.getCustomer(id);
    }

    @Override
    public List<Customer> getCustomersList() {
        return customerDao.getCustomersList();
    }

    @Override
    public List<Customer> getSortedCustomersList() {
        List<Customer> customers = getCustomersList();
        Collections.sort(customers);
        return customers;
    }

    @Override
    public List<Customer> getSortedCustomersList(Comparator<Customer> comparator) {
        List<Customer> customers = getCustomersList();
        customers.sort(comparator);
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(String id) {
        return customerDao.deleteCustomer(id);
    }
}
