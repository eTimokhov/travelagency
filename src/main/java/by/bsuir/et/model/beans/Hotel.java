package by.bsuir.et.model.beans;

public class Hotel {

    private String name;
    private String country;
    private String address;

    public Hotel(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Hotel() {
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
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
