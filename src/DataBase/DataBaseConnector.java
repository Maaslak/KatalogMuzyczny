package DataBase;

import JavaObjects.Album;
import JavaObjects.Festiwal;
import JavaObjects.Koncert;
import JavaObjects.Zespol;

import java.sql.*;

import java.util.ArrayList;
import java.util.Properties;

public class DataBaseConnector {
    private Connection connection;
    private Properties connectionProperties;
    private static String user;
    private static String password;
    private ArrayList<Zespol> zespoly;
    private ArrayList<Album> albumy;
    private ArrayList<Koncert> koncerty;
    private ArrayList<Festiwal> festiwale;

    public DataBaseConnector() throws SQLException {
        zespoly = new ArrayList<>();
        albumy = new ArrayList<>();
        koncerty = new ArrayList<>();
        festiwale = new ArrayList<>();
        connect();
    }

    public void connect() throws SQLException {
        connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", password);
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", connectionProperties);
    }

    public ArrayList<Zespol> getZespoly() throws Exception {
        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM ZESPOLY");
        return executegetZespoly(statement);
    }

    public ArrayList<Zespol> getZespoly(String nazwa, Date dataBegin, Date dataEnd, String kraj_zalozenia, String miasto_zalozenia) throws Exception {
        String query= new String();
        query += "SELECT * FROM ZESPOLY WHERE ";
        if(nazwa != null) query += "NAZWA LIKE ? ";
        if(dataBegin != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_zalozenia > ? ";
        }
        if(dataEnd != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_zalozenia < ?";
        }
        if(kraj_zalozenia != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "kraj_zalozenia = ?";
        }
        if(miasto_zalozenia != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "miasto_zalozenia = ?";
        }
        PreparedStatement statement = null;
        statement = connection.prepareStatement(query);
        int i = 1;
        if(nazwa != null) {
            statement.setString(i, nazwa);
            i ++;
        }
        if(dataBegin != null){
            statement.setDate(i, dataBegin);
            i++;
        }
        if(dataEnd != null){
            statement.setDate(i, dataEnd);
            i++;
        }
        if(kraj_zalozenia != null){
            statement.setString(i, kraj_zalozenia);
            i++;
        }
        if(miasto_zalozenia != null){
            statement.setString(i, miasto_zalozenia);
            i++;
        }
        return executegetZespoly(statement);
    }

    private ArrayList<Zespol> executegetZespoly(PreparedStatement statement) throws Exception {
        zespoly = null;
        boolean error = false;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Zespol zespol = new Zespol(resultSet.getString(1), resultSet.getDate(2), resultSet.getString(3), resultSet.getString(4));
                zespoly.add(zespol);
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
        return zespoly;
    }

    public ArrayList<Album> getAlbumy() throws Exception {
        albumy = null;
        boolean error = false;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ALBUMY");
            while (resultSet.next()){
                Album album = new Album(resultSet.getString(1), resultSet.getDate(2), resultSet.getFloat(3), resultSet.getString(4));
                albumy.add(album);
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
            throw new Exception("Nie udalo sie pobrac albumow");
        return albumy;
    }

    public ArrayList<Koncert> getKoncert() throws Exception {
        koncerty = null;
        boolean error = false;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM KONCERTY");
            while (resultSet.next()){
                Koncert koncert = new Koncert(resultSet.getString(1), resultSet.getDate(2), resultSet.getString(3));
                koncerty.add(koncert);
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
            throw new Exception("Nie udalo sie pobrac koncertow");
        return koncerty;
    }

    public ArrayList<Festiwal> getFestiwale() throws Exception {
        festiwale = null;
        boolean error = false;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM KONCERTY");
            while (resultSet.next()){
                Festiwal festiwal = new Festiwal(resultSet.getString(1), resultSet.getDate(2), resultSet.getDate(3));
                festiwale.add(festiwal);
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
            throw new Exception("Nie udalo sie pobrac festiwali");
        return festiwale;
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
