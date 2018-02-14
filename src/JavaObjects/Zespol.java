package JavaObjects;

import DataBase.DataBaseConnector;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Zespol {
    private String nazwa, miasto_zalozenia, kraj_zalozenia;
    private Date date;
    private Integer id;
    private ArrayList<Gatunek> generes;
    private DataBaseConnector connector;

    public Zespol(String nazwa, Date date, String miasto_zalozenia, String kraj_zalozenia, Integer id, DataBaseConnector connector) throws Exception {
        this.nazwa = nazwa;
        this.date = date;
        this.miasto_zalozenia = miasto_zalozenia;
        this.kraj_zalozenia = kraj_zalozenia;
        this.id = id;
        this.connector = connector;
        uploadGeneres();
    }
    public Zespol(String nazwa, Date date, String miasto_zalozenia, String kraj_zalozenia, DataBaseConnector connector) throws Exception {
        this(nazwa, date, miasto_zalozenia, kraj_zalozenia, null, connector);
    }

    private void uploadGeneres() throws Exception {
        this.generes = connector.getGatunki(id, "");
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getMiasto_zalozenia() {
        return miasto_zalozenia;
    }

    public void setMiasto_zalozenia(String miasto_zalozenia) {
        this.miasto_zalozenia = miasto_zalozenia;
    }

    public String getKraj_zalozenia() {
        return kraj_zalozenia;
    }

    public void setKraj_zalozenia(String kraj_zalozenia) {
        this.kraj_zalozenia = kraj_zalozenia;
    }

    public LocalDate getLocalDate() {
        return date.toLocalDate();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Gatunek> getGeneres() {
        try {
            uploadGeneres();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generes;
    }

    @Override
    public String toString() {
        String string = "<html>Nazwa: " + nazwa + "<br/>Data: " + date.toString() + "<br/>Miasto zalozenia: " + miasto_zalozenia + "<br/>Kraj zalozenia: " + kraj_zalozenia + "<br/>Gatunki:";
        string += "<br/></html>";
        return string;
    }
}
