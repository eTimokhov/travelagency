package by.bsuir.et.model.parser;

import by.bsuir.et.model.beans.Event;

import javax.xml.bind.DatatypeConverter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventStaxParser {
    private XMLStreamReader reader;
    private List<Event> events = new ArrayList<>();

    public List<Event> getDataFromFile(String filePath) throws DataSourceException {
        XMLInputFactory xmlInputFactory;
        xmlInputFactory = XMLInputFactory.newFactory();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            reader = xmlInputFactory.createXMLStreamReader(inputStream);
            parse();
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new DataSourceException(e.getMessage());
        }
        return events;
    }

    private void parse() throws XMLStreamException {
        String tag = null;
        Event event = null;
        int staxEvent;
        while (reader.hasNext()) {
            staxEvent = reader.next();
            switch (staxEvent) {
                case XMLEvent.START_ELEMENT:
                    try {
                        tag = reader.getLocalName();
                        if ("event".equals(tag)) {
                            event = new Event();
                            int id = 0;
                            try {
                                id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                            } catch (NumberFormatException e) {
                                throw new XMLStreamException(e);
                            }
                            event.setId(id);
                        }
                    } catch (Exception e) {
                        tag = null;
                    }
                    break;
                case XMLEvent.CHARACTERS:
                    String text = reader.getText();
                    if (text.trim().isEmpty())
                        break;
                    if ((event != null) && (tag != null)) {
                        switch (tag) {
                            case "name":
                                event.setName(text);
                                break;
                            case "address":
                                event.setAddress(text);
                                break;
                            case "date":
                                Calendar calendar = null;
                                try {
                                    calendar = DatatypeConverter.parseDateTime(text);
                                } catch (Exception e) {
                                    throw new XMLStreamException(e);
                                }
                                event.setDate(calendar.getTime());
                                break;
                        }
                    }
                    break;
                case XMLEvent.END_ELEMENT:
                    try {
                        if ("event".equals(reader.getLocalName())) {
                            events.add(event);
                            event = null;
                        }
                    } catch (Exception e) {
                    }
                    break;
            }
        }
    }
}
