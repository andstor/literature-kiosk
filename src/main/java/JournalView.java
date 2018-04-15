import java.util.Scanner;

/**
 * Represents a single journal view.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class JournalView implements LiteratureView {

    private static final int MAX_PUBLICATIONS_YEARLY = 365;

    private UserInputReader UIReader;


    /**
     * Constructor for objects of class JournalView.
     */
    public JournalView() {
        UIReader = new UserInputReader();
    }

    /**
     * Prints a formatted string containing all the details about the journal, on the form
     * "Title: title, Publisher: publisher, Number of yearly publications: publicationsYearly,
     * Type of reading material: type, Genre: genre".
     *
     * @param literature the literature to print all the details from
     */
    public void printAllDetails(Literature literature) {
        if (literature instanceof Journal) {
            Journal journal = (Journal) literature;
            System.out.println("Title: " + journal.getTitle() + ", Publisher: " + journal.getPublisher()
                    + ", Publications yearly: " + journal.getPublicationsYearly()
                    + ", Type: " + journal.getType() + ", Genre: " + journal.getGenre());
        }
    }

/**
 * Gets the title of the book provided by the user.
 *
 * @return the title of this book
 */
    public String getTitleFromUser() {
        System.out.println("Please enter the title of the journal:");
        String title = UIReader.getStringFromUserInput("title");
        return title;
    }

/**
 * Gets the publisher of the book provided by the user.
 *
 * @return the publisher of this book
 */
    public String getPublisherFromUser() {
        System.out.println("Please enter the publisher of the journal:");
        String publisher = UIReader.getStringFromUserInput("publisher");
        return publisher;
    }


    /**
     * Gets the number of the book's yearly publications provided by the user.
     *
     * @return the number of the book's yearly publications
     */
    public int getPublicationsYearlyFromUser() {
        Scanner reader = new Scanner(System.in);
        int publicationsYearly = 0;


        System.out.println("Please enter the number of yearly publications of the journal:");


        boolean quit = false;
        while (!quit) {
            if (reader.hasNextInt()) {
                publicationsYearly = reader.nextInt();
                reader.nextLine();

                if ((1 > publicationsYearly) || (MAX_PUBLICATIONS_YEARLY < publicationsYearly)) {
                    System.out.println("Please enter a valid number between 1 and " + MAX_PUBLICATIONS_YEARLY + ":");
                } else {
                    quit = true;
                }

            } else {
                System.out.println("Please enter a valid number between 1 and " + MAX_PUBLICATIONS_YEARLY + ":");
                reader.next();
            }
        }
        return publicationsYearly;
    }

    /**
     * Gets the genre of the book provided by the user.
     *
     * @return the genre of this book
     */
    public String getGenreFromUser() {
        System.out.println("Please enter the genre of the journal:");
        String genre = UIReader.getStringFromUserInput("genre");
        return genre;
    }
}