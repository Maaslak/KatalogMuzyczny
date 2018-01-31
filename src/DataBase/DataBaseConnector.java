package DataBase;

import JavaObjects.*;

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
    private ArrayList<Miasto> miasta;

    public DataBaseConnector() throws SQLException {
        zespoly = new ArrayList<>();
        albumy = new ArrayList<>();
        koncerty = new ArrayList<>();
        festiwale = new ArrayList<>();
        miasta = new ArrayList<>();
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
        if(nazwa.isEmpty() && dataBegin == null && dataEnd == null && kraj_zalozenia!= null && kraj_zalozenia.isEmpty() && miasto_zalozenia.isEmpty())
            return getZespoly();
        String query= new String();
        query += "SELECT * FROM ZESPOLY WHERE ";
        if(!nazwa.isEmpty()) {
            query += "NAZWA LIKE ? ";
        }
        if(dataBegin != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_zalozenia > ? ";
        }
        if(dataEnd != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_zalozenia < ? ";
        }
        if(!kraj_zalozenia.isEmpty()){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "kraj_zalozenia = ? ";
        }
        if(!miasto_zalozenia.isEmpty()){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "miasto_zalozenia = ? ";
        }
        PreparedStatement statement = null;
        statement = connection.prepareStatement(query);
        int i = 1;
        if(!nazwa.isEmpty()) {
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
        if(!kraj_zalozenia.isEmpty()){
            statement.setString(i, kraj_zalozenia);
            i++;
        }
        if(!miasto_zalozenia.isEmpty()){
            statement.setString(i, miasto_zalozenia);
            i++;
        }
        return executegetZespoly(statement);
    }

    private ArrayList<Zespol> executegetZespoly(PreparedStatement statement) throws Exception {
        zespoly.clear();
        boolean error = false;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Zespol zespol = new Zespol(resultSet.getString(1), resultSet.getDate(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
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
        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM ALBUMY");
        return executegetAlbumy(statement);
    }

    public ArrayList<Album> getAlbumy(String nazwa, Date dateBegin, Date dateEnd, Float ocena, String jezyk) throws Exception {
        if(nazwa.isEmpty() && dateBegin == null && dateEnd == null && ocena != null && jezyk.isEmpty())
            return getAlbumy();
        String query= new String();
        query += "SELECT * FROM ALBUMY WHERE ";
        if(!nazwa.isEmpty()) {
            query += "NAZWA LIKE ? ";
        }
        if(dateBegin != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_wydania > ? ";
        }
        if(dateEnd != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_wydania < ? ";
        }
        if(ocena != null){
                if(query.charAt(query.length() - 1) == '?')
                    query += "AND ";
                query += "ocena = ? ";
            }
        if(!jezyk.isEmpty()){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "jezyk = ? ";
        }
        PreparedStatement statement = null;
        statement = connection.prepareStatement(query);
        int i = 1;
        if(!nazwa.isEmpty()) {
            statement.setString(i, nazwa);
            i ++;
        }
        if(dateBegin != null){
            statement.setDate(i, dateBegin);
            i++;
        }
        if(dateEnd != null){
            statement.setDate(i, dateEnd);
            i++;
        }
        if(ocena!=null){
                statement.setFloat(i, ocena);
                i++;
            }
        if(!jezyk.isEmpty()){
            statement.setString(i, jezyk);
            i++;
        }
        return executegetAlbumy(statement);
    }

    private ArrayList<Album> executegetAlbumy(PreparedStatement statement) throws Exception {
        albumy.clear();
        boolean error = false;
        ResultSet resultSet = null;
        try {

            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Album album = new Album(resultSet.getString(1), resultSet.getDate(2), resultSet.getFloat(3), resultSet.getString(4), resultSet.getInt(5));
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
        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM KONCERTY");
        return executegetKoncerty(statement);
    }

    public ArrayList<Koncert> getKoncert(String nazwa, Date dateBegin, Date dateEnd, String miastoNazwa) throws Exception {
        if(nazwa.isEmpty() && dateBegin == null && dateEnd == null && miastoNazwa.isEmpty())
            return getKoncert();
        String query= new String();
        query += "SELECT * FROM KONCERTY WHERE ";
        if(!nazwa.isEmpty()) {
            query += "NAZWA LIKE ? ";
        }
        if(dateBegin != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data > ? ";
        }
        if(dateEnd != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data < ? ";
        }
        if(!miastoNazwa.isEmpty()){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "Miasto_nazwa = ? ";
        }
        PreparedStatement statement = null;
        statement = connection.prepareStatement(query);
        int i = 1;
        if(!nazwa.isEmpty()) {
            statement.setString(i, nazwa);
            i ++;
        }
        if(dateBegin != null){
            statement.setDate(i, dateBegin);
            i++;
        }
        if(dateEnd != null){
            statement.setDate(i, dateEnd);
            i++;
        }
        if(!miastoNazwa.isEmpty()){
            statement.setString(i, miastoNazwa);
            i++;
        }
        return executegetKoncerty(statement);
    }

    private ArrayList<Koncert> executegetKoncerty(PreparedStatement statement) throws Exception {
        koncerty.clear();
        boolean error = false;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
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
        PreparedStatement statement = null;
        statement = connection.prepareStatement("SELECT * FROM FESTIWALE");
        return executegetFestiwale(statement);
    }

    public ArrayList<Festiwal> getFestiwale(String nazwa, Date dateBegin, Date dateEnd) throws Exception {
        if(nazwa.isEmpty() && dateBegin == null && dateEnd == null)
            return getFestiwale();
        String query= new String();
        query += "SELECT * FROM FESTIWALE WHERE ";
        if(!nazwa.isEmpty()) {
            query += "NAZWA LIKE ? ";
        }
        if(dateBegin != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_rozpoczecia > ? ";
        }
        if(dateEnd != null){
            if(query.charAt(query.length() - 1) == '?')
                query += "AND ";
            query += "data_zakonczenia < ? ";
        }

        PreparedStatement statement = null;
        statement = connection.prepareStatement(query);
        int i = 1;
        if(!nazwa.isEmpty()) {
            statement.setString(i, nazwa);
            i ++;
        }
        if(dateBegin != null){
            statement.setDate(i, dateBegin);
            i++;
        }
        if(dateEnd != null){
            statement.setDate(i, dateEnd);
            i++;
        }
        return executegetFestiwale(statement);
    }

    private ArrayList<Festiwal> executegetFestiwale(PreparedStatement statement) throws Exception {
        festiwale.clear();
        boolean error = false;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Festiwal festiwal = new Festiwal(resultSet.getString(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getInt(4));
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

    public ArrayList<Miasto> getMiasta() throws Exception {
        PreparedStatement statement = null;
        miasta.clear();
        boolean error = false;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM MIASTA");
            resultSet = statement.executeQuery();
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
        return miasta;
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
