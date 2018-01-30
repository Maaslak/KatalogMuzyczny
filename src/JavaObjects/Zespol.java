package JavaObjects;

import java.sql.Date;
import java.time.LocalDate;

public class Zespol {
    private String nazwa, miasto_zalozenia, kraj_zalozenia;
    private Date date;

    public Zespol(String nazwa, Date date, String miasto_zalozenia, String kraj_zalozenia){
        this.nazwa = nazwa;
        this.date = date;
        this.miasto_zalozenia = miasto_zalozenia;
        this.kraj_zalozenia = kraj_zalozenia;
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

    @Override
    public String toString() {
        return "Nazwa: " + nazwa + "\nData: " + date.toString() + "\nMiasto zalozenia: " + miasto_zalozenia + "\nKraj zalozenia: " + kraj_zalozenia + "\n\n";
    }
}
