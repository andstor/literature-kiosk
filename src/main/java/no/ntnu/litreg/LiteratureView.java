package no.ntnu.litreg;

import javafx.scene.layout.Pane;
import no.ntnu.litreg.event.LiteratureEventListener;

/**
 * Interface for literature view.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public interface LiteratureView {

    Pane buildUIView(Literature literature);

    void setEditable(Boolean editable);

    void addEventHandler(LiteratureEventListener listener);
}
