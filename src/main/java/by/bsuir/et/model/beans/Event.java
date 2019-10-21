package by.bsuir.et.model.beans;

import java.util.Date;

public class Event {
    private String name;
    private String address;
    private Date date;

    public Event() {
    }

    public Event(String name, String address, Date date) {
        this.name = name;
        this.address = address;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
