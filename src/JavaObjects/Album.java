package JavaObjects;

import java.sql.Date;

public class Album {
    private String nazwa;
    private String jezyk;
    private float ocena;
    private Date dataWydania;

    public Album(String nazwa, Date dataWydania, float ocena, String jezyk){
        this.nazwa = nazwa;
        this.dataWydania = dataWydania;
        this.ocena = ocena;
        this.jezyk = jezyk;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getJezyk() {
        return jezyk;
    }

    public void setJezyk(String jezyk) {
        this.jezyk = jezyk;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public Date getDate() {
        return dataWydania;
    }

    public void setDate(Date dataWydania) {
        this.dataWydania = dataWydania;
    }

    @Override
    public String toString() {
        return "Nazwa: " + nazwa + "\nData wydania: " + dataWydania + "\nOcena: " + ocena + "\nJezyk: " + jezyk + "\n\n";
    }
}
