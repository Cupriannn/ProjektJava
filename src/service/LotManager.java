package service;
// LotManager.java
import java.io.*;
import java.util.*;

import model.Lot;

/** Zarządza operacjami na lotach (wczytywanie, wyświetlanie, wyszukiwanie). */
public class LotManager {
    private List<Lot> loty = new ArrayList<>();

    public void wczytajLoty(String sciezka) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sciezka))) {
            reader.readLine(); // pomiń nagłówek
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");
                Lot lot = new Lot(dane[0], dane[1], dane[2], dane[3], 
                                  Double.parseDouble(dane[4]), 150);
                loty.add(lot);
            }
        } catch (IOException e) {
            System.out.println("Błąd wczytywania lotów: " + e.getMessage());
        }
    }

    public void wyswietlLotyTabela() {
        System.out.println("\nDOSTĘPNE LOTY:");
        System.out.println("+------------+----------------+----------------+-------------+------------+----------------+");
        System.out.println("| Numer lotu | Miasto wylotu  | Miasto przylotu| Klasa       | Cena (zł)  | Wolne miejsca  |");
        System.out.println("+------------+----------------+----------------+-------------+------------+----------------+");
        for (Lot lot : loty) {
            System.out.printf("| %-10s | %-14s | %-14s | %-11s | %10.2f | %14d |\n",
                              lot.numerLotu, lot.miastoStart, lot.miastoKoniec,
                              lot.klasa, lot.cena, lot.iloscMiejsc);
        }
        System.out.println("+------------+----------------+----------------+-------------+------------+----------------+");
    }

    public void wyszukajLoty(Scanner scanner) {
        System.out.print("Szukaj (miasto wylotu/przylotu), bez znakow specjalnych !!!: ");
        String fraza = scanner.nextLine().toLowerCase();
        boolean znaleziono = false;
        for (Lot lot : loty) {
            if (lot.miastoStart.toLowerCase().contains(fraza) ||
                lot.miastoKoniec.toLowerCase().contains(fraza)) {
                System.out.println(lot.numerLotu + " | " + lot.miastoStart + " -> " + lot.miastoKoniec);
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("Brak wyników.");
        }
    }

    public Lot znajdzLot(String numer) {
        for (Lot lot : loty) {
            if (lot.numerLotu.equalsIgnoreCase(numer)) return lot;
        }
        return null;
    }
}
