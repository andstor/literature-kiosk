import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagazineTest {

    @BeforeEach
    void setUp() {
        Magazine magazine = new Magazine("TV Guide", "TVSM inc", 150, "Magazine", "Television");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTitle() {
        assertEquals("TV Guide2", magazine.getTitle());
    }

    @Test
    void getPublisher() {
    }

    @Test
    void getPublicationsYearly() {
    }

    @Test
    void getType() {
    }

    @Test
    void getGenre() {
    }

    @Test
    void setTitle() {
    }

    @Test
    void setPublisher() {
    }

    @Test
    void setPublicationsYearly() {
    }

    @Test
    void setType() {
    }

    @Test
    void setGenre() {
    }

    @Test
    void getAllDetailsAsString() {
    }
}