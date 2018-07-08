package no.ntnu.litreg;

/**
 * Represents a abstract literature.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public abstract class Literature {
    private String title;
    private String publisher;
    private String genre;
    private String type;


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
        this.type = getType();
    }

    /**
     * Returns the type of reading material.
     *
     * @return the type of reading material
     */
    public abstract String getType();

    /**
     * Returns the title of this literature.
     *
     * @return the title of this literature
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
     * Returns the genre number of this literature.
     *
     * @return the genre number of this literature
     */
    public String getGenre() {
        return genre;
    }


    //TODO check this ->>>

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
