package model;

/**
 * Reprezentuje wydarzenie, na które można zarezerwować bilety.
 */
public class Event {
    private String name;
    private String date;
    private int totalSeats;
    private int reservedSeats;

    public Event(String name, String date, int totalSeats) {
        this.name = name;
        this.date = date;
        this.totalSeats = totalSeats;
        this.reservedSeats = 0;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public int getTotalSeats() { return totalSeats; }
    public int getReservedSeats() { return reservedSeats; }
    public int getAvailableSeats() { return totalSeats - reservedSeats; }

    public void reserveSeat() {
        if (reservedSeats < totalSeats) {
            reservedSeats++;
        }
    }

    public void cancelReservation() {
        if (reservedSeats > 0) {
            reservedSeats--;
        }
    }

    @Override
    public String toString() {
        return name + "," + date + "," + totalSeats + "," + reservedSeats;
    }
}