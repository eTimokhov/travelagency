package by.bsuir.et.migration.saver;

import by.bsuir.et.migration.DatabaseException;
import by.bsuir.et.migration.JdbcService;

import java.sql.SQLException;

public abstract class EntitySaver {
    protected JdbcService jdbcService = JdbcService.getInstance();
    public abstract String getDropTableQuery();
    public abstract String getCreateTableQuery();

    protected void initTable() throws DatabaseException{
        try {
            jdbcService.getConnection().createStatement().execute(getDropTableQuery());
            jdbcService.getConnection().createStatement().execute(getCreateTableQuery());
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
