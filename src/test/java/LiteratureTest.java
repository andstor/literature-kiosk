import no.ntnu.litreg.Literature;
import no.ntnu.litreg.Magazine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author André Storhaug
 * @version 4.0.0
 */
class LiteratureTest {
    private Literature literature;

    @BeforeEach
    void setUp() {
        literature = new Magazine("title", "publisher", 100, "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTitle() {
        assertEquals("title", literature.getTitle());
    }



    @Test
    void getPublisher() {
        assertEquals("publisher", literature.getPublisher());
    }



    @Test
    void getGenre() {
        assertEquals("genre", literature.getGenre());
    }



}