package JavaObjects;

import java.sql.Date;

public class Koncert {
    private String nazwa, miasto_nazwa;
    private Date data;
    private int zespolId;
    private int festiwalId;

    public Koncert(String nazwa, Date data, String miasto_nazwa, int zespolId) {
        this.nazwa = nazwa;
        this.miasto_nazwa = miasto_nazwa;
        this.data = data;
        this.zespolId = zespolId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getMiasto_nazwa() {
        return miasto_nazwa;
    }

    public void setMiasto_nazwa(String miasto_nazwa) {
        this.miasto_nazwa = miasto_nazwa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getZespolId() {
        return zespolId;
    }

    public void setZespolId(int zespolId) {
        this.zespolId = zespolId;
    }

    @Override
    public String toString() {
        return "<html>Nazwa: " + nazwa + "<br/>Data: " + data + "<br/>Miasto: " + miasto_nazwa + "<br/><br/><html>";
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString() == obj.toString();
    }
}
