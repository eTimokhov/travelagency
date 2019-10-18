package by.bsuir.et.model;

import java.util.Date;

public class Event {
    private String id;
    private String name;
    private String address;
    private Date date;

    public Event() {
    }

    public Event(String id, String name, String address, Date date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
