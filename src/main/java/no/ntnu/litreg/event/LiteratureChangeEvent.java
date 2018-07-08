package no.ntnu.litreg.event;

import no.ntnu.litreg.Literature;

/**
 * @author André Storhaug
 * @version 4.0.0
 */
public class LiteratureChangeEvent extends LiteratureEvent {
    private Literature updatedLiterature;
    private Boolean isEditable;

    public LiteratureChangeEvent (Object source, Literature updatedLiterature, Boolean isEditable) {
        super(source);
        this.updatedLiterature = updatedLiterature;
        this.isEditable = isEditable;
    }

    public Literature getUpdatedLiterature() {
        return updatedLiterature;
    }

    public Boolean isEditable() {
        return isEditable;
    }
}