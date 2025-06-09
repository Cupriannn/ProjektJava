/**
 * Klasa reprezentująca rezerwację biletu.
 */
public class Rezerwacja {
    String imieNazwisko;
    String numerLotu;
    int iloscZarezerwowanych;
    double lacznaCena;

    public Rezerwacja(String imieNazwisko, String numerLotu, int iloscZarezerwowanych, double lacznaCena) {
        this.imieNazwisko = imieNazwisko;
        this.numerLotu = numerLotu;
        this.iloscZarezerwowanych = iloscZarezerwowanych;
        this.lacznaCena = lacznaCena;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-8s | %2d miejsc | %.2f zł", 
            imieNazwisko, numerLotu, iloscZarezerwowanych, lacznaCena);
    }

    public String toCsv() {
        return imieNazwisko + "," + numerLotu + "," + iloscZarezerwowanych + "," + lacznaCena;
    }
}