// Statystyki.java
/** Klasa zliczająca operacje wykonane przez użytkownika. */
public class Statystyki {
    private int liczbaRezerwacji = 0;
    private int liczbaWyswietlen = 0;

    public void dodajRezerwacje() {
        liczbaRezerwacji++;
    }

    public void dodajWyswietlenia() {
        liczbaWyswietlen++;
    }

    public void wyswietl() {
        System.out.println("\n=== STATYSTYKI ===");
        System.out.println("Liczba rezerwacji: " + liczbaRezerwacji);
        System.out.println("Liczba wyświetleń lotów: " + liczbaWyswietlen);
    }
}
