import java.io.*;
import java.util.*;

/**
 * Główna klasa programu do rezerwacji biletów lotniczych.
 * Obsługuje rozbudowane menu, walidację danych i statystyki.
 */
public class Main {
    private static List<Lot> loty = new ArrayList<>();
    private static List<Rezerwacja> rezerwacje = new ArrayList<>();
    private static final String PLIK_REZERWACJE = "rezerwacje.csv";
    private static int liczbaRezerwacji = 0;
    private static int liczbaWyswietlen = 0;
    private static long startCzasu;

    public static void main(String[] args) {
        startCzasu = System.currentTimeMillis();
        wczytajLoty("dane/loty_duzo.csv");
        wczytajRezerwacje();

        Scanner scanner = new Scanner(System.in);
        boolean dziala = true;

        while (dziala) {
            System.out.println("\n=== SYSTEM REZERWACJI LOTÓW ===");
            System.out.println("1. Wyświetl wszystkie loty (tabela)");
            System.out.println("2. Wyszukaj loty (miasto/kierunek)");
            System.out.println("3. Zarezerwuj bilet");
            System.out.println("4. Wyświetl rezerwacje");
            System.out.println("5. Zapisz dane do pliku");
            System.out.println("6. Statystyki programu");
            System.out.println("7. Zakończ");
            System.out.print("Wybierz opcję: ");

            try {
                int wybor = Integer.parseInt(scanner.nextLine());
                switch (wybor) {
                    case 1:
                        wyswietlLotyTabela();
                        liczbaWyswietlen++;
                        break;
                    case 2:
                        wyszukajLoty(scanner);
                        break;
                    case 3:
                        zarezerwujBilet(scanner);
                        break;
                    case 4:
                        wyswietlRezerwacje();
                        break;
                    case 5:
                        zapiszDane();
                        break;
                    case 6:
                        wyswietlStatystyki();
                        break;
                    case 7:
                        dziala = false;
                        long czasDzialania = System.currentTimeMillis() - startCzasu;
                        System.out.printf("\nProgram działał przez %.2f sekund.\n", czasDzialania / 1000.0);
                        System.out.println("Do zobaczenia!");
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja. Wybierz 1-7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wpisz liczbę od 1 do 7!");
            }
        }
    }

    private static void wczytajLoty(String sciezka) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sciezka))) {
            String linia;
            reader.readLine(); // Pomijamy nagłówek
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");
                Lot lot = new Lot(dane[0], dane[1], dane[2], dane[3], 
                                Double.parseDouble(dane[4]), 150); // Domyślnie 150 miejsc
                loty.add(lot);
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania lotów: " + e.getMessage());
        }
    }

    private static void wczytajRezerwacje() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PLIK_REZERWACJE))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] dane = linia.split(",");
                Rezerwacja r = new Rezerwacja(dane[0], dane[1], 
                                            Integer.parseInt(dane[2]), 
                                            Double.parseDouble(dane[3]));
                rezerwacje.add(r);
                liczbaRezerwacji++;
            }
        } catch (IOException e) {
            System.out.println("Brak pliku rezerwacji. Utworzono nowy.");
        }
    }

    private static void wyswietlLotyTabela() {
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

    private static void wyszukajLoty(Scanner scanner) {
        System.out.print("Szukaj (miasto wylotu/przylotu): ");
        String fraza = scanner.nextLine().toLowerCase();

        List<Lot> wyniki = new ArrayList<>();
        for (Lot lot : loty) {
            if (lot.miastoStart.toLowerCase().contains(fraza) || 
                lot.miastoKoniec.toLowerCase().contains(fraza)) {
                wyniki.add(lot);
            }
        }

        if (wyniki.isEmpty()) {
            System.out.println("Brak wyników.");
        } else {
            System.out.println("\n=== WYNIKI WYSZUKIWANIA ===");
            for (Lot lot : wyniki) {
                System.out.println(lot.numerLotu + " | " + lot.miastoStart + " → " + lot.miastoKoniec);
            }
        }
    }

    private static void zarezerwujBilet(Scanner scanner) {
        try {
            System.out.print("Podaj imię i nazwisko: ");
            String imie = scanner.nextLine();
            System.out.print("Podaj numer lotu: ");
            String numerLotu = scanner.nextLine();

            Lot wybrany = null;
            for (Lot lot : loty) {
                if (lot.numerLotu.equalsIgnoreCase(numerLotu)) {
                    wybrany = lot;
                    break;
                }
            }

            if (wybrany == null) {
                System.out.println("Błąd: Nie znaleziono lotu!");
                return;
            }

            System.out.print("Podaj liczbę miejsc: ");
            int ile = Integer.parseInt(scanner.nextLine());

            if (ile <= 0) {
                System.out.println("Błąd: Liczba miejsc musi być większa od 0!");
            } else if (ile > wybrany.iloscMiejsc) {
                System.out.println("Błąd: Brak miejsc! Dostępne: " + wybrany.iloscMiejsc);
            } else {
                wybrany.iloscMiejsc -= ile;
                double lacznaCena = ile * wybrany.cena;
                Rezerwacja r = new Rezerwacja(imie, numerLotu, ile, lacznaCena);
                rezerwacje.add(r);
                liczbaRezerwacji++;

                try (FileWriter fw = new FileWriter(PLIK_REZERWACJE, true)) {
                    fw.write(r.toString() + "\n");
                    System.out.printf("Sukces! Zarezerwowano %d miejsc. Cena: %.2f zł\n", ile, lacznaCena);
                } catch (IOException e) {
                    System.out.println("Błąd zapisu rezerwacji.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Błąd: Wpisz poprawną liczbę miejsc!");
        }
    }

    private static void wyswietlRezerwacje() {
        if (rezerwacje.isEmpty()) {
            System.out.println("Brak rezerwacji.");
            return;
        }
        System.out.println("\n=== LISTA REZERWACJI ===");
        for (Rezerwacja r : rezerwacje) {
            System.out.println(r);
        }
    }

    private static void zapiszDane() {
        try (PrintWriter pw = new PrintWriter(PLIK_REZERWACJE)) {
            for (Rezerwacja r : rezerwacje) {
                pw.println(r.toCsv());
            }
            System.out.println("Dane zapisane do pliku " + PLIK_REZERWACJE);
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }

    private static void wyswietlStatystyki() {
        System.out.println("\n=== STATYSTYKI ===");
        System.out.println("Liczba rezerwacji: " + liczbaRezerwacji);
        System.out.println("Liczba wyświetleń lotów: " + liczbaWyswietlen);
    }
}