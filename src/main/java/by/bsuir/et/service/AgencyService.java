package by.bsuir.et.service;

import by.bsuir.et.model.beans.*;
import by.bsuir.et.model.parser.DataSourceException;

import java.util.Comparator;
import java.util.List;

public interface AgencyService {
    Customer getCustomer(int id);
    List<Customer> getCustomersList();
    List<Tour> getToursList();
    Tour getTour(int id);
    List<Hotel> getHotelsList() throws DataSourceException;
    Hotel getHotel(int id) throws DataSourceException;
    List<Event> getEventsList() throws DataSourceException;
    Event getEvent(int id) throws DataSourceException;
    List<Flight> getFlightsList() throws DataSourceException;
    Flight getFlight(int id) throws DataSourceException;
    List<Customer> getSortedCustomersList();
    List<Customer> getSortedCustomersList(Comparator<Customer> comparator);
    void saveCustomer(Customer customer);
    boolean deleteCustomer(int id);
}
