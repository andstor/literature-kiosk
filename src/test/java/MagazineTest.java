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

    @Test
    void setPublicationsYearly() {
        magazine.setPublicationsYearly(1);
        assertEquals(1, magazine.getPublicationsYearly());

        magazine.setPublicationsYearly(365);
        assertEquals(365, magazine.getPublicationsYearly());
    }

}