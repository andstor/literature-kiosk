package no.ntnu.litreg;

/**
 * Represents a single book.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public class Book extends NonPeriodical {

    private static final String TYPE = "book";

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
    public Book(String title, String publisher, String genre, String author, int edition, String dateOfRelease, int pages) {
        super(title, genre, publisher, author);
        this.edition = edition;
        this.dateOfRelease = dateOfRelease;
        this.pages = pages;
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


    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    /**
     * Returns this book's release date.
     *
     * @return the edition number of this book.
     */
    public String getDateOfRelease() {
        return dateOfRelease;
    }

    /**
     * Returns the number of this book's pages.
     *
     * @return the number of this book's pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * Creates a book series based on the information of this book.
     *
     * @return the created book series.
     */
    public Literature convertToSeries() {
        BookSeries bookSeries = new BookSeries(getTitle(), getPublisher(), getGenre(), getAuthor());
        bookSeries.addBookToSeries(this);
        return bookSeries;
    }
}