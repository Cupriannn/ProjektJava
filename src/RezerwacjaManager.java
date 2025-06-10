// RezerwacjaManager.java
import java.io.*;
import java.util.*;

/** Zarządza rezerwacjami – dodawanie, zapis, wyświetlanie. */
public class RezerwacjaManager {
    private List<Rezerwacja> rezerwacje = new ArrayList<>();
    private final String PLIK = "dane/rezerwacje.csv";

    public void wczytajRezerwacje() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PLIK))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");
                rezerwacje.add(new Rezerwacja(
                    Integer.parseInt(dane[0]),dane[1],dane[2],Integer.parseInt(dane[3]),Double.parseDouble(dane[4])));
            }
        } catch (IOException e) {
            System.out.println("Brak pliku rezerwacji – utworzono nowy.");
        }
    }

    public void zarezerwujBilet(Scanner scanner, LotManager loty, Statystyki statystyki) {
        try {
            System.out.print("Podaj nazwe klienta: ");
            String imie = scanner.nextLine();
            System.out.print("Podaj numer lotu(format LO###): ");
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
            cena = Math.round(cena * 100.0) / 100.0;
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

    public void usunBilet() {
    if (rezerwacje.isEmpty()) {
        System.out.println("Brak rezerwacji do usunięcia.");
        return;
    }
    Scanner scanner = new Scanner(System.in);
    System.out.print("Podaj ID rezerwacji do usunięcia: ");
    try {
        int id = Integer.parseInt(scanner.nextLine());
        boolean znaleziono = false;
        Iterator<Rezerwacja> it = rezerwacje.iterator();
        while (it.hasNext()) {
            Rezerwacja r = it.next();
            if (r.id == id) {
                it.remove();
                System.out.println("Rezerwacja o ID " + id + " została usunięta.");
                // Nadpisz plik po usunięciu
                zapiszDane();
                znaleziono = true;
                break;
            }
        }
        if (!znaleziono) {
            System.out.println("Nie znaleziono rezerwacji o podanym ID.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Błędny format ID.");
    }
}

    public void wyswietlRezerwacje() {
    if (rezerwacje.isEmpty()) {
        System.out.println("Brak rezerwacji.");
    } else {
        System.out.println("\n=== REZERWACJE ===");
        System.out.println("+-----+-------------------------------+--------------+----------------+--------------+");
        System.out.println("|  ID | Nazwa Klienta                 | Numer lotu   | Ilość miejsc   | Łączna cena  |");
        System.out.println("+-----+-------------------------------+--------------+----------------+--------------+");
        for (Rezerwacja r : rezerwacje) {
            System.out.printf("| %3d | %-29s | %-12s | %14d | %12.2f |\n",
                              r.id, r.imie, r.numerLotu, r.iloscMiejsc, r.lacznaCena);
        }
        System.out.println("+-----+-------------------------------+--------------+----------------+--------------+");
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