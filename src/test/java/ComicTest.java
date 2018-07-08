import no.ntnu.litreg.Comic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComicTest {
    private Comic comic;

    @BeforeEach
    void setUp() {
        comic = new Comic("title", "publisher", 100, "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPublicationsYearly() {
        assertEquals(100, comic.getPublicationsYearly());
    }

    @Test
    void getType() {
        assertEquals("comic", comic.getType());
    }
}