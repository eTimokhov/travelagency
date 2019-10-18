package by.bsuir.et.model;

import java.util.Date;

public class Flight {
    private String id;

    private FlightClass flightClass;
    private int seats;
    private Date departureTime;
    private Date arrivalTime;
    private String departureAirport;
    private String arrivalAirport;

    public Flight() {
    }

    public Flight(String id, FlightClass flightClass, int seats, Date departureTime, Date arrivalTime, String departureAirport, String arrivalAirport) {
        this.id = id;
        this.flightClass = flightClass;
        this.seats = seats;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public enum FlightClass {
        ECONOMY, BUSINESS
    }
}
