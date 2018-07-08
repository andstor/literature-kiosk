package no.ntnu.litreg.event;

import no.ntnu.litreg.Literature;
import no.ntnu.litreg.event.LiteratureEvent;

/**
 * @author André Storhaug
 * @version 4.0.0
 */
public class LiteratureDeleteEvent extends LiteratureEvent {
    private Literature deletedLiterature;

    public LiteratureDeleteEvent(Object source, Literature deletedLiterature) {
        super(source);
        this.deletedLiterature = deletedLiterature;
    }

    public Literature getDeletedLiterature() {
        return deletedLiterature;
    }
}