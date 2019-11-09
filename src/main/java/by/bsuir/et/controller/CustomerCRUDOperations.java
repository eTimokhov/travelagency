package by.bsuir.et.controller;

import by.bsuir.et.service.CustomerService;
import by.bsuir.et.model.beans.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class with simple operations with customers
 */
public class CustomerCRUDOperations {
    private CustomerService controller;
    private Scanner scanner;

    /**
     * @param controller
     */
    public CustomerCRUDOperations(CustomerService controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    /**
     * deleting customer process
     */
    public void delete() {
        String customerId = readNotEmptyString("Customer id");
        boolean removed = controller.deleteCustomer(customerId);
        if (removed) {
            System.out.println("Customer " + customerId + " removed successfully");
        } else {
            System.out.println("Customer with id " + customerId + " not found.");
        }
    }

    /**
     * output customer to console
     */
    public void read() {
        String customerId = readNotEmptyString("Customer id");
        Customer customer = controller.getCustomer(customerId);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer with id " + customerId + " not found.");
        }
    }

    /**
     * creating customer process
     */
    public void create() {
        String firstName = readNotEmptyString("First name");
        String lastName = readNotEmptyString("Last name");
        String address = readNotEmptyString("Address");
        String phoneNumber = readNotEmptyString("Phone number");
        Customer customer = new Customer(firstName, lastName, address, phoneNumber, new ArrayList<>());
        controller.saveCustomer(customer);
        System.out.println("Customer " + customer.getId() + " added successfully.");
    }

    /**
     * updating customer process
     */
    public void update() {
        String customerId = readNotEmptyString("Customer id");
        Customer customer = controller.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer with id " + customerId + " not found.");
            return;
        }
        customer.setFirstName(readNewPropertyValueOrReturnOldOne("First name", customer.getFirstName()));
        customer.setLastName(readNewPropertyValueOrReturnOldOne("Last name", customer.getLastName()));
        customer.setAddress(readNewPropertyValueOrReturnOldOne("Address", customer.getAddress()));
        customer.setPhoneNumber(readNewPropertyValueOrReturnOldOne("Phone number", customer.getPhoneNumber()));
        controller.saveCustomer(customer);
        System.out.println("Customer " + customer.getId() + " updated successfully.");
    }

    /**
     * outputs all customers
     */
    public void readAll() {
        List<Customer> customers = controller.getCustomersList();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * outputs all customers sorted by first name, last name, address
     */
    public void readAllSorted() {
        List<Customer> customers = controller.getSortedCustomersList();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * @param propertyName  name of property
     * @param propertyValue value of property
     * @return new string or old value is string wasn't read
     */
    private String readNewPropertyValueOrReturnOldOne(String propertyName, String propertyValue) {
        System.out.println(propertyName + ": " + propertyValue);
        System.out.println("Enter new " + propertyName + " if you want to change current value, else press Enter:");
        String result = scanner.nextLine();
        if (!result.trim().isEmpty()) {
            return result;
        }
        return propertyValue;
    }

    /**
     * @param propertyName name of property to ask user to input it
     * @return read string
     */
    private String readNotEmptyString(String propertyName) {
        System.out.println(propertyName + ": ");
        String result = scanner.nextLine();
        while (result.trim().isEmpty()) {
            System.out.println(propertyName + " can't be empty. Please, try again: ");
            result = scanner.nextLine();
        }
        return result;
    }
}
