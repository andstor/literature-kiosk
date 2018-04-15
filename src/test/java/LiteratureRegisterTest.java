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
        literature = new Magazine("title", "publisher", 100, "genre");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMagazine() {

        assertTrue(register.addLiterature(literature));
    }

    @Test
    void addMagazineNegative() {
        Literature literature = null;
        assertFalse(register.addLiterature(literature));
    }

    @Test
    void removeMagazine() {
        register.addLiterature(literature);
        assertTrue(register.removeLiterature(literature));
    }

    @Test
    void removeMagazineNegative() {
        Literature literatureNull = null;
        assertFalse(register.removeLiterature(literatureNull));

        register.addLiterature(literature);
        assertFalse(register.removeLiterature(literatureNull));
    }

    @Test
    void getAllMagazines() {
        register.addLiterature(literature);
        assertTrue(register.getAllMagazines().hasNext());
    }

    @Test
    void getAllMagazinesNegative() {
        assertFalse(register.getAllMagazines().hasNext());
    }

    @Test
    void findMagazineByTitleAndPublisher() {

        register.addLiterature(literature);

        assertEquals(literature, register.getLiteratureByTitleAndPublisher("title", "publisher"));
    }

    @Test
    void findMagazineByTitleAndPublisherNegative() {

        register.addLiterature(literature);

        assertEquals(null, register.getLiteratureByTitleAndPublisher("not a title", "not a publisher"));
    }

    @Test
    void getMagazineByPublisherAsCollection() {

        register.addLiterature(literature);

        assertTrue(register.getLiteratureByPublisherAsCollection("publisher").hasNext());
    }

    @Test
    void getMagazineByPublisherAsCollectionNegative() {

        register.addLiterature(literature);

        assertFalse(register.getLiteratureByPublisherAsCollection("not a publisher").hasNext());
    }
}