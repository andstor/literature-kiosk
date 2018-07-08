import no.ntnu.litreg.Journal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author André Storhaug
 * @version 4.0.0
 */
class JournalTest {
    private Journal journal;

    @BeforeEach
    void setUp() {
        journal = new Journal("title", "publisher", 100, "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPublicationsYearly() {
        assertEquals(100, journal.getPublicationsYearly());
    }

    @Test
    void getType() {
        assertEquals("journal", journal.getType());
    }
}