/**
 * Represents a single journal.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class Journal extends Periodical {

    private static final String TYPE = "journal";


    /**
     * Constructor for objects of class Journal.
     *
     * @param title              the title  of this journal
     * @param publisher          the publisher of this journal
     * @param publicationsYearly the release date of this journal journal
     * @param genre              the genre of this journal
     */
    public Journal(String title, String publisher, int publicationsYearly, String genre) {
        super(title, genre, publisher, publicationsYearly);

    }

    @Override
    public String getType() {
        return TYPE;
    }


}