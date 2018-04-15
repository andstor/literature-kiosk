import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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