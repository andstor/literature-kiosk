public class Literature {
    protected String title;
    protected String publisher;
    protected String type;
    protected String genre;

    public Literature(String type, String title, String genre, String publisher) {
        this.type = type;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
    }

    /**
     * Returns the title of this magazine.
     *
     * @return the title of this magazine
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the publisher's name.
     *
     * @return the publisher's name
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * Returns the type of reading material.
     *
     * @return the type of reading material
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the genre number of this magazine.
     *
     * @return the genre number of this magazine
     */
    public String getGenre() {
        return genre;
    }

        
    /**
     * Set the title of this magazine
     *
     * @param title the title of this magazine
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the publisher of this magazine.
     *
     * @param publisher the publisher of this magazine
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Set the literature type of this magazine.
     *
     * @param type this type of magazine
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set the genre of this magazine.
     *
     * @param genre the genre of this magazine
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
