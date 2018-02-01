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

    public int insertUtwor(String tytul, Date czas, int albumId) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO UTWORY(TYTUL, ALBUM_ID";
        if(czas != null)
            sql += ", CZAS ";
        sql += ") VALUES (?, ?";
        if(czas != null)
            sql += " ,?";
        sql += ")";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tytul);
            statement.setInt(2, albumId);
            if(czas != null)
                statement.setDate(3, czas);
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac utworu");
        return changes;
    }

    public int insertAlbum(String nazwa, int zespolId, Date dataWydania, Float ocena, String jezyk) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO ALBUMY(NAZWA, ZESPOL_ID";
        if(dataWydania != null)
            sql += ", data_wydania";
        if(ocena != null)
            sql += ", ocena";
        if(!jezyk.isEmpty())
            sql += ", jezyk";
        sql += ") VALUES (?, ?, ";
        if(dataWydania != null)
            sql += ", ?";
        if(ocena != null)
            sql += ", ?";
        if(!jezyk.isEmpty())
            sql += ", ?";
        sql += ")";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nazwa);
            statement.setInt(2, zespolId);
            int i = 3;
            if(dataWydania != null) {
                statement.setDate(i, dataWydania);
                i++;
            }
            if(ocena != null){
                statement.setFloat(i, ocena);
                i++;
            }
            if(!jezyk.isEmpty()){
                statement.setString(i, jezyk);
                i++;
            }
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac albumu");
        return changes;
    }

    public int insertKoncert(String nazwa,  Date data, String miastoNazwa, int zespolId, Integer festiwalId) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO KONCERTY(NAZWA, DATA,  MIASTO_NAZWA, ZESPOL_ID";
        if(festiwalId != null)
            sql += ", Festiwal_ID";
        sql += ") VALUES (?, ?, ?, ?, ?";
        if(festiwalId != null)
            sql += ", ?";
        sql += ")";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nazwa);
            statement.setDate(2, data);
            statement.setString(3, miastoNazwa);
            statement.setInt(4, zespolId);
            if(festiwalId != null)
                statement.setInt(5, festiwalId);
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac koncertu");
        return changes;
    }

    public int insertZespol(String nazwa,  Date dataZalozenia, String miastoZalozenia, String krajZalozenia) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO ZESPOLY(NAZWA, DATA_ZALOZENIA";
        if(!miastoZalozenia.isEmpty())
            sql += ", MIASTO_ZALOZENIA";
        if(!krajZalozenia.isEmpty())
            sql += ", KRAJ_ZALOZENIA";
        sql += ") VALUES (?, ?";
        if(!miastoZalozenia.isEmpty())
            sql += ", ?";
        if(!krajZalozenia.isEmpty())
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, nazwa);
            statement.setDate(2, dataZalozenia);
            int i =3;
            if(!miastoZalozenia.isEmpty()){
                statement.setString(i, miastoZalozenia);
                i++;
            }
            if(!krajZalozenia.isEmpty())
                statement.setString(i, krajZalozenia);
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac zespolu");
        return changes;
    }

    public int insertFestiwal(String nazwa,  Date dataRozpoczecia, Date dataZakonczenia) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO FESTIWALE(NAZWA, DATA_ROZPOCZECIA";
        if(dataZakonczenia != null)
            sql += ", DATA_ZAKONCZENIA";
        sql += ") VALUES (?, ?";
        if(dataZakonczenia != null)
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, nazwa);
            statement.setDate(2, dataRozpoczecia);
            int i =3;
            if(dataZakonczenia != null)
                statement.setDate(i, dataZakonczenia);
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac festiwalu");
        return changes;
    }

    public int insertMiasto(String nazwa,  Integer polozenieX, Integer polozenieY) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO MIASTA(NAZWA";
        if(polozenieX != null)
            sql += ", POLOZENIE_X";
        if(polozenieY != null)
            sql += ", KRAJ_ZALOZENIA";
        sql += ") VALUES (?";
        if(polozenieX != null)
            sql += ", ?";
        if(polozenieY != null)
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, nazwa);
            int i= 2;
            if(polozenieX != null){
                statement.setInt(i, polozenieX);
                i++;
            }
            if(polozenieY != null){
                statement.setInt(i, polozenieY);
                i++;
            }

            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac miasta");
        return changes;
    }

    public int insertGatunek(String nazwa,  String opis) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO GATUNKI(NAZWA";
        if(!opis.isEmpty())
            sql += ", OPIS";
        sql += ") VALUES (?";
        if(!opis.isEmpty())
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, nazwa);
            if(!opis.isEmpty())
                statement.setString(2, opis);
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac gatunku");
        return changes;
    }

    public int insertMuzyk(String imie, String nazwisko,  Date dataUrodzenia, String pochodzenie) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO MUZYCY(IMIE, NAZWISKO, DATA_URODZENIA";
        if(!pochodzenie.isEmpty())
            sql += ", POCHODZENIE";
        sql += ") VALUES (?, ?, ?";
        if(!pochodzenie.isEmpty())
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, imie);
            statement.setString(2, nazwisko);
            if(!pochodzenie.isEmpty())
                statement.setString(3, pochodzenie);
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac muzyka");
        return changes;
    }

    public int insertPrzynaleznosc(String nazwa,  String gatunekNazwa, int zespolId, Character glowna) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO PRZYNALEZNOSCI(NAZWA, GATUNEK_NAZWA, ZESPOL_ID";
        if(glowna != null)
            sql += ", GLOWNA";
        sql += ") VALUES (?, ?, ?";
        if(glowna != null)
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, nazwa);
            statement.setString(2, gatunekNazwa);
            statement.setInt(3, zespolId);
            if(glowna != null)
                statement.setString(4, glowna.toString());
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac przynaleznosci do zespolu");
        return changes;
    }

    public int insertCzlonkostwo(Date dataOd,  int zespolId, int muzykId, Date dataDo, String funkcja) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO CZŁONKOWSTWA(OD, ZESPOL_ID, MUZYK_ID";
        if(dataDo != null)
            sql += ", DO";
        if(!funkcja.isEmpty())
            sql += ", FUNKCJA";
        sql += ") VALUES (?, ?, ?";
        if(dataDo != null)
            sql += ", ?";
        if(!funkcja.isEmpty())
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setDate(1, dataOd);
            statement.setInt(2, zespolId);
            statement.setInt(3, muzykId);
            int i =4;
            if(dataDo != null){
                statement.setDate(i, dataDo);
                i++;
            }
            if(!funkcja.isEmpty())
                statement.setString(i, funkcja);
            changes = statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if(error)
            throw new Exception("Nie udalo sie dodac czlonkowstwa");
        return changes;
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
