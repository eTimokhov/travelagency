package by.bsuir.et.model.xmldao;

import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.dao.CustomerDao;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class XmlCustomerDao implements CustomerDao {

    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;
    private Unmarshaller jaxbUnmarshaller;
    private File dataFile;

    private static final String DATA_FILE_PATH = "C:\\Users\\Evgeny\\IdeaProjects\\travelagency\\src\\main\\java\\by\\bsuir\\et\\model\\xmldao\\data.xml";

    private CustomersList customersList;

    public XmlCustomerDao(String xmlDataFilePath) {
        try {
            jaxbContext = JAXBContext.newInstance(CustomersList.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            System.err.println("Error with XmlCustomerDao initialization");
            throw new ExceptionInInitializerError(e);
        }
        dataFile = new File(xmlDataFilePath);

        updateCustomersList();
    }

    @Override
    public Customer getCustomer(String id) {
        updateCustomersList();
        return customersList.getCustomers().stream()
                .filter(c -> id.equals(c.getId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Customer> getCustomersList() {
        updateCustomersList();
        return customersList.getCustomers();
    }

    @Override
    public void saveCustomer(Customer customer) {
        if (customer.getId() != null && customerWithIdExists(customer.getId())) {
            updateList(customer);
        } else {
            setGeneratedId(customer);
            addToList(customer);
        }
        updateFile();
    }

    @Override
    public boolean deleteCustomer(String id) {
        boolean removed = false;
        if (id != null) {
            removed = customersList.getCustomers().removeIf(c -> id.equals(c.getId()));
        }
        updateFile();
        return removed;
    }

    private void updateCustomersList() {
        try {
            customersList = (CustomersList) jaxbUnmarshaller.unmarshal(dataFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (customersList == null) {
            customersList = new CustomersList();
            customersList.setCustomers(new ArrayList<>());
        }
    }

    private void updateFile() {
        try {
            jaxbMarshaller.marshal(customersList, dataFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private boolean customerWithIdExists(String id) {
        return customersList.getCustomers().stream()
                .anyMatch(c -> id.equals(c.getId()));
    }

    private void addToList(Customer customer) {
        setGeneratedId(customer);
        customersList.getCustomers().add(customer);
    }

    private void updateList(Customer customer) {
        customersList.getCustomers().removeIf(c -> c.getId().equals(customer.getId()));
        customersList.getCustomers().add(customer);
    }

    private void setGeneratedId(Customer customer) {
        String newId = generateId(customer);
        customer.setId(newId);
    }

    private String generateId(Customer customer) {
        if (customersList == null) {
            return "0";
        }
        int maxNumericId = customersList.getCustomers().stream()
                .filter(c -> isInteger(c.getId()))
                .mapToInt(c -> Integer.parseInt(c.getId()))
                .max()
                .orElse(0);
        if (Integer.MAX_VALUE != maxNumericId) {
            return String.valueOf(maxNumericId + 1);
        } else {
            return UUID.randomUUID().toString();
        }
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(NullPointerException | NumberFormatException e) {
            return false;
        }
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class CustomersList {
        @XmlElementWrapper(name = "customers")
        @XmlElement(name = "customer")
        private List<Customer> customers;

        private List<Customer> getCustomers() {
            return customers;
        }

        private void setCustomers(List<Customer> customers) {
            this.customers = customers;
        }
    }
}
