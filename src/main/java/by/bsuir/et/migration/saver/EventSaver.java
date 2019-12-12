package by.bsuir.et.migration.saver;

import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.beans.Event;
import by.bsuir.et.model.beans.Tour;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EventSaver extends EntitySaver {
    private static EventSaver eventSaver;
    private EventSaver() throws DatabaseException {
        initTable();
    }

    public static EventSaver getInstance() throws DatabaseException {
        if (eventSaver == null) {
            eventSaver = new EventSaver();
        }
        return eventSaver;
    }

    private static final String INSERT_QUERY = "INSERT INTO events (id, tour_id, name, address, ev_date) VALUES (?, ?, ?, ?, ?)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS events";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS events (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "tour_id INT," +
            "name VARCHAR(255)," +
            "address VARCHAR(255)," +
            "ev_date TIMESTAMP" +
            ");";

    public void save(Event event, Tour tour) throws DatabaseException {
        try {
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, event.getId());
            preparedStatement.setInt(2, tour.getId());
            preparedStatement.setString(3, event.getName());
            preparedStatement.setString(4, event.getAddress());
            preparedStatement.setTimestamp(5, new Timestamp(event.getDate().getTime()));

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
