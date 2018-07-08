package no.ntnu.litreg;

/**
 * Represents a single comic.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public class Comic extends Periodical {

    private static final String TYPE = "comic";

    /**
     * Constructor for objects of class Comic.
     *
     * @param title              the title  of this comic
     * @param publisher          the publisher of this comic
     * @param publicationsYearly the release date of this magazine comic
     * @param genre              the genre of this comic
     */
    public Comic(String title, String publisher, int publicationsYearly, String genre) {
        super(title, genre, publisher, publicationsYearly);

    }

    @Override
    public String getType() {
        return TYPE;
    }
}