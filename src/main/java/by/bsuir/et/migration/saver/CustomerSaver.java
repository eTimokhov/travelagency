package by.bsuir.et.migration.saver;

import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.beans.Tour;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerSaver extends EntitySaver {
    private static CustomerSaver customerSaver;
    private TourSaver tourSaver = TourSaver.getInstance();
    private CustomerSaver() throws DatabaseException {
        initTable();
    }

    public static CustomerSaver getInstance() throws DatabaseException {
        if (customerSaver == null) {
            customerSaver = new CustomerSaver();
        }
        return customerSaver;
    }

    private static final String INSERT_QUERY = "INSERT INTO customers (id, first_name, last_name, address, phone_number) VALUES (?, ?, ?, ?, ?)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS customers";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS customers (" +
            "    id INT AUTO_INCREMENT PRIMARY KEY," +
            "    first_name VARCHAR(255)," +
            "    last_name VARCHAR(255)," +
            "    address VARCHAR(255)," +
            "    phone_number VARCHAR(255)" +
            ");";

    public void save(Customer customer) throws DatabaseException {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.executeUpdate();

            for (Tour tour : customer.getTours()) {
                tourSaver.save(tour, customer);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public String getDropTableQuery() {
        return DROP_TABLE;
    }

    @Override
    public String getCreateTableQuery() {
        return CREATE_TABLE;
    }
}
