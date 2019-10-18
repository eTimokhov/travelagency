package by.bsuir.et.model.xmldao;

import by.bsuir.et.model.beans.Tour;
import by.bsuir.et.model.dao.TourDao;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.List;

public class XmlTourDao implements TourDao {

    private final JAXBContext jaxbContext;
    private final Marshaller jaxbMarshaller;
    private final Unmarshaller jaxbUnmarshaller;
    private final File dataFile;

    private static final String DATA_FILE_PATH = "data.xml";

    private Tours tours;

    public XmlTourDao() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(Tour.class);
        jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        dataFile = new File(DATA_FILE_PATH);
    }

    @Override
    public Tour getTour(String id) {
        updateToursList();
        return tours.getTours().stream()
                .filter(t -> id.equals(t.getId()))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Tour> getTours() {
        updateToursList();
        return tours.getTours();
    }

    @Override
    public void saveTour(Tour tour) {
        if (tour.getId() == null || tourWithIdExists(tour.getId())) {
            update(tour);
        } else {
            add(tour);
        }
        updateFile();
    }

    @Override
    public void deleteTour(String id) {

    }

    private void updateToursList() {
        try {
            tours = (Tours) jaxbUnmarshaller.unmarshal(dataFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void updateFile() {
        try {
            jaxbMarshaller.marshal(tours, dataFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private boolean tourWithIdExists(String id) {
        return tours.getTours().stream()
                .anyMatch(t -> id.equals(t.getId()));
    }

    private void add(Tour tour) {

    }

    private void update(Tour tour) {
        String updatedTourId
    }
}
