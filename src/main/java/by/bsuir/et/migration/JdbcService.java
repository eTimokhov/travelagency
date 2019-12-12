package by.bsuir.et.migration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcService {
    private final Logger logger = LogManager.getLogger(JdbcService.class);
    private static final String DB_URL = "jdbc:mysql://localhost:3306/agency?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";


    private Connection connection;
    private boolean initialized = false;

    private JdbcService() {
    }

    public void init() throws DatabaseException {
        init(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void init(String url, String user, String password) throws DatabaseException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            logger.debug("Connection to " + url + " successful");
            initialized = true;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Connection to " + url + " failed " + e);
            initialized = false;
            throw new DatabaseException(e.getMessage());
        }
    }

    private static class SingletonHandler {
        static final JdbcService INSTANCE = new JdbcService();
    }

    public static JdbcService getInstance() {
        return JdbcService.SingletonHandler.INSTANCE;
    }

    public Connection getConnection() throws DatabaseException {
        if (initialized)
            return connection;
        else
            throw new DatabaseException("JdbcService not initialized");
    }
}
