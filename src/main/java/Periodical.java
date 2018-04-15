/**
 * Represents periodical literature.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public abstract class Periodical extends Literature {

    private int publicationsYearly;
    /**
     * Constructor for objects of class Periodical.
     */
    public Periodical(String title, String genre, String publisher, int publicationsYearly) {
        super(title, genre, publisher);
        this.publicationsYearly = publicationsYearly;
    }

    /**
     * Gets the number of the periodical's yearly publications.
     *
     * @return the number of the periodical's yearly publications
     */
    public int getPublicationsYearly() {
        return publicationsYearly;
    }
}
