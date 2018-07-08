package no.ntnu.litreg.event;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author André Storhaug
 * @version 4.0.0
 */
public class LiteratureEventGenerator {

    // Use CopyOnWriteArrayList to avoid ConcurrentModificationExceptions if a
    // listener attempts to remove itself during event notification.
    private final CopyOnWriteArrayList<LiteratureEventListener> listeners;

    public LiteratureEventGenerator() {
        this.listeners = new CopyOnWriteArrayList<>();
    }


    public void addListener(LiteratureEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(LiteratureEventListener listener) {
        this.listeners.remove(listener);
    }

    // Event firing method.  Called internally by other class methods.
    public void fireEvent(LiteratureEvent event) {

        for (LiteratureEventListener listener : listeners) {
            listener.updateLiterature(event);
        }
    }
}