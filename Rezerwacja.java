// Rezerwacja.java
/** Klasa przechowująca dane rezerwacji. */
public class Rezerwacja {
    String imie;
    String numerLotu;
    int iloscMiejsc;
    double lacznaCena;

    public Rezerwacja(String imie, String numerLotu, int iloscMiejsc, double lacznaCena) {
        this.imie = imie;
        this.numerLotu = numerLotu;
        this.iloscMiejsc = iloscMiejsc;
        this.lacznaCena = lacznaCena;
    }

    public String toString() {
        return imie + ", " + numerLotu + ", " + iloscMiejsc + ", " + lacznaCena;
    }

    public String toCsv() {
        return imie + "," + numerLotu + "," + iloscMiejsc + "," + lacznaCena;
    }
}