package no.ntnu.litreg;

/**
 * Represents a single magazine.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public class Magazine extends Periodical {

    private static final String TYPE = "magazine";

    /**
     * Constructor for objects of class Magazine.
     *
     * @param title              the title  of this magazine
     * @param publisher          the publisher of this magazine
     * @param publicationsYearly the release date of this magazine magazine
     * @param genre              the genre of this magazine
     */
    public Magazine(String title, String publisher, int publicationsYearly, String genre) {
        super(title, genre, publisher, publicationsYearly);

    }

    @Override
    public String getType() {
        return TYPE;
    }


}