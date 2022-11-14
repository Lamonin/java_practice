package DataBase;

import java.sql.*;
import java.util.Properties;

public class DataBaseController {
    private Connection connection;
    private final Properties connectionProperties;

    public DataBaseController() {
        connectionProperties = new Properties();

/*        try {
            Properties props = new Properties();
            props.setProperty("user", "javapractice");
            props.setProperty("password", "javapracticepassword");
            var conn = DriverManager.getConnection("jdbc:postgresql://localhost/javapractice", props);
            var st = conn.prepareStatement(conn.nativeSQL("DROP TABLE IF EXISTS users; CREATE TABLE users(Id SERIAL PRIMARY KEY, Name CHARACTER VARYING(30), Age INTEGER);"));
            st.executeUpdate();

            st = conn.prepareStatement(conn.nativeSQL("INSERT INTO users (Name, Age) VALUES ('Tom', 33), ('Ben', 32);"));
            st.execute();
            var rs = st.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
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
