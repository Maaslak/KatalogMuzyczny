package JavaObjects;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Utwor {
    private String tytul;
    private Timestamp czas;
    private String formatedTimestamp;
    private java.text.SimpleDateFormat format;

    public Utwor(String tytul, Timestamp czas) {
        this.tytul = tytul;
        this.czas = czas;
        format = new SimpleDateFormat("mm:ss");
        formatTimestamp();
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    /*public Timestamp getCzas() {
        return czas;
    }*/

    public void setCzas(Timestamp czas) {
        this.czas = czas;
        formatTimestamp();
    }

    public String getFormatedTimestamp() {
        return formatedTimestamp;
    }

    public void setFormatedTimestamp(String formatedTimestamp) {
        this.formatedTimestamp = formatedTimestamp;
    }

    public void formatTimestamp(){
        formatedTimestamp = format.format(this.czas);
    }

    @Override
    public String toString() {
        return "<html>Tytul: " + tytul + "<br/>Czas: " + formatedTimestamp + "<br/><br/></html>";
    }

    public Timestamp getCzas() {
        return czas;
    }
}
