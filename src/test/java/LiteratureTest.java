import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureTest {
    private Literature literature;

    @BeforeEach
    void setUp() {
        literature = new Magazine("title", "publisher", 100, "genre");
    }

    @AfterEach
    void tearDown() {
    }

    /*@Test
    void getType() {
        assertEquals("type", literature.getType());
    }*/


    /*@Test
    void setType() {
        literature.setType("new type");
        assertEquals("new type", literature.getType());
    }*/

    @Test
    void getTitle() {
        assertEquals("title", literature.getTitle());
    }


    @Test
    void setTitle() {
        literature.setTitle("new title");
        assertEquals("new title", literature.getTitle());
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