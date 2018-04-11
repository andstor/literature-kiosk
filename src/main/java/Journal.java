/**
 * Represents a single magazine.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 2.0.1
 */
public class Journal extends Literature {

    private static final String TYPE = "journal";

    protected int publicationsYearly;

    /**
     * Constructor for objects of class Journal.
     *
     * @param title              the title  of this journal
     * @param publisher          the publisher of this journal
     * @param publicationsYearly the release date of this journal journal
     * @param genre              the genre of this journal
     */
    public Journal(String title, String publisher, int publicationsYearly, String genre) {
        super(title, genre, publisher);
        this.publicationsYearly = publicationsYearly;

    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Returns the release date of the journal.
     *
     * @return the release date of the journal
     */
    public int getPublicationsYearly() {
        return publicationsYearly;
    }

    /**
     * Set the number of yearly publications of this journal.
     *
     * @param publicationsYearly the number of yearly publications of this journal
     */
    public void setPublicationsYearly(int publicationsYearly) {
        this.publicationsYearly = publicationsYearly;
    }
}