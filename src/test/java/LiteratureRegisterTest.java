import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiteratureRegisterTest {
    private LiteratureRegister register;
    private Magazine magazine;

    @BeforeEach
    void setUp() {
        register = new LiteratureRegister();
        magazine = new Magazine("title", "publisher", 100, "type", "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMagazine() {

        assertTrue(register.addMagazine(magazine));
    }

    @Test
    void addMagazinenegative() {
        Magazine magazine = null;
        assertFalse(register.addMagazine(magazine));
    }

    @Test
    void removeMagazine() {
        register.addMagazine(magazine);
        assertTrue(register.removeMagazine(magazine));
    }

    @Test
    void removeMagazineNegative() {
        Magazine magazineNull = null;
        assertFalse(register.removeMagazine(magazineNull));

        register.addMagazine(magazine);
        assertFalse(register.removeMagazine(magazineNull));
    }

    @Test
    void getAllMagazines() {
        register.addMagazine(magazine);
        assertTrue(register.getAllMagazines().hasNext());
    }

    @Test
    void getAllMagazinesNegative() {
        assertFalse(register.getAllMagazines().hasNext());
    }

    @Test
    void findMagazineByTitleAndPublisher() {

        register.addMagazine(magazine);

        assertEquals(magazine, register.findMagazineByTitleAndPublisher("title", "publisher"));
    }

    @Test
    void findMagazineByTitleAndPublisherNegative() {

        register.addMagazine(magazine);

        assertEquals(null, register.findMagazineByTitleAndPublisher("not a title", "not a publisher"));
    }

    @Test
    void getMagazineByPublisherAsCollection() {

        register.addMagazine(magazine);

        assertTrue(register.getMagazineByPublisherAsCollection("publisher").hasNext());
    }

    @Test
    void getMagazineByPublisherAsCollectionNegative() {

        register.addMagazine(magazine);

        assertFalse(register.getMagazineByPublisherAsCollection("not a publisher").hasNext());
    }
}