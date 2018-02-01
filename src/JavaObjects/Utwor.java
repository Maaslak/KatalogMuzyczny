package JavaObjects;


import java.sql.Date;
import java.sql.Timestamp;

public class Utwor {
    String tytul;
    Timestamp czas;

    public Utwor(String tytul, Timestamp czas) {
        this.tytul = tytul;
        this.czas = czas;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Timestamp getCzas() {
        return czas;
    }

    public void setCzas(Timestamp czas) {
        this.czas = czas;
    }

    @Override
    public String toString() {
        return "<html>Tytul: " + tytul + "<br/>Czas: " + czas + ':' + czas.toString() + "<br/><br/></html>";
    }
}
