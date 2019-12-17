package by.bsuir.et.model.parser;

import by.bsuir.et.model.beans.Hotel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class HotelSaxParser extends DefaultHandler {
    private List<Hotel> hotels = new ArrayList<>();
    private Hotel hotel;
    private StringBuilder input;

    public List<Hotel> getHotels() {
        return hotels;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        input = new StringBuilder();
        if ((qName.equals("hotel"))) {
            hotel = new Hotel();
            hotel.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        input.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            switch (qName) {
                case "name":
                    hotel.setName(input.toString());
                    break;
                case "country":
                    hotel.setCountry(input.toString());
                    break;
                case "address":
                    hotel.setAddress(input.toString());
                    break;
                case "hotel":
                    hotels.add(hotel);
                    hotel = null;
                    break;
            }
        } catch (Exception e) {
        }
    }
}
