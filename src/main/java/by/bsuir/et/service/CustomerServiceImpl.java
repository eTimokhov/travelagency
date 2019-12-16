package by.bsuir.et.service;

import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.dao.AgencyDao;
import by.bsuir.et.model.dao.XmlAgencyDao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private AgencyDao agencyDao;
    //hardcoded path
    private static final String DATA_FILE_PATH = "src/main/resources/data.xml";

    public CustomerServiceImpl() {
        agencyDao = new XmlAgencyDao(DATA_FILE_PATH);
    }

    @Override
    public Customer getCustomer(int id) {
        return agencyDao.getCustomer(id);
    }

    @Override
    public List<Customer> getCustomersList() {
        return agencyDao.getCustomersList();
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
        agencyDao.saveCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        return agencyDao.deleteCustomer(id);
    }
}
