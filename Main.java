import model.Event;
import service.EventManager;

import java.util.Scanner;

/**
 * Główna klasa uruchamiająca system rezerwacji biletów.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager manager = new EventManager();

        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Dodaj wydarzenie");
            System.out.println("2. Wyświetl wydarzenia");
            System.out.println("0. Wyjdź");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // konsumuj newline

            switch (choice) {
                case 1:
                    System.out.print("Nazwa wydarzenia: ");
                    String name = scanner.nextLine();
                    System.out.print("Data (RRRR-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Liczba miejsc: ");
                    int seats = scanner.nextInt();
                    scanner.nextLine();
                    manager.addEvent(new Event(name, date, seats));
                    break;
                case 2:
                    for (Event e : manager.getEvents()) {
                        System.out.println(e.getName() + " | " + e.getDate() + " | Dostępne miejsca: " + e.getAvailableSeats());
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }
        }

        scanner.close();
    }
}