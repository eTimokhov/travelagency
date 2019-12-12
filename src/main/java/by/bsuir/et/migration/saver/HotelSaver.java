package by.bsuir.et.migration.saver;

import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.model.beans.Flight;
import by.bsuir.et.model.beans.Hotel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HotelSaver extends EntitySaver {
    private static HotelSaver hotelSaver;
    private HotelSaver() throws DatabaseException {
        initTable();
    }

    public static HotelSaver getInstance() throws DatabaseException {
        if (hotelSaver == null) {
            hotelSaver = new HotelSaver();
        }
        return hotelSaver;
    }

    private static final String INSERT_QUERY = "INSERT INTO hotels (id, name, country, address) VALUES (?, ?, ?, ?)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS hotels";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS hotels (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255)," +
            "country VARCHAR(255)," +
            "address VARCHAR(255)" +
            ");";

    public void save(Hotel hotel) throws DatabaseException {
        try {
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, hotel.getId());
            preparedStatement.setString(2, hotel.getName());
            preparedStatement.setString(3, hotel.getCountry());
            preparedStatement.setString(4, hotel.getAddress());

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
