package JavaObjects;

import java.sql.Date;

public class Album {
    private String nazwa;
    private String jezyk;
    private Float ocena;
    private Date dataWydania;
    private Integer id;
    private Integer zespolId;

    public Album(String nazwa, Date dataWydania, Float ocena, String jezyk, Integer id, Integer zespolId){
        this.nazwa = nazwa;
        this.dataWydania = dataWydania;
        this.ocena = ocena;
        this.jezyk = jezyk;
        this.id = id;
        this.zespolId = zespolId;
    }

    public Album(String nazwa, Date dataWydania, Float ocena, String jezyk){
        this(nazwa, dataWydania, ocena, jezyk, null, null);
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

    public Integer getZespolId() {
        return zespolId;
    }

    public void setZespolId(Integer zespolId) {
        this.zespolId = zespolId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "<html>Name: " + nazwa;
    }

    public String getInfo(){
        String info = "<html>Name: " + nazwa;
        if(dataWydania != null)
            info += "<br/>Recorded date: " + dataWydania;
        if(ocena != null)
            info += "<br/>Rateing: " + ocena;
        if(jezyk != null)
            info += "<br/>Language: " + jezyk;
        return info += "</html>";
    }
}
