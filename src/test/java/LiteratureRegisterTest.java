import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureRegisterTest {
    private LiteratureRegister register;
    private Literature literature;

    @BeforeEach
    void setUp() {
        register = new LiteratureRegister();
        literature = new Magazine("title", "publisher", 100, "type", "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMagazine() {

        assertTrue(register.addMagazine(literature));
    }

    @Test
    void addMagazinenegative() {
        Literature literature = null;
        assertFalse(register.addMagazine(literature));
    }

    @Test
    void removeMagazine() {
        register.addMagazine(literature);
        assertTrue(register.removeMagazine(literature));
    }

    @Test
    void removeMagazineNegative() {
        Literature literatureNull = null;
        assertFalse(register.removeMagazine(literatureNull));

        register.addMagazine(literature);
        assertFalse(register.removeMagazine(literatureNull));
    }

    @Test
    void getAllMagazines() {
        register.addMagazine(literature);
        assertTrue(register.getAllMagazines().hasNext());
    }

    @Test
    void getAllMagazinesNegative() {
        assertFalse(register.getAllMagazines().hasNext());
    }

    @Test
    void findMagazineByTitleAndPublisher() {

        register.addMagazine(literature);

        assertEquals(literature, register.findMagazineByTitleAndPublisher("title", "publisher"));
    }

    @Test
    void findMagazineByTitleAndPublisherNegative() {

        register.addMagazine(literature);

        assertEquals(null, register.findMagazineByTitleAndPublisher("not a title", "not a publisher"));
    }

    @Test
    void getMagazineByPublisherAsCollection() {

        register.addMagazine(literature);

        assertTrue(register.getMagazineByPublisherAsCollection("publisher").hasNext());
    }

    @Test
    void getMagazineByPublisherAsCollectionNegative() {

        register.addMagazine(literature);

        assertFalse(register.getMagazineByPublisherAsCollection("not a publisher").hasNext());
    }
}