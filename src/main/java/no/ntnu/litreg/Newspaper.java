package no.ntnu.litreg;

/**
 * Represents a single newspaper.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class Newspaper extends Periodical {

    private static final String TYPE = "newspaper";

    /**
     * Constructor for objects of class Newspaper.
     *
     * @param title              the title  of this magazine
     * @param publisher          the publisher of this newspaper
     * @param publicationsYearly the release date of this newspaper newspaper
     * @param genre              the genre of this newspaper
     */
    public Newspaper(String title, String publisher, int publicationsYearly, String genre) {
        super(title, genre, publisher, publicationsYearly);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}