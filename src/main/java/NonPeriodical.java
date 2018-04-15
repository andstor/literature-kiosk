/**
 * Represents non periodical literature.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public abstract class NonPeriodical extends Literature {

    protected String author;

    /**
     * Constructor for objects of class NonPeriodical.
     */
    public NonPeriodical(String title, String genre, String publisher, String author) {
        super(title, genre, publisher);
        this.author = author;
    }

    /**
     * Gets the author of this book.
     *
     * @return the author of this book
     */
    public String getAuthor() {
        return author;
    }
}
