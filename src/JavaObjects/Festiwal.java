package JavaObjects;

import java.sql.Date;

public class Festiwal {
    private String nazwa;
    private Date dataRozpoczecia, dataZakonczenia;

    public Festiwal(String nazwa, Date dataRozpoczecia, Date dataZakonczenia) {
        this.nazwa = nazwa;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    @Override
    public String toString() {
        return "Festiwal: " +
                "\nNazwa: " + nazwa +
                "\nDataRozpoczecia: " + dataRozpoczecia +
                "\nDataZakonczenia: " + dataZakonczenia +
                "\n\n";
    }
}
