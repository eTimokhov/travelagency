package by.bsuir.et.controller.console;

import by.bsuir.et.migration.saver.CustomerSaver;
import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.migration.JdbcService;
import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.service.AgencyService;
import by.bsuir.et.service.AgencyServiceImpl;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) throws JAXBException {
        //System.out.println(validateXml());
        //userInput();
        migrate();
    }

    private static void userInput() {
        AgencyService controller = new AgencyServiceImpl();
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

    private static boolean validateXml() {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File("src/main/resources/data.xsd"));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("src/main/resources/data.xml")));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getResource(String filename) throws FileNotFoundException {
        URL resource = TestApp.class.getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }

    private static void migrate() {
        if (!validateXml()) {
            System.err.println("Cannot migrate: Xml is not valid.");
            return;
        }
        AgencyService agencyService = new AgencyServiceImpl();
        JdbcService jdbcService = JdbcService.getInstance();
        List<Customer> customers = agencyService.getCustomersList();
        try {
            jdbcService.init();
            CustomerSaver customerSaver = CustomerSaver.getInstance();
            for (Customer customer : customers) {
                customerSaver.save(customer);
            }
        } catch (DatabaseException e) {
            System.err.println("Cannot migrate:");
            e.printStackTrace();
        }
    }
}
