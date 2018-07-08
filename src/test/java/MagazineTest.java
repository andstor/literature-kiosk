import no.ntnu.litreg.Magazine;
import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;

class MagazineTest {
    private Magazine magazine;

    @BeforeEach
    void setUp() {
        magazine = new Magazine("title", "publisher", 100, "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPublicationsYearly() {
        assertEquals(100, magazine.getPublicationsYearly());
    }

    @Test
    void getType() {
        assertEquals("magazine", magazine.getType());
    }
}