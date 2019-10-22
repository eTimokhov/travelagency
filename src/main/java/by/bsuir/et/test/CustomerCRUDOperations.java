package by.bsuir.et.test;

import by.bsuir.et.controller.CustomerController;
import by.bsuir.et.model.beans.Customer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerCRUDOperations {
    private CustomerController controller;
    private Scanner scanner;

    public CustomerCRUDOperations(CustomerController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void delete() {
        String customerId = readNotEmptyString("Customer id");
        boolean removed = controller.deleteCustomer(customerId);
        if (removed) {
            System.out.println("Customer " + customerId + " removed successfully");
        } else {
            System.out.println("Customer with id " + customerId + " not found.");
        }
    }

    public void read() {
        String customerId = readNotEmptyString("Customer id");
        Customer customer = controller.getCustomer(customerId);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer with id " + customerId + " not found.");
        }
    }

    public void create() {
        String firstName = readNotEmptyString("First name");
        String lastName = readNotEmptyString("Last name");
        String address = readNotEmptyString("Address");
        String phoneNumber = readNotEmptyString("Phone number");
        Customer customer = new Customer(firstName, lastName, address, phoneNumber, new ArrayList<>());
        controller.saveCustomer(customer);
        System.out.println("Customer " + customer.getId() + " added successfully.");
    }

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

    public void readAll() {
        List<Customer> customers = controller.getCustomersList();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void readAllSorted() {
        List<Customer> customers = controller.getSortedCustomersList();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private String readNewPropertyValueOrReturnOldOne(String propertyName, String propertyValue) {
        System.out.println(propertyName + ": " + propertyValue);
        System.out.println("Enter new " + propertyName + " if you want to change current value, else press Enter:");
        String result = scanner.nextLine();
        if (!result.trim().isEmpty()) {
            return result;
        }
        return propertyValue;
    }
    
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
