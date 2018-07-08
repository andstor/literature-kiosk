package no.ntnu.litreg;

import com.sun.istack.internal.NotNull;

/**
 * Factory for creating literature views.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class LiteratureViewFactory {

    private static LiteratureViewFactory instance = null;

    public LiteratureViewFactory() {
    }

    public static LiteratureViewFactory getInstance() {
        if (null == instance) {
            instance = new LiteratureViewFactory();
        }
        return instance;
    }


    /**
     * Creates the correct literature view based on the type passed as parameter.
     *
     * @param literature the type of literature to be created.
     * @return the type of literature view. If no match is found, <code>null</code> is returned.
     */
    public LiteratureView create(Literature literature) {
        LiteratureView literatureView;

        // Guard condition
        if (null == literature) {
            throw new NullPointerException("literature passed as parameter to LiteratureViewFactory can't be null");
        }


        if (literature instanceof Magazine) {
            literatureView = new MagazineView();
        } else if (literature instanceof Newspaper) {
            literatureView = new NewspaperView();
        } else if (literature instanceof Journal) {
            literatureView = new JournalView();
        } else if (literature instanceof Comic) {
            literatureView = new ComicView();
        } else if (literature instanceof Book) {
            literatureView = new BookView();
        } else if (literature instanceof BookSeries) {
            literatureView = new BookSeriesView();
        } else {
            throw new IllegalArgumentException("Not a valid literature type passed to LiteratureViewFactory.");
        }

        return literatureView;

    }
}