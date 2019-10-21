package by.bsuir.et.test;

import by.bsuir.et.controller.CustomerController;
import by.bsuir.et.controller.CustomerControllerImpl;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) throws JAXBException {
        CustomerController controller = new CustomerControllerImpl();
        CustomerCRUDOperations crudOperations = new CustomerCRUDOperations(controller);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose operation: [create, read, update, delete, exit]");
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
                default:
                    System.out.println("Please, enter a valid operation name: ");
            }
            System.out.println("Choose operation: [create, read, update, delete, exit]");
            input = scanner.nextLine();
        }
    }



}
