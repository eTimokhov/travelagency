package by.bsuir.et.model.beans;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Customer implements Serializable, Comparable<Customer> {

    @XmlAttribute
    private int id;

    private String firstName;
    private String lastName;

    private String address;
    private String phoneNumber;

    @XmlElementWrapper(name = "tours")
    @XmlElement(name = "tour")
    private List<Tour> tours;

    private static Comparator<Customer> customerComparator = getComparator();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String address, String phoneNumber, List<Tour> tours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.tours = tours;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tours=" + tours +
                '}';
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public int compareTo(Customer that) {
        return customerComparator.compare(this, that);
    }

    private static Comparator<Customer> getComparator() {
        return Comparator.comparing(Customer::getFirstName)
                .thenComparing(Customer::getLastName)
                .thenComparing(Customer::getAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(tours, customer.tours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, phoneNumber, tours);
    }
}
