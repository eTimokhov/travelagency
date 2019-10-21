package by.bsuir.et.controller;

import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.dao.CustomerDao;
import by.bsuir.et.model.xmldao.XmlCustomerDao;

import java.util.List;

public class CustomerControllerImpl implements CustomerController {
    private CustomerDao customerDao;
    //hardcoded path
    private static final String DATA_FILE_PATH = "C:\\Users\\Evgeny\\IdeaProjects\\travelagency\\src\\main\\java\\by\\bsuir\\et\\model\\xmldao\\data.xml";

    public CustomerControllerImpl() {
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
    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(String id) {
        return customerDao.deleteCustomer(id);
    }
}
