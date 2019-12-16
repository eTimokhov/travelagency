package by.bsuir.et.migration.saver;

import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.model.beans.Event;
import by.bsuir.et.model.beans.Flight;
import by.bsuir.et.model.beans.Tour;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FlightSaver extends EntitySaver {
    private static FlightSaver flightSaver;
    private FlightSaver() throws DatabaseException {
        initTable();
    }

    public static FlightSaver getInstance() throws DatabaseException {
        if (flightSaver == null) {
            flightSaver = new FlightSaver();
        }
        return flightSaver;
    }

    private static final String INSERT_QUERY = "INSERT INTO flights (id, class, seats, departure_time, arrival_time, " +
            "departure_airport, arrival_airport) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS flights";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS flights (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "class VARCHAR(255)," +
            "seats INT," +
            "departure_time TIMESTAMP," +
            "arrival_time TIMESTAMP," +
            "departure_airport VARCHAR(255)," +
            "arrival_airport VARCHAR(255)" +
            ");";

    public void save(Flight flight) throws DatabaseException {
        try (PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, flight.getId());
            preparedStatement.setString(2, flight.getFlightClass().name());
            preparedStatement.setInt(3, flight.getSeats());
            preparedStatement.setTimestamp(4, new Timestamp(flight.getDepartureTime().getTime()));
            preparedStatement.setTimestamp(5, new Timestamp(flight.getArrivalTime().getTime()));
            preparedStatement.setString(6, flight.getDepartureAirport());
            preparedStatement.setString(7, flight.getArrivalAirport());

            preparedStatement.executeUpdate();
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
