# System Rezerwacji Lotów – Szczegółowy opis działania

## Cel programu
Program służy do zarządzania rezerwacjami biletów lotniczych. Pozwala użytkownikowi na przeglądanie dostępnych lotów, wyszukiwanie połączeń według miast, rezerwowanie miejsc, przeglądanie i usuwanie rezerwacji oraz generowanie statystyk dotyczących sesji.

---

## Funkcjonalności

### 1. Wyświetlanie dostępnych lotów
Po uruchomieniu programu użytkownik może wyświetlić listę wszystkich dostępnych lotów. Każdy lot zawiera następujące informacje:
- Numer lotu
- Miasto wylotu
- Miasto przylotu
- Data i godzina wylotu
- Klasa (ekonomiczna, biznesowa)
- Cena biletu
- Liczba dostępnych miejsc

Loty wczytywane są z pliku CSV, co umożliwia łatwą edycję i aktualizację oferty.

---

### 2. Wyszukiwanie lotów
Użytkownik może wyszukać loty według miasta wylotu lub przylotu. Program filtruje dostępne połączenia i wyświetla tylko te, które spełniają podane kryteria.

---

### 3. Rezerwacja biletu
Aby zarezerwować bilet, użytkownik wybiera numer lotu oraz liczbę miejsc do rezerwacji. Program sprawdza dostępność miejsc i w przypadku powodzenia:
- Zmniejsza liczbę wolnych miejsc w danym locie
- Zapisuje rezerwację do pliku `rezerwacje.csv` (zawiera ID rezerwacji, dane lotu, liczbę miejsc, datę rezerwacji)
- Wyświetla potwierdzenie rezerwacji

---

### 4. Przeglądanie rezerwacji
Użytkownik może w każdej chwili wyświetlić listę wszystkich swoich rezerwacji. Każda rezerwacja zawiera:
- Unikalny identyfikator
- Szczegóły lotu
- Liczbę zarezerwowanych miejsc
- Datę dokonania rezerwacji

---

### 5. Usuwanie rezerwacji
Program umożliwia usunięcie wybranej rezerwacji na podstawie jej ID. Po usunięciu rezerwacji liczba wolnych miejsc w danym locie zostaje zwiększona.

---

### 6. Statystyki sesji
Podczas działania programu zbierane są statystyki, takie jak:
- Liczba dokonanych rezerwacji
- Liczba wyświetleń dostępnych lotów
- Czas trwania bieżącej sesji

Statystyki te można wyświetlić w dowolnym momencie.

---

### 7. Zapis i odczyt danych
Wszystkie operacje na rezerwacjach i lotach są zapisywane do plików CSV, co pozwala na zachowanie danych między uruchomieniami programu. Program automatycznie tworzy pliki, jeśli nie istnieją.

---

## Obsługa programu

1. **Uruchomienie**  
   Program uruchamia się w trybie konsolowym. Po starcie wyświetla menu z dostępnymi opcjami.

2. **Nawigacja**  
   Użytkownik wybiera opcje, wpisując odpowiedni numer lub polecenie.

3. **Zakończenie pracy**  
   Po zakończeniu pracy program wyświetla podsumowanie sesji i zapisuje wszystkie zmiany.

---

## Przykładowy scenariusz użycia

1. Użytkownik uruchamia program.
2. Przegląda dostępne loty.
3. Wyszukuje lot z Warszawy do Londynu.
4. Rezerwuje 2 miejsca na wybrany lot.
5. Sprawdza swoje rezerwacje.
6. Usuwa jedną z rezerwacji.
7. Wyświetla statystyki sesji.
8. Kończy działanie programu.

---
