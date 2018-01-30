package JavaObjects;


import java.sql.Date;

public class Utwor {
    String tytul;
    Date czas;

    public Utwor(String tytul, Date czas) {
        this.tytul = tytul;
        this.czas = czas;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Date getCzas() {
        return czas;
    }

    public void setCzas(Date czas) {
        this.czas = czas;
    }

    @Override
    public String toString() {
        return "Tytul" + tytul + "\nCzas: " + czas.getMinutes() + ':' + czas.getSeconds() + "\n\n";

    }
}
