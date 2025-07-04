// Lot.java
/** Klasa reprezentująca pojedynczy lot. */
public class Lot {
    String numerLotu;
    String miastoStart;
    String miastoKoniec;
    String klasa;
    double cena;
    int iloscMiejsc;

    public Lot(String numerLotu, String miastoStart, String miastoKoniec, String klasa, double cena, int iloscMiejsc) {
        this.numerLotu = numerLotu;
        this.miastoStart = miastoStart;
        this.miastoKoniec = miastoKoniec;
        this.klasa = klasa;
        this.cena = cena;
        this.iloscMiejsc = iloscMiejsc;
    }
}
