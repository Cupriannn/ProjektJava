// Rezerwacja.java
/** Klasa przechowująca dane rezerwacji. */
public class Rezerwacja {
    private static int licznik = 1; // automatyczny licznik ID
    int id;
    String imie;
    String numerLotu;
    int iloscMiejsc;
    double lacznaCena;

    public Rezerwacja(String imie, String numerLotu, int iloscMiejsc, double lacznaCena) {
        this.id = licznik++;
        this.imie = imie;
        this.numerLotu = numerLotu;
        this.iloscMiejsc = iloscMiejsc;
        this.lacznaCena = lacznaCena;
    }

    // Konstruktor do wczytywania z pliku z ID
    public Rezerwacja(int id, String imie, String numerLotu, int iloscMiejsc, double lacznaCena) {
        this.id = id;
        this.imie = imie;
        this.numerLotu = numerLotu;
        this.iloscMiejsc = iloscMiejsc;
        this.lacznaCena = lacznaCena;
        if (id >= licznik) licznik = id + 1;
    }

    public String toString() {
        return id + ", " + imie + ", " + numerLotu + ", " + iloscMiejsc + ", " + lacznaCena;
    }

    public String toCsv() {
        return id + "," + imie + "," + numerLotu + "," + iloscMiejsc + "," + lacznaCena;
    }
}