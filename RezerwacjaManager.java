// RezerwacjaManager.java
import java.io.*;
import java.util.*;

/** Zarządza rezerwacjami – dodawanie, zapis, wyświetlanie. */
public class RezerwacjaManager {
    private List<Rezerwacja> rezerwacje = new ArrayList<>();
    private final String PLIK = "rezerwacje.csv";

    public void wczytajRezerwacje() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PLIK))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");
                rezerwacje.add(new Rezerwacja(dane[0], dane[1], Integer.parseInt(dane[2]), Double.parseDouble(dane[3])));
            }
        } catch (IOException e) {
            System.out.println("Brak pliku rezerwacji – utworzono nowy.");
        }
    }

    public void zarezerwujBilet(Scanner scanner, LotManager loty, Statystyki statystyki) {
        try {
            System.out.print("Podaj imię i nazwisko: ");
            String imie = scanner.nextLine();
            System.out.print("Podaj numer lotu: ");
            String numer = scanner.nextLine();
            Lot lot = loty.znajdzLot(numer);

            if (lot == null) {
                System.out.println("Nie znaleziono lotu.");
                return;
            }

            System.out.print("Ile miejsc chcesz zarezerwować: ");
            int ile = Integer.parseInt(scanner.nextLine());

            if (ile <= 0 || ile > lot.iloscMiejsc) {
                System.out.println("Błędna liczba miejsc.");
                return;
            }

            lot.iloscMiejsc -= ile;
            double cena = ile * lot.cena;
            Rezerwacja r = new Rezerwacja(imie, numer, ile, cena);
            rezerwacje.add(r);
            statystyki.dodajRezerwacje();

            try (FileWriter fw = new FileWriter(PLIK, true)) {
                fw.write(r.toCsv() + "\n");
                System.out.println("Rezerwacja zakończona sukcesem.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Błąd podczas rezerwacji: " + e.getMessage());
        }
    }

    public void wyswietlRezerwacje() {
        if (rezerwacje.isEmpty()) {
            System.out.println("Brak rezerwacji.");
        } else {
            System.out.println("\n=== REZERWACJE ===");
            for (Rezerwacja r : rezerwacje) {
                System.out.println(r);
            }
        }
    }

    public void zapiszDane() {
        try (PrintWriter pw = new PrintWriter(PLIK)) {
            for (Rezerwacja r : rezerwacje) {
                pw.println(r.toCsv());
            }
            System.out.println("Zapisano dane do pliku.");
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }
}