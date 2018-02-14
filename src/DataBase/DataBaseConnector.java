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
    private ArrayList<Utwor> utwory;
    private ArrayList<Gatunek> gatunki;
    private ArrayList<Muzyk> muzycy;

    public DataBaseConnector() throws SQLException {
        zespoly = new ArrayList<>();
        albumy = new ArrayList<>();
        koncerty = new ArrayList<>();
        festiwale = new ArrayList<>();
        miasta = new ArrayList<>();
        utwory = new ArrayList<>();
        gatunki = new ArrayList<>();
        muzycy = new ArrayList<>();
        connect();
    }

    public void connect() throws SQLException {
        connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", password);
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", connectionProperties);
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
            query += "NAZWA = ? ";
        }
        if(dataBegin != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "data_zalozenia >= ? ";
        }
        if(dataEnd != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "data_zalozenia <= ? ";
        }
        if(!kraj_zalozenia.isEmpty()){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "kraj_zalozenia = ? ";
        }
        if(!miasto_zalozenia.isEmpty()){
            if(query.charAt(query.length() - 2) == '?')
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
                Zespol zespol = new Zespol(resultSet.getString(1), resultSet.getDate(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), this);
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

    public ArrayList<Album> getAlbumy(String nazwa, Date dateBegin, Date dateEnd, Float ocena, String jezyk, Integer zespolId) throws Exception {
        if(nazwa.isEmpty() && dateBegin == null && dateEnd == null && ocena == null && jezyk.isEmpty() && zespolId == null)
            return getAlbumy();
        String query= new String();
        query += "SELECT * FROM ALBUMY WHERE ";
        if(!nazwa.isEmpty()) {
            query += "NAZWA LIKE ? ";
        }
        if(dateBegin != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "data_wydania > ? ";
        }
        if(dateEnd != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "data_wydania < ? ";
        }
        if(ocena != null){
                if(query.charAt(query.length() - 2) == '?')
                    query += "AND ";
                query += "ocena = ? ";
            }
        if(!jezyk.isEmpty()){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "jezyk = ? ";
        }
        if(zespolId != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "zespol_id = ? ";
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
        if(zespolId != null){
            statement.setInt(i, zespolId);
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

    public ArrayList<Koncert> getKoncert(String nazwa, Date dateBegin, Date dateEnd, String miastoNazwa, Integer festiwalId) throws Exception {
        if(nazwa.isEmpty() && dateBegin == null && dateEnd == null && miastoNazwa.isEmpty() && festiwalId == null)
            return getKoncert();
        String query= new String();
        query += "SELECT * FROM KONCERTY WHERE ";
        if(!nazwa.isEmpty()) {
            query += "NAZWA LIKE ? ";
        }
        if(dateBegin != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "data > ? ";
        }
        if(dateEnd != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "data < ? ";
        }
        if(!miastoNazwa.isEmpty()){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "Miasto_nazwa = ? ";
        }
        if(festiwalId != null){
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "FESTIWAL_ID = ?";
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
        if(festiwalId != null){
            statement.setInt(i, festiwalId);
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
                Koncert koncert = new Koncert(resultSet.getString(1), resultSet.getDate(2), resultSet.getString(3), resultSet.getInt(5));
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
            if(query.charAt(query.length() - 2) == '?')
                query += "AND ";
            query += "data_rozpoczecia > ? ";
        }
        if(dateEnd != null){
            if(query.charAt(query.length() - 2) == '?')
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
                Miasto miasto = new Miasto(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3));
                miasta.add(miasto);
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
            throw new Exception("Nie udalo sie pobrac miast");
        return miasta;
    }

    public ArrayList<Utwor> getUtwory(Integer albumId) throws Exception {
        PreparedStatement statement = null;
        utwory.clear();
        boolean error = false;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM UTWORY WHERE ALBUM_ID = ?");
            statement.setInt(1, albumId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Utwor utwor = new Utwor(resultSet.getString(1), resultSet.getTimestamp(2));
                utwory.add(utwor);
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
        return utwory;
    }

    public ArrayList<Gatunek> getGatunki(Integer zespolId, String nazwa) throws Exception {
        if(!nazwa.isEmpty() && zespolId != null)
            throw new Exception("Bad use of function 'getGatunki'");
        PreparedStatement statement = null;
        gatunki.clear();
        boolean error = false;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM GATUNKI ";
        if(zespolId != null)
            sql += "g JOIN PRZYNALEZNOSCI p ON g.NAZWA = p.GATUNEK_NAZWA WHERE p.ZESPOL_ID = ?";
        if(!nazwa.isEmpty())
            sql += "WHERE NAZWA = ?";
        try {
            statement = connection.prepareStatement(sql);
            if(zespolId != null)
                statement.setInt(1, zespolId);
            if(!nazwa.isEmpty())
                statement.setString(1, nazwa);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Gatunek gatunek= new Gatunek(resultSet.getString(1), resultSet.getString(2));
                gatunki.add(gatunek);
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
            throw new Exception("Nie udalo sie pobrac gatunkow");
        return gatunki;
    }

    public ArrayList<Muzyk> getMuzycy(int zespolId) throws Exception {
        PreparedStatement statement = null;
        miasta.clear();
        boolean error = false;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM MUZYCY g JOIN CZŁONKOWSTWA p USING(MUZYK_ID) WHERE p.ZESPOL_ID= ?");
            statement.setInt(1, zespolId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Muzyk muzyk= new Muzyk(resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5));
                muzycy.add(muzyk);
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
            throw new Exception("Nie udalo sie pobrac muzykow");
        return muzycy;
    }

    public int insertUtwor(Utwor utwor, Integer albumId) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO UTWORY(TYTUL, ALBUM_ID";
        if(utwor.getCzas() != null)
            sql += ", CZAS ";
        sql += ") VALUES (?, ?";
        if(utwor.getCzas() != null)
            sql += " ,?";
        sql += ")";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, utwor.getTytul());
            statement.setInt(2, albumId);
            if(utwor.getCzas() != null)
                statement.setTimestamp(3, utwor.getCzas());
            changes = statement.executeUpdate();

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

    public int insertAlbum(Album album, Integer zespolId) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO ALBUMY(NAZWA, ZESPOL_ID";
        if(album.getDataWydania() != null)
            sql += ", data_wydania";
        if(album.getOcena() != null)
            sql += ", ocena";
        if(!album.getJezyk().isEmpty())
            sql += ", jezyk";
        sql += ") VALUES (?, ?";
        if(album.getDataWydania() != null)
            sql += ", ?";
        if(album.getOcena() != null)
            sql += ", ?";
        if(!album.getJezyk().isEmpty())
            sql += ", ?";
        sql += ")";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, album.getNazwa());
            statement.setInt(2, zespolId);
            int i = 3;
            if(album.getDataWydania() != null) {
                statement.setDate(i, album.getDataWydania());
                i++;
            }
            if(album.getOcena() != null){
                statement.setFloat(i, album.getOcena());
                i++;
            }
            if(!album.getJezyk().isEmpty()){
                statement.setString(i, album.getJezyk());
                i++;
            }
            changes = statement.executeUpdate();

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

    public int insertKoncert(Koncert koncert, Integer festiwalId) throws Exception {
        if(koncert == null)
            throw new Exception("No concert added");
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO KONCERTY(NAZWA, DATA,  MIASTO_NAZWA, ZESPOL_ID";
        if(festiwalId != null)
            sql += ", Festiwal_ID";
        sql += ") VALUES (?, ?, ?, ?";
        if(festiwalId != null)
            sql += ", ?";
        sql += ")";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, koncert.getNazwa());
            statement.setDate(2, koncert.getData());
            statement.setString(3, koncert.getMiasto_nazwa());
            statement.setInt(4, koncert.getZespolId());
            if(festiwalId != null)
                statement.setInt(5, festiwalId);
            changes = statement.executeUpdate();

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

    public int insertZespol(Zespol zespol) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO ZESPOLY(NAZWA, DATA_ZALOZENIA";
        if(!zespol.getMiasto_zalozenia().isEmpty())
            sql += ", MIASTO_ZALOZENIA";
        if(!zespol.getKraj_zalozenia().isEmpty())
            sql += ", KRAJ_ZALOZENIA";
        sql += ") VALUES (?, ?";
        if(!zespol.getMiasto_zalozenia().isEmpty())
            sql += ", ?";
        if(!zespol.getKraj_zalozenia().isEmpty())
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, zespol.getNazwa());
            statement.setDate(2, zespol.getDate());
            int i =3;
            if(!zespol.getMiasto_zalozenia().isEmpty()){
                statement.setString(i, zespol.getMiasto_zalozenia());
                i++;
            }
            if(!zespol.getKraj_zalozenia().isEmpty())
                statement.setString(i, zespol.getKraj_zalozenia());
            changes = statement.executeUpdate();

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

    public int insertFestiwal(Festiwal festiwal) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO FESTIWALE(NAZWA, DATA_ROZPOCZECIA";
        if(festiwal.getDataZakonczenia() != null)
            sql += ", DATA_ZAKONCZENIA";
        sql += ") VALUES (?, ?";
        if(festiwal.getDataZakonczenia() != null)
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, festiwal.getNazwa());
            statement.setDate(2, festiwal.getDataRozpoczecia());
            int i =3;
            if(festiwal.getDataZakonczenia() != null)
                statement.setDate(i, festiwal.getDataZakonczenia());
            changes = statement.executeUpdate();

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

            changes = statement.executeUpdate();

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
            changes = statement.executeUpdate();

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
            changes = statement.executeUpdate();

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

    public int insertPrzynaleznosc(String gatunekNazwa, int zespolId, Character glowna) throws Exception {
        boolean error = false;
        int changes = 0;
        PreparedStatement statement= null;
        ResultSet rs = null;
        String sql;
        sql = "INSERT INTO PRZYNALEZNOSCI(GATUNEK_NAZWA, ZESPOL_ID";
        if(glowna != null)
            sql += ", GLOWNA";
        sql += ") VALUES (?, ?";
        if(glowna != null)
            sql += ", ?";
        sql += ")";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, gatunekNazwa);
            statement.setInt(2, zespolId);
            if(glowna != null)
                statement.setObject(3, glowna);
            changes = statement.executeUpdate();

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
            changes = statement.executeUpdate();

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

    public void deleteUtwor(String tytul, int albumId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from UTWORY where TYTUL = ? AND ALBUM_ID = ?");
            statement.setString(1, tytul);
            statement.setInt(2, albumId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac utworu");
    }

    public void deleteAlbum(int albumId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from UTWORY WHERE ALBUM_ID = ?");
            statement.setInt(1, albumId);
            changes = statement.executeUpdate();
            statement = connection.prepareStatement("delete from ALBUMY WHERE ALBUM_ID = ?");
            statement.setInt(1, albumId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac albumu");
    }

    public void deleteZespol(int zespolId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from PRZYNALEZNOSCI WHERE ZESPOL_ID = ?");
            statement.setInt(1, zespolId);
            changes = statement.executeUpdate();
            statement = connection.prepareStatement("delete from ZESPOLY WHERE ZESPOL_ID = ?");
            statement.setInt(1, zespolId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac zespolu");
    }

    public void deletePrzynaleznosc(String gatunekNazwa, int zespolId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from PRZYNALEZNOSCI where GATUNEK_NAZWA = ? AND ZESPOL_ID = ?");
            statement.setString(1, gatunekNazwa);
            statement.setInt(2, zespolId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac przynaleznosci do gatunku");
    }

    public void deleteGatunek(String nazwa) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from GATUNKI where NAZWA = ?");
            statement.setString(1, nazwa);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac gatunku");
    }

    public void deleteCzlonkostwo(Date od, int zespolId, int muzykId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from CZŁONKOWSTWA where OD = ? AND ZESPOL_ID = ? AND MUZYK_ID = ?");
            statement.setDate(1, od);
            statement.setInt(2, zespolId);
            statement.setInt(3, muzykId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac przynaleznosci do Czlonkostwa");
    }

    public void deleteKoncert(String nazwa, Date data) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from KONCERTY WHERE NAZWA = ? AND DATA = ?");
            statement.setString(1, nazwa);
            statement.setDate(2, data);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac koncertu");
    }

    public void deleteMiasto(String nazwa) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from MIASTA where NAZWA= ?");
            statement.setString(1, nazwa);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac miasta");
    }

    public void deleteFestiwal(int festiwalId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from FESTIWALE where FESTIWAL_ID= ?");
            statement.setInt(1, festiwalId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac festiwalu");
    }

    public void deleteMuzyk(int muzykId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("delete from MUZYCY WHERE MUZYK_ID = ?");
            statement.setInt(1, muzykId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie usunac muzyka");
    }

    public void updateUtwor(String tytul, Timestamp czas) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("UPDATE UTWORY SET CZAS = ? WHERE TYTUL = ?");
            statement.setTimestamp(1, czas);
            statement.setString(2, tytul);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie zmodyfikowac utworu");
    }

    public void updateAlbum(String nazwa, Date dataWydania, Float ocena, String jezyk, int albumId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("UPDATE ALBUMY SET NAZWA = ?, DATA_WYDANIA = ?, OCENA = ?, JEZYK = ? WHERE ALBUM_ID = ?");
            statement.setString(1, nazwa);
            statement.setDate(2, dataWydania);
            statement.setFloat(3, ocena);
            statement.setString(4, jezyk);
            statement.setInt(5, albumId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie zmodyfikowac albumu");
    }

    public void updateZespol(String name, Date data, String city, String country, int zespolId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("UPDATE ZESPOLY SET NAZWA = ?, DATA_ZALOZENIA = ?, MIASTO_ZALOZENIA = ?, KRAJ_ZALOZENIA = ? WHERE ZESPOL_ID = ?");
            statement.setString(1, name);
            statement.setDate(2, data);
            statement.setString(3, city);
            statement.setString(4, country);
            statement.setInt(5, zespolId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie zmodyfikowac zespolu");
    }

    public void updatePrzynaleznosc(String glowna, String nazwaGatunku,  int zespolId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("UPDATE PRZYNALEZNOSCI SET GLOWNA = ? WHERE GATUNEK_NAZWA = ? AND ZESPOL_ID = ?");
            statement.setString(1, glowna);
            statement.setString(2, nazwaGatunku);
            statement.setInt(3, zespolId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie zmodyfikowac przynaleznosci");
    }

    public void updateGatunek(String nazwa, String opis) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("UPDATE GATUNKI SET OPIS = ? WHERE NAZWA = ?");
            statement.setString(1, opis);
            statement.setString(2, nazwa);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie zmodyfikowac gatunek");
    }

    public void updateCzlonkostwo(Date dataOd, Date dataDo, String funkcja, int zespolId, int muzykId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("UPDATE CZŁONKOWSTWA SET DO = ?, FUNKCJA = ? WHERE OD = ? AND ZESPOL_ID = ? AND MUZYK_ID = ?");
            statement.setDate(1, dataDo);
            statement.setString(2, funkcja);
            statement.setDate(3, dataOd);
            statement.setInt(4, zespolId);
            statement.setInt(5, muzykId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi */ }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie zmodyfikowac czlonkostwa");
    }
/*
    public void updateKoncerty(String nazwa, Date data, String miastoNazwa, int festiwalId, int zespolId) throws Exception {
        boolean error =false;
        PreparedStatement statement = null;
        int changes = 0;
        try {
            statement = connection.prepareStatement("UPDATE KONCERTY SET  = ?, FUNKCJA = ? WHERE OD = ? AND ZESPOL_ID = ? AND MUZYK_ID = ?");
            statement.setDate(1, dataDo);
            statement.setString(2, funkcja);
            statement.setDate(3, dataOd);
            statement.setInt(4, zespolId);
            statement.setInt(5, muzykId);
            changes = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
            error = true;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    /* kod obsługi *//* }
            }
        }
        if (error == true)
            throw new Exception("Nie udalo sie zmodyfikowac czlonkostwa");
    }
*/


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
