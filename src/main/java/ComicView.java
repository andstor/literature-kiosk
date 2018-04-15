import java.util.Scanner;

/**
 * Represents a single comic view.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class ComicView implements LiteratureView {

    private static final int MAX_PUBLICATIONS_YEARLY = 365;

    private UserInputReader UIReader;

    /**
     * Constructor for objects of class ComicView.
     */
    public ComicView() {
        UIReader = new UserInputReader();
    }

    /**
     * Prints a formatted string containing all the details about the comic, on the form
     * "Title: title, Publisher: publisher, Number of yearly publications: publicationsYearly,
     * Type of reading material: type, Genre: genre".
     */
    public void printAllDetails(Literature literature) {
        if (literature instanceof Comic) {
            Comic comic = (Comic) literature;
            System.out.println("Title: " + comic.getTitle() + ", Publisher: " + comic.getPublisher()
                    + ", Publications yearly: " + comic.getPublicationsYearly()
                    + ", Type: " + comic.getType() + ", Genre: " + comic.getGenre());
        }
    }

    /**
     * Gets the title of the comic provided by the user.
     *
     * @return the title of this comic
     */
    public String getTitleFromUser() {
        System.out.println("Please enter the title of the comic:");
        String title = UIReader.getStringFromUserInput("title");
        return title;
    }

    /**
     * Gets the publisher of the comic provided by the user.
     *
     * @return the publisher of this comic
     */
    public String getPublisherFromUser() {
        System.out.println("Please enter the publisher of the comic:");
        String publisher = UIReader.getStringFromUserInput("publisher");
        return publisher;
    }

    /**
     * Gets the number of the comic's yearly publications provided by the user.
     *
     * @return the number of the comic's yearly publications
     */
    public int getPublicationsYearlyFromUser() {
        Scanner reader = new Scanner(System.in);
        int publicationsYearly = 0;


        System.out.println("Please enter the number of yearly publications of the comic:");


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
     * Gets the genre of the comic provided by the user.
     *
     * @return the genre of this comic
     */
    public String getGenreFromUser() {
        System.out.println("Please enter the genre of the comic:");
        String genre = UIReader.getStringFromUserInput("genre");
        return genre;
    }
}