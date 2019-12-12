package by.bsuir.et.model.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Flight implements Serializable {
    @XmlAttribute
    private int id;

    private FlightClass flightClass;
    private int seats;
    private Date departureTime;
    private Date arrivalTime;
    private String departureAirport;
    private String arrivalAirport;

    public Flight() {
    }

    public Flight(FlightClass flightClass, int seats, Date departureTime, Date arrivalTime, String departureAirport, String arrivalAirport) {
        this.flightClass = flightClass;
        this.seats = seats;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", flightClass=" + flightClass +
                ", seats=" + seats +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
