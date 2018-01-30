package DataBase;

import JavaObjects.Zespol;

import java.sql.*;

import java.util.ArrayList;
import java.util.Properties;

public class DataBaseConnector {
    private Connection connection;
    private Properties connectionProperties;
    private static String user;
    private static String password;
    private ArrayList<Zespol> zespols;

    public DataBaseConnector() throws SQLException {
        zespols = new ArrayList<>();
        connect();
    }

    public void connect() throws SQLException {
        connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", password);
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", connectionProperties);
    }

    public ArrayList<Zespol> getZespoly() throws Exception {
        boolean error = false;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ZESPOLY");
            while (resultSet.next()){
                Zespol zespol = new Zespol(resultSet.getString(1), resultSet.getDate(2), resultSet.getString(3), resultSet.getString(4));
                zespols.add(zespol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            error = true;
        }
        finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                }
            }
         }
         if (error)
             throw new Exception("Nie udalo sie pobrac zespolow");
        return zespols;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        DataBaseConnector.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataBaseConnector.password = password;
    }
}
