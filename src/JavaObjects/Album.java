package JavaObjects;

import java.sql.Date;

public class Album {
    private String nazwa;
    private String jezyk;
    private Float ocena;
    private Date dataWydania;
    private Integer id;

    public Album(String nazwa, Date dataWydania, Float ocena, String jezyk, Integer id){
        this.nazwa = nazwa;
        this.dataWydania = dataWydania;
        this.ocena = ocena;
        this.jezyk = jezyk;
        this.id = id;
    }

    public Album(String nazwa, Date dataWydania, Float ocena, String jezyk){
        this(nazwa, dataWydania, ocena, jezyk, null);
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

    public Float getOcena() {
        return ocena;
    }

    public void setOcena(Float ocena) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "<html>Nazwa: " + nazwa + "<br/>Data wydania: " + dataWydania + "<br/>Ocena: " + ocena + "<br/>Jezyk: " + jezyk + "<br/><br/></html>";
    }
}
