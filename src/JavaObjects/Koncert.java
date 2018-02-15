package JavaObjects;

import java.sql.Date;

public class Koncert {
    private String nazwa, miasto_nazwa;
    private Date data;
    private int zespolId;
    private Integer festiwalId;

    public Koncert(String nazwa, Date data, String miasto_nazwa, int zespolId, Integer festiwalId) {
        this.nazwa = nazwa;
        this.miasto_nazwa = miasto_nazwa;
        this.data = data;
        this.zespolId = zespolId;
        this.festiwalId = festiwalId;
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

    public int getZespolId() {
        return zespolId;
    }

    public void setZespolId(int zespolId) {
        this.zespolId = zespolId;
    }

    @Override
    public String toString() {
        return "<html>Name: " + nazwa + "<br/>Date: " + data + "<br/><br/><html>";
    }

    public String getInfo(){
        String info = "<html>Name: " + nazwa + "<br/>Date: " + data;
        if(miasto_nazwa != null)
            info += "<br/>City: " + miasto_nazwa;
        return  info + "<br/><br/><html>";
    }

    public Integer getFestiwalId() {
        return festiwalId;
    }

    public void setFestiwalId(int festiwalId) {
        this.festiwalId = festiwalId;
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString() == obj.toString();
    }
}
