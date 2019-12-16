package by.bsuir.et.model.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Hotel implements Serializable {
    @XmlAttribute
    private int id;

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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(country, hotel.country) &&
                Objects.equals(address, hotel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, address);
    }
}
