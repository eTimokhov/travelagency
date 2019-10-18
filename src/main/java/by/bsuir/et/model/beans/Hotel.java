package by.bsuir.et.model;

public class Hotel {

    private String id;

    private String name;
    private String country;
    private String address;

    public Hotel(String id, String name, String country, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Hotel() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
