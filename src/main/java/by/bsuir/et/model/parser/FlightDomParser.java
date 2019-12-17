package by.bsuir.et.model.parser;

import by.bsuir.et.model.beans.Flight;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FlightDomParser {
    public List<Flight> getDataFromFile(String filePath) throws DataSourceException {
        List<Flight> flights = new ArrayList<>();
        flights.addAll(getFlightsWithTag(filePath,"outboundFlight"));
        flights.addAll(getFlightsWithTag(filePath,"returnFlight"));
        return flights;
    }

    private List<Flight> getFlightsWithTag(String filePath, String tag) throws DataSourceException {
        List<Flight> flights;
        File file = new File(filePath);
        try {
            Document document = buildDocument(file);
            NodeList nodeList = document.getDocumentElement().getElementsByTagName(tag);
            flights = new ArrayList<>(nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() != Node.TEXT_NODE) {
                    flights.add(getFlightFromNode(nodeList.item(i)));
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new DataSourceException("File " + filePath + " not found");
        }
        return flights;
    }

    private Flight getFlightFromNode(Node orderNode) throws DataSourceException {
        Flight flight = new Flight();
        flight.setId(Integer.parseInt(orderNode.getAttributes().getNamedItem("id").getNodeValue()));
        NodeList orderProps = orderNode.getChildNodes();
        String tag = null;
        for (int j = 0; j < orderProps.getLength(); j++) {
            if ((orderProps.item(j).getNodeType() != Node.TEXT_NODE)) {
                try {
                    tag = orderProps.item(j).getNodeName();
                    switch (tag) {
                        case "flightClass":
                            flight.setFlightClass(Flight.FlightClass.valueOf(orderProps.item(j).getTextContent()));
                            break;
                        case "seats":
                            flight.setSeats(Integer.parseInt(orderProps.item(j).getTextContent()));
                            break;
                        case "departureAirport":
                            flight.setDepartureAirport(orderProps.item(j).getTextContent());
                            break;
                        case "arrivalAirport":
                            flight.setArrivalAirport(orderProps.item(j).getTextContent());
                            break;
                        case "departureTime":
                            String departureTimeText = orderProps.item(j).getTextContent();
                            Calendar departureTimeCalendar = null;
                            try {
                                departureTimeCalendar = DatatypeConverter.parseDateTime(departureTimeText);
                            } catch (Exception e) {
                                throw new DataSourceException(e);
                            }
                            flight.setDepartureTime(departureTimeCalendar.getTime());
                            break;
                        case "arrivalTime":
                            String arrivalTimeText = orderProps.item(j).getTextContent();
                            Calendar arrivalTimeCalendar = null;
                            try {
                                arrivalTimeCalendar = DatatypeConverter.parseDateTime(arrivalTimeText);
                            } catch (Exception e) {
                                throw new DataSourceException(e);
                            }
                            flight.setArrivalTime(arrivalTimeCalendar.getTime());
                            break;
                    }
                } catch (DOMException e) {
                    throw new DataSourceException(e);
                }
            }
        }
        return flight;
    }

    private Document buildDocument(File file) throws IOException, ParserConfigurationException,
            org.xml.sax.SAXException {
        Document document;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.parse(file);
        document.normalize();
        return document;
    }
}
