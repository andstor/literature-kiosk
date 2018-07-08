package no.ntnu.litreg.event;

import java.util.EventListener;

/**
 * @author André Storhaug
 * @version 4.0.0
 */
public interface LiteratureNewEventListener extends LiteratureEventListener {
    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    void updateLiterature(LiteratureEvent event);
}


