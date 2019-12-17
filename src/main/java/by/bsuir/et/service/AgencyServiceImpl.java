package by.bsuir.et.service;

import by.bsuir.et.model.beans.*;
import by.bsuir.et.model.dao.AgencyDao;
import by.bsuir.et.model.dao.XmlAgencyDao;
import by.bsuir.et.model.parser.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AgencyServiceImpl implements AgencyService {
    private AgencyDao agencyDao;
    private TourService tourService = new TourService();
    //hardcoded path
    private static final String DATA_FILE_PATH = "src/main/resources/data.xml";

    public AgencyServiceImpl() {
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
    public List<Tour> getToursList() {
        return tourService.getToursList(getCustomersList());
    }

    @Override
    public Tour getTour(int id) {
        return tourService.getTour(getCustomersList(), id);
    }

    /**
     * using Sax parser
     */
    @Override
    public List<Hotel> getHotelsList() throws DataSourceException {
        HotelSaxParser hotelSaxParser = new HotelSaxParser();
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(hotelSaxParser);
            xmlReader.parse(new InputSource(DATA_FILE_PATH));
            return hotelSaxParser.getHotels();
        } catch (SAXException | IOException e) {
            throw new DataSourceException(e);
        }

    }

    @Override
    public Hotel getHotel(int id) throws DataSourceException {
        List<Hotel> hotels = getHotelsList();
        return hotels.stream()
                .filter(h -> h.getId() == id)
                .findAny()
                .orElse(null);
    }

    /**
     * using Stax parser
     */
    @Override
    public List<Event> getEventsList() throws DataSourceException {
        EventStaxParser eventStaxParser = new EventStaxParser();
        return eventStaxParser.getDataFromFile(DATA_FILE_PATH);
    }

    @Override
    public Event getEvent(int id) throws DataSourceException {
        List<Event> events = getEventsList();
        return events.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElse(null);
    }
    /**
     * using DOM parser
     */
    @Override
    public List<Flight> getFlightsList() throws DataSourceException {
        FlightDomParser flightDomParser = new FlightDomParser();
        return flightDomParser.getDataFromFile(DATA_FILE_PATH);
    }

    @Override
    public Flight getFlight(int id) throws DataSourceException {
        List<Flight> flights = getFlightsList();
        return flights.stream()
                .filter(f -> f.getId() == id)
                .findAny()
                .orElse(null);
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
