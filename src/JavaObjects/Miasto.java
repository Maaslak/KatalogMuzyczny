package JavaObjects;

public class Miasto {
    String nazwa;
    int polozenieX, polozenieY;

    public Miasto(String nazwa, int polozenieX, int polozenieY) {
        this.nazwa = nazwa;
        this.polozenieX = polozenieX;
        this.polozenieY = polozenieY;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPolozenieX() {
        return polozenieX;
    }

    public void setPolozenieX(int polozenieX) {
        this.polozenieX = polozenieX;
    }

    public int getPolozenieY() {
        return polozenieY;
    }

    public void setPolozenieY(int polozenieY) {
        this.polozenieY = polozenieY;
    }

    @Override
    public String toString() {
        return nazwa;
    }
}
