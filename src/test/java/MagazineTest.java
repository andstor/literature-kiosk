import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagazineTest {
    private Magazine magazine;

    @BeforeEach
    void setUp() {
        magazine = new Magazine("title", "publisher", 100, "type", "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTitle() {
        assertEquals("title", magazine.getTitle());
    }

    @Test
    void getPublisher() {
        assertEquals("publisher", magazine.getPublisher());

    }

    @Test
    void getPublicationsYearly() {
        assertEquals(100, magazine.getPublicationsYearly());
    }

    @Test
    void getType() {
        assertEquals("type", magazine.getType());
    }

    @Test
    void getGenre() {
        assertEquals("genre", magazine.getGenre());
    }

    @Test
    void setTitle() {
        magazine.setTitle("new title");
        assertEquals("new title", magazine.getTitle());
    }

    @Test
    void setPublisher() {
        magazine.setPublisher("new publisher");
        assertEquals("new publisher", magazine.getPublisher());
    }

    @Test
    void setPublicationsYearly() {
        magazine.setPublicationsYearly(365);
        assertEquals(365, magazine.getPublicationsYearly());
    }

    @Test
    void setType() {
        magazine.setType("new type");
        assertEquals("new type", magazine.getType());
    }

    @Test
    void setGenre() {
        magazine.setGenre("new genre");
        assertEquals("new genre", magazine.getGenre());
    }
}