import no.ntnu.litreg.Book;
import no.ntnu.litreg.BookSeries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author André Storhaug
 * @version 4.0.0
 */
class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("title", "publisher", "genre", "author", 1, "01/01/2000", 100);

    }

    @Test
    void getType() {
        assertEquals("book", book.getType());
    }

    @Test
    void getTypeNegative() {
        assertNotEquals("not a book", book.getType());
    }

    @Test
    void getEdition() {
        assertEquals(1, book.getEdition());
    }

    @Test
    void getDateOfRelease() {
        assertEquals("01/01/2000", book.getDateOfRelease());
    }

    @Test
    void getPages() {
        assertEquals(100, book.getPages());
    }

    @Test
    void convertToSeries() {
        BookSeries bookSeries = new BookSeries("title", "publisher", "genre", "author");
        BookSeries newSeries = (BookSeries) book.convertToSeries();

        assertEquals(bookSeries.getTitle(), newSeries.getTitle());
        assertEquals(bookSeries.getPublisher(), newSeries.getPublisher());
        assertEquals(bookSeries.getGenre(), newSeries.getGenre());
        assertEquals(bookSeries.getAuthor(), newSeries.getAuthor());
    }
}