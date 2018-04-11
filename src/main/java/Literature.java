public abstract class Literature {
    protected String title;
    protected String publisher;
    protected String genre;
    protected String type;


    /**
     * Constructor for objects of class Literature.
     *
     * @param title     the title  of this literature
     * @param publisher the publisher of this literature
     * @param genre     the genre of this literature
     */
    public Literature(String title, String genre, String publisher) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.setType();
    }

    /**
     * literature
     * Returns the type of reading material.
     *
     * @return the type of reading material
     */
    abstract public String getType();

    /**
     * Set the literature type of this literature.
     */
    private final void setType() {
        this.type = getType();
    }

    /**
     * Returns the title of this literature.
     *
     * @return the title of this literature
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title of this literature
     *
     * @param title the title of this literature
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Set the publisher of this literature.
     *
     * @param publisher the publisher of this literature
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Returns the genre number of this literature.
     *
     * @return the genre number of this literature
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the genre of this literature.
     *
     * @param genre the genre of this literature
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
