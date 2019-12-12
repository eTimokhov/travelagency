package by.bsuir.et.model.dao;

import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.model.beans.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class XmlAgencyDao implements AgencyDao {

    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;
    private Unmarshaller jaxbUnmarshaller;
    private File dataFile;

    private Agency agency;

    public XmlAgencyDao(String xmlDataFilePath) {
        try {
            jaxbContext = JAXBContext.newInstance(Agency.class, Customer.class, Event.class, Flight.class, Hotel.class, Tour.class);
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
    public Customer getCustomer(int id) {
        updateCustomersList();
        return agency.getCustomers().stream()
                .filter(c -> id == c.getId())
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Customer> getCustomersList() {
        updateCustomersList();
        return agency.getCustomers();
    }

    @Override
    public void saveCustomer(Customer customer) {
        if (customer.getId() != 0 && customerWithIdExists(customer.getId())) {
            updateList(customer);
        } else {
            setGeneratedId(customer);
            addToList(customer);
        }
        updateFile();
    }

    @Override
    public boolean deleteCustomer(int id) {
        boolean removed = false;
        if (id != 0) {
            removed = agency.getCustomers().removeIf(c -> id == c.getId());
        }
        updateFile();
        return removed;
    }

    private void updateCustomersList() {
        try {
            agency = (Agency) jaxbUnmarshaller.unmarshal(dataFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (agency == null) {
            agency = new Agency();
            agency.setCustomers(new ArrayList<>());
        }
    }

    private void updateFile() {
        try {
            jaxbMarshaller.marshal(agency, dataFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private boolean customerWithIdExists(int id) {
        return agency.getCustomers().stream()
                .anyMatch(c -> id == c.getId());
    }

    private void addToList(Customer customer) {
        setGeneratedId(customer);
        agency.getCustomers().add(customer);
    }

    private void updateList(Customer customer) {
        agency.getCustomers().removeIf(c -> c.getId() == customer.getId());
        agency.getCustomers().add(customer);
    }

    private void setGeneratedId(Customer customer) {
        int newId = generateId(customer);
        customer.setId(newId);
    }

    private int generateId(Customer customer) {
        if (agency == null) {
            throw new NullPointerException("Agency is null");
        }
        if (agency.getCustomers().isEmpty()) {
            return 1;
        }
        List<Integer> existingIds = agency.getCustomers().stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (!existingIds.contains(i)) {
                return i;
            }
        }
        throw new IllegalStateException("No possible id exists");
    }
}
