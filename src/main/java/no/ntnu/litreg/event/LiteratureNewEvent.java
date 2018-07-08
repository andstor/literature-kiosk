package no.ntnu.litreg.event;

import no.ntnu.litreg.Literature;

public class LiteratureNewEvent extends LiteratureEvent {
    private Literature newLiterature;

    public LiteratureNewEvent(Object source, Literature newLiterature) {
        super(source);
        this.newLiterature = newLiterature;
    }

    public Literature getNewLiterature() {
        return newLiterature;
    }
}