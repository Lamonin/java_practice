package DataBase;

import java.sql.*;
import java.util.Properties;

public class DataBaseController {
    private Connection connection;
    private final Properties connectionProperties;

    public DataBaseController() {
        connectionProperties = new Properties();
    }

    public DataBaseController setUser(String user, String password)
    {
        connectionProperties.setProperty("user", user);
        connectionProperties.setProperty("password", password);
        return this;
    }

    public void connect(String url)
    {
        try {
            connection = DriverManager.getConnection(url, connectionProperties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableController createTable(String tableName, TableTemplate template)
    {
        var builder = new StringBuilder("CREATE TABLE ");
        builder.append(tableName).append(template.getColumnsWithType()).append(";");

        try {
            connection.prepareStatement(builder.toString()).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new TableController(this, template).setTableName(tableName);
    }

    public boolean tableExist(String tableName)
    {
        try {
            var meta = connection.getMetaData();
            var resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
