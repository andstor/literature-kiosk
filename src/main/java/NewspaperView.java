import java.util.Scanner;

/**
 * Represents a newspaper view.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class NewspaperView implements LiteratureView {

    private static final int MAX_PUBLICATIONS_YEARLY = 365;

    private UserInputReader UIReader;

    /**
     * Constructor for objects of class NewspaperView.
     */
    public NewspaperView() {
        UIReader = new UserInputReader();
    }

    /**
     * Prints a formatted string containing all the details about the newspaper, on the form
     * "Title: title, Publisher: publisher, Number of yearly publications: publicationsYearly,
     * Type of reading material: type, Genre: genre".
     */
    public void printAllDetails(Literature literature) {
        if (literature instanceof Newspaper) {
            Newspaper newspaper = (Newspaper) literature;
            System.out.println("Title: " + newspaper.getTitle() + ", Publisher: " + newspaper.getPublisher()
                    + ", Publications yearly: " + newspaper.getPublicationsYearly()
                    + ", Type: " + newspaper.getType() + ", Genre: " + newspaper.getGenre());
        }
    }

    /**
     * Gets the title of the newspaper provided by the user.
     *
     * @return the title of this newspaper
     */
    public String getTitleFromUser() {
        System.out.println("Please enter the title of the newspaper:");
        String title = UIReader.getStringFromUserInput("title");
        return title;
    }

    /**
     * Gets the publisher of the newspaper provided by the user.
     *
     * @return the publisher of this newspaper
     */
    public String getPublisherFromUser() {
        System.out.println("Please enter the publisher of the newspaper:");
        String publisher = UIReader.getStringFromUserInput("publisher");
        return publisher;
    }

    /**
     * Gets the number of the newspaper's yearly publications provided by the user.
     *
     * @return the number of the newspaper's yearly publications
     */
    public int getPublicationsYearlyFromUser() {
        Scanner reader = new Scanner(System.in);
        int publicationsYearly = 0;


        System.out.println("Please enter the number of yearly publications of the newspaper:");


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
     * Gets the genre of the newspaper provided by the user.
     *
     * @return the genre of this newspaper
     */
    public String getGenreFromUser() {
        System.out.println("Please enter the genre of the newspaper:");
        String genre = UIReader.getStringFromUserInput("genre");
        return genre;
    }
}