/**
 * Represents a single magazine.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 2.0.1
 */
public class Magazine extends Literature {

    protected int publicationsYearly;

    /**
     * Constructor for objects of class Magazine.
     *
     * @param title              the title  of this magazine
     * @param publisher          the publisher of this magazine
     * @param publicationsYearly the release date of this magazine magazine
     * @param type               the amount of pages of this magazine
     * @param genre              the genre of this magazine
     */
    public Magazine(String title, String publisher, int publicationsYearly, String type, String genre) {
        super(type, title, genre, publisher);
        this.publicationsYearly = publicationsYearly;

    }


    /**
     * Returns the release date of the magazine.
     *
     * @return the release date of the magazine
     */
    public int getPublicationsYearly() {
        return publicationsYearly;
    }

    /**
     * Set the number of yearly publications of this magazine.
     *
     * @param publicationsYearly the number of yearly publications of this magazine
     */
    public void setPublicationsYearly(int publicationsYearly) {
        this.publicationsYearly = publicationsYearly;
    }
}