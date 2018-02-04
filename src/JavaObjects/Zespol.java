package JavaObjects;

import java.awt.*;
import java.sql.Date;

public class Zespol {
    private String nazwa, miasto_zalozenia, kraj_zalozenia;
    private Date date;
    private Integer id;

    public Zespol(String nazwa, Date date, String miasto_zalozenia, String kraj_zalozenia, Integer id){
        this.nazwa = nazwa;
        this.date = date;
        this.miasto_zalozenia = miasto_zalozenia;
        this.kraj_zalozenia = kraj_zalozenia;
        this.id = id;
    }
    public Zespol(String nazwa, Date date, String miasto_zalozenia, String kraj_zalozenia){
        this(nazwa, date, miasto_zalozenia, kraj_zalozenia, null);
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

    @Override
    public String toString() {
        return "<html>Nazwa: " + nazwa + "<br/>Data: " + date.toString() + "<br/>Miasto zalozenia: " + miasto_zalozenia + "<br/>Kraj zalozenia: " + kraj_zalozenia + "<br/><br/></html>";
    }
}
