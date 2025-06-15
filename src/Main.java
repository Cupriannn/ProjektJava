// Main.java
import java.util.*;

import service.LotManager;
import service.RezerwacjaManager;
import service.Statystyki;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LotManager lotManager = new LotManager();
        RezerwacjaManager rezerwacjaManager = new RezerwacjaManager();
        Statystyki statystyki = new Statystyki();

        long startCzasu = System.currentTimeMillis();
        lotManager.wczytajLoty("dane/loty_duzo.csv");

        rezerwacjaManager.wczytajRezerwacje();

        boolean dziala = true;
        while (dziala) {
            System.out.println("\n=== SYSTEM REZERWACJI LOTÓW ===");
            System.out.println("1. Wyświetl wszystkie loty (tabela)");
            System.out.println("2. Wyszukaj loty (miasto/kierunek)");
            System.out.println("3. Zarezerwuj bilet");
            System.out.println("4. Usuń rezerwacje");
            System.out.println("5. Wyświetl rezerwacje");
            System.out.println("6. Zapisz dane do pliku");
            System.out.println("7. Statystyki programu");
            System.out.println("8. Zakończ");
            System.out.print("Wybierz opcję: ");

            try {
                int wybor = Integer.parseInt(scanner.nextLine());
                switch (wybor) {
                    case 1:
                        lotManager.wyswietlLotyTabela();
                        statystyki.dodajWyswietlenia();
                        break;
                    case 2:
                        lotManager.wyszukajLoty(scanner);
                        break;
                    case 3:
                        rezerwacjaManager.zarezerwujBilet(scanner, lotManager, statystyki);
                        break;
                    case 4:
                        rezerwacjaManager.usunBilet(scanner, lotManager);
                        break;    
                    case 5:
                        rezerwacjaManager.wyswietlRezerwacje();
                        break;
                    case 6:
                        rezerwacjaManager.zapiszDane();
                        break;
                    case 7:
                        statystyki.wyswietl();
                        break;
                    case 8:
                        dziala = false;
                        long czasDzialania = System.currentTimeMillis() - startCzasu;
                        System.out.printf("\nProgram działał przez %.2f sekund.\n", czasDzialania / 1000.0);
                        System.out.println("Do zobaczenia!");
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja. Wybierz 1-8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wpisz liczbę od 1 do 8!");
            }
        }
    }
}
