package service;

import model.Event;
import java.util.ArrayList;
import java.util.List;

/**
 * Zarządza wydarzeniami i rezerwacjami.
 */
public class EventManager {
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }
}