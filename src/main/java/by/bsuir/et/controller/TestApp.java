package by.bsuir.et.controller;

import by.bsuir.et.service.CustomerService;
import by.bsuir.et.service.CustomerServiceImpl;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) throws JAXBException {
        CustomerService controller = new CustomerServiceImpl();
        CustomerCRUDOperations crudOperations = new CustomerCRUDOperations(controller);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose operation: [create, read, update, delete, readAll, readAllSorted exit]");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("exit")) {
            switch (input) {
                case "create":
                    crudOperations.create();
                    break;
                case "read":
                    crudOperations.read();
                    break;
                case "update":
                    crudOperations.update();
                    break;
                case "delete":
                    crudOperations.delete();
                    break;
                case "readAll":
                    crudOperations.readAll();
                    break;
                case "readAllSorted":
                    crudOperations.readAllSorted();
                    break;
                default:
                    System.out.println("Please, enter a valid operation name: ");
            }
            System.out.println("Choose operation: [create, read, update, delete, exit]");
            input = scanner.nextLine();
        }
    }



}
