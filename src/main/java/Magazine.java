/**
 * Represents a single magazine.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 1.0
 */
public class Magazine {
    private String title;
    private String publisher;
    private int publicationsYearly;
    private String type;
    private String genre;

    /**
     * Constructor for objects of class Magazine.
     * @param title the title  of this magazine
     * @param publisher the publisher of this magazine
     * @param publicationsYearly the release date of this magazine magazine
     * @param type the amount of pages of this magazine
     * @param genre the genre of this magazine
     */
    public Magazine(String title, String publisher, int publicationsYearly, String type, String genre) {
        this.title = title;
        this.publisher = publisher;
        this.publicationsYearly = publicationsYearly;
        this.type = type;
        this.genre = genre;
    }


    /**
     * Returns the title of this magazine.
     * @return the title of this magazine
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the publisher's name.
     * @return the publisher's name
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * Returns the release date of the magazine.
     * @return the release date of the magazine
     */
    public int getPublicationsYearly() {
        return publicationsYearly;
    }

    /**
     * Returns the type of reading material.
     * @return the type of reading material
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the genre number of this magazine.
     * @return the genre number of this magazine
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the title of this magazine
     * @param title the title of this magazine
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the publisher of this magazine.
     * @param publisher the publisher of this magazine
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Set the number of yearly publications of this magazine.
     * @param publicationsYearly the number of yearly publications of this magazine
     */
    public void setPublicationsYearly(int publicationsYearly) {
        this.publicationsYearly = publicationsYearly;
    }

    /**
     * Set the literature type of this magazine.
     * @param type this type of magazine
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set the genre of this magazine.
     * @param genre the genre of this magazine
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
}