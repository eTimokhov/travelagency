package by.bsuir.et.migration.saver;

import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.beans.Event;
import by.bsuir.et.model.beans.Hotel;
import by.bsuir.et.model.beans.Tour;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TourSaver extends EntitySaver {
    private static TourSaver tourSaver;

    private EventSaver eventSaver = EventSaver.getInstance();
    private FlightSaver flightSaver = FlightSaver.getInstance();
    private HotelSaver hotelSaver = HotelSaver.getInstance();

    private TourSaver() throws DatabaseException {
        initTable();
    }

    public static TourSaver getInstance() throws DatabaseException {
        if (tourSaver == null) {
            tourSaver = new TourSaver();
        }
        return tourSaver;
    }

    private static final String INSERT_QUERY = "INSERT INTO tours (id, customer_id, hotel_id, out_flight_id, ret_flight_id, " +
            "price, country, start_date, end_date, hot) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS tours";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS tours (\n" +
            "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "    customer_id INT,\n" +
            "    hotel_id INT,\n" +
            "    out_flight_id INT,\n" +
            "    ret_flight_id INT,\n" +
            "    price DECIMAL,\n" +
            "    country VARCHAR(255),\n" +
            "    start_date TIMESTAMP,\n" +
            "    end_date TIMESTAMP,\n" +
            "    hot TINYINT(1)\n" +
            ");";

    public void save(Tour tour, Customer customer) throws DatabaseException {
        try {
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, tour.getId());
            preparedStatement.setInt(2, customer.getId());
            preparedStatement.setInt(3, tour.getHotel().getId());
            preparedStatement.setInt(4, tour.getOutboundFlight().getId());
            preparedStatement.setInt(5, tour.getReturnFlight().getId());
            preparedStatement.setDouble(6, tour.getPrice());
            preparedStatement.setString(7, tour.getCountry());
            preparedStatement.setTimestamp(8, new Timestamp(tour.getStartDate().getTime()));
            preparedStatement.setTimestamp(9, new Timestamp(tour.getEndDate().getTime()));
            preparedStatement.setBoolean(10, tour.isHot());
            preparedStatement.executeUpdate();

            for (Event event : tour.getEvents()) {
                eventSaver.save(event,tour);
            }
            flightSaver.save(tour.getOutboundFlight());
            flightSaver.save(tour.getReturnFlight());

            hotelSaver.save(tour.getHotel());

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
