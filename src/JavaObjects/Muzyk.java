package JavaObjects;

import java.sql.Date;

public class Muzyk {
    String imie, nazwisko, pochodzenie;
    Date dataUrodzenia;

    public Muzyk(String imie, String nazwisko, Date dataUrodzenia, String pochodzenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pochodzenie = pochodzenie;
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPochodzenie() {
        return pochodzenie;
    }

    public void setPochodzenie(String pochodzenie) {
        this.pochodzenie = pochodzenie;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}
