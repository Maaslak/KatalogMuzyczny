package JavaObjects;

import java.sql.Date;

public class Album {
    private String nazwa;
    private String jezyk;
    private float ocena;
    private Date dataWydania;
    private int id;

    public Album(String nazwa, Date dataWydania, float ocena, String jezyk, int id){
        this.nazwa = nazwa;
        this.dataWydania = dataWydania;
        this.ocena = ocena;
        this.jezyk = jezyk;
        this.id = id;
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

    public Date getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(Date dataWydania) {
        this.dataWydania = dataWydania;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "<html>Nazwa: " + nazwa + "<br/>Data wydania: " + dataWydania + "<br/>Ocena: " + ocena + "<br/>Jezyk: " + jezyk + "<br/><br/></html>";
    }
}
