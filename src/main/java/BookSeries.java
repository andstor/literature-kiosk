import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a book series.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 2.0.1
 */
public class BookSeries extends Literature {

    private static final String TYPE = "book series";

    private ArrayList<Book> listOfBooksInSeries;
    private String author;

    /**
     * Constructor for objects of class BookSeries.
     *
     * @param title     the title  of this book series
     * @param publisher the publisher of this book series
     * @param genre     the genre of this book series
     * @param author    The author of this book series.
     * @param book      A list of the books in this series.
     */
    public BookSeries(String title, String publisher, String genre, String author, Book book) {
        super(title, genre, publisher);
        this.listOfBooksInSeries = new ArrayList<>();
        this.author = author;
        addBookToSeries(book);
    }

    /**
     * Constructor for objects of class BookSeries.
     *
     * @param title     the title  of this book series
     * @param publisher the publisher of this book series
     * @param genre     the genre of this book series
     * @param author    The author of this book series.
     * @param books     A list of the books in this series.
     */
    public BookSeries(String title, String publisher, String genre, String author, Iterator<Book> books) {
        super(title, genre, publisher);
        this.listOfBooksInSeries = new ArrayList<>();
        this.author = author;
        addBooksToSeries(books);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Adds a book to this book series.
     *
     * @param book This
     */
    public void addBookToSeries(Book book) {
        this.listOfBooksInSeries.add(book);
    }

    /**
     * Adds several books to this book series.
     *
     * @param books
     */
    public void addBooksToSeries(Iterator<Book> books) {
        while (books.hasNext()) {
            Book book = books.next();
            addBookToSeries(book);
        }
    }

    /**
     * Removes a book from this book series.
     *
     * @param book
     */
    public void removeBookFromSeries(Book book) {
        listOfBooksInSeries.remove(book);
    }

    /**
     * Returns a collection (ArrayList<Book>) of the books in this book series.
     *
     * @return a collection (ArrayList<Book>) of the books in this book series.
     */
    public ArrayList<Book> getListOfBooksInSeries() {
        return listOfBooksInSeries;
    }
}