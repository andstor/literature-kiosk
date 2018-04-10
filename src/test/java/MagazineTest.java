import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagazineTest {
    private Literature literature;

    @BeforeEach
    void setUp() {
        literature = new Magazine("title", "publisher", 100, "type", "genre");
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
    void getPublicationsYearly() {
        assertEquals(100, literature.getPublicationsYearly());
    }

    @Test
    void getType() {
        assertEquals("type", literature.getType());
    }

    @Test
    void getGenre() {
        assertEquals("genre", literature.getGenre());
    }

    @Test
    void setTitle() {
        literature.setTitle("new title");
        assertEquals("new title", literature.getTitle());
    }

    @Test
    void setPublisher() {
        literature.setPublisher("new publisher");
        assertEquals("new publisher", literature.getPublisher());
    }

    @Test
    void setPublicationsYearly() {
        literature.setPublicationsYearly(1);
        assertEquals(1, literature.getPublicationsYearly());

        literature.setPublicationsYearly(365);
        assertEquals(365, literature.getPublicationsYearly());
    }

    @Test
    void setType() {
        literature.setType("new type");
        assertEquals("new type", literature.getType());
    }

    @Test
    void setGenre() {
        literature.setGenre("new genre");
        assertEquals("new genre", literature.getGenre());
    }
}