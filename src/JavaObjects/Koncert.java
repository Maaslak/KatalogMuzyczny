package JavaObjects;

import java.sql.Date;

public class Koncert {
    private String nazwa, miasto_nazwa;
    private Date data;

    public Koncert(String nazwa, Date data, String miasto_nazwa) {
        this.nazwa = nazwa;
        this.miasto_nazwa = miasto_nazwa;
        this.data = data;
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

    @Override
    public String toString() {
        return "<html>Nazwa: " + nazwa + "<br/>Data: " + data + "<br/>Miasto: " + miasto_nazwa + "<br/><br/><html>";
    }
}
