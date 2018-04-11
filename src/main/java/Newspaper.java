/**
 * Represents a single magazine.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 2.0.1
 */
public class Newspaper extends Literature {

    private static final String TYPE = "newspaper";

    protected int publicationsYearly;

    /**
     * Constructor for objects of class Newspaper.
     *
     * @param title              the title  of this magazine
     * @param publisher          the publisher of this newspaper
     * @param publicationsYearly the release date of this newspaper newspaper
     * @param genre              the genre of this newspaper
     */
    public Newspaper(String title, String publisher, int publicationsYearly, String genre) {
        super(title, genre, publisher);
        this.publicationsYearly = publicationsYearly;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Returns the release date of the newspaper.
     *
     * @return the release date of the newspaper
     */
    public int getPublicationsYearly() {
        return publicationsYearly;
    }

    /**
     * Set the number of yearly publications of this newspaper.
     *
     * @param publicationsYearly the number of yearly publications of this newspaper
     */
    public void setPublicationsYearly(int publicationsYearly) {
        this.publicationsYearly = publicationsYearly;
    }


}