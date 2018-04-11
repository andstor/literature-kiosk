/**
 * Represents a single book.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 2.0.1
 */
public class Book extends Literature {

    private static final String TYPE = "book";

    protected int publicationsYearly;
    private String dateOfRelease;
    private int pages;
    private int edition;

    /**
     * Constructor for objects of class book.
     *
     * @param title     the title  of this book
     * @param publisher the publisher of this book
     * @param genre     the genre of this book
     */
    public Book(String title, String publisher, int publicationsYearly, String genre, String dateOfRelease, int pages, int edition) {
        super(title, genre, publisher);
        this.publicationsYearly = publicationsYearly;
        this.dateOfRelease = dateOfRelease;
        this.pages = pages;
        this.edition = edition;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Returns the edition number of this book.
     *
     * @return the edition number of this book.
     */
    public int getEdition() {
        return edition;
    }


    public int getPages() {
        return pages;
    }


    public String getDateOfRelease() {
        return dateOfRelease;
    }
}