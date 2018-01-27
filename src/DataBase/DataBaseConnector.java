package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnector {
    private Connection connection;
    private Properties connectionProperties;
    private static String user;
    private static String password;

    public DataBaseConnector() throws SQLException {
        connect();
    }

    private void connect() throws SQLException {
        connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", password);
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//admlab2.cs.put.poznan.pl:1521/dblab02_students.cs.put.poznan.pl", connectionProperties);
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
