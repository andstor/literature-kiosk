import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewspaperTest {
    private Newspaper newspaper;

    @BeforeEach
    void setUp() {
        newspaper = new Newspaper("title", "publisher", 100, "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPublicationsYearly() {
        assertEquals(100, newspaper.getPublicationsYearly());
    }

    @Test
    void getType() {
        assertEquals("newspaper", newspaper.getType());
    }
}