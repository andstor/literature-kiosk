package no.ntnu.litreg.event;

import java.util.EventListener;

public interface LiteratureEventListener extends EventListener {
    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    void updateLiterature(LiteratureEvent event);
}

