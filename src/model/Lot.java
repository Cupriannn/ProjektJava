package model;
// Lot.java
/** Klasa reprezentująca pojedynczy lot. */
public class Lot {
    public String numerLotu;
    public String miastoStart;
    public String miastoKoniec;
    public String klasa;
    public double cena;
    public int iloscMiejsc;

    public Lot(String numerLotu, String miastoStart, String miastoKoniec, String klasa, double cena, int iloscMiejsc) {
        this.numerLotu = numerLotu;
        this.miastoStart = miastoStart;
        this.miastoKoniec = miastoKoniec;
        this.klasa = klasa;
        this.cena = cena;
        this.iloscMiejsc = iloscMiejsc;
    }
}
