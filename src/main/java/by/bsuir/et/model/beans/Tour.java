package by.bsuir.et.model.beans;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tour implements Serializable {
    @XmlAttribute
    private int id;

    private double price;
    private String country;
    private Date startDate;
    private Date endDate;
    private int personAmount;

    @XmlElement(name = "hotel")
    private Hotel hotel;

    @XmlElement(name = "outboundFlight")
    private Flight outboundFlight;

    @XmlElement(name = "returnFlight")
    private Flight returnFlight;

    private boolean hot;

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    private List<Event> events;

    public Tour() {
    }

    public Tour(double price, String country, Date startDate, Date endDate, int personAmount, Hotel hotel, Flight outboundFlight, Flight returnFlight, boolean hot, List<Event> events) {
        this.price = price;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.personAmount = personAmount;
        this.hotel = hotel;
        this.outboundFlight = outboundFlight;
        this.returnFlight = returnFlight;
        this.hot = hot;
        this.events = events;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", country='" + country + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", personAmount=" + personAmount +
                ", hotel=" + hotel +
                ", outboundFlight=" + outboundFlight +
                ", returnFlight=" + returnFlight +
                ", hot=" + hot +
                ", events=" + events +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPersonAmount() {
        return personAmount;
    }

    public void setPersonAmount(int personAmount) {
        this.personAmount = personAmount;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
