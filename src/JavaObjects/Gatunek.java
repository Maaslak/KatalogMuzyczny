package JavaObjects;

public class Gatunek {
    String nazwa, opis;

    public Gatunek(String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return nazwa;
        /*return "<html>Gatunek" +
                "Nazwa: " + nazwa + "<br/>" +
                "Opis: '" + opis + "<br/></html>";*/
    }
}
