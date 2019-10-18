package by.bsuir.et.model;

import java.util.Date;
import java.util.List;

public class Tour {
    private String id;

    private double price;
    private String country;
    private Date startDate;
    private Date endDate;
    private int personAmount;
    private Hotel hotel;
    private Flight outboundFlight;
    private Flight returnFlight;
    private List<Event> events;

    public Tour() {
    }

    public Tour(String id, double price, String country, Date startDate, Date endDate, int personAmount, Hotel hotel, Flight outboundFlight, Flight returnFlight, List<Event> events) {
        this.id = id;
        this.price = price;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.personAmount = personAmount;
        this.hotel = hotel;
        this.outboundFlight = outboundFlight;
        this.returnFlight = returnFlight;
        this.events = events;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
