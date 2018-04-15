import java.util.Scanner;

public class MagazineView implements LiteratureView {

    private static final int MAX_PUBLICATIONS_YEARLY = 365;

    private UserInputReader UIReader;

    /**
     * Constructor for objects of class MagazineView.
     */
    public MagazineView() {
        UIReader = new UserInputReader();
    }

    /**
     * Prints a formatted string containing all the details about the magazine, on the form
     * "Title: title, Publisher: publisher, Number of yearly publications: publicationsYearly,
     * Type of reading material: type, Genre: genre".
     */
    public void printAllDetails(Literature literature) {
        if (literature instanceof Magazine) {
            Magazine magazine = (Magazine) literature;
            System.out.println("Title: " + magazine.getTitle() + ", Publisher: " + magazine.getPublisher()
                    + ", Publications yearly: " + magazine.getPublicationsYearly()
                    + ", Type: " + magazine.getType() + ", Genre: " + magazine.getGenre());
        }
    }

    /**
     * Gets the title of the magazine provided by the user.
     *
     * @return the title of this magazine
     */
    public String getTitleFromUser() {
        System.out.println("Please enter the title of the magazine:");
        String title = UIReader.getStringFromUserInput("title");
        return title;
    }

    /**
     * Gets the publisher of the magazine provided by the user.
     *
     * @return the publisher of this magazine
     */
    public String getPublisherFromUser() {
        System.out.println("Please enter the publisher of the magazine:");
        String publisher = UIReader.getStringFromUserInput("publisher");
        return publisher;
    }

    /**
     * Gets the number of the magazine's yearly publications provided by the user.
     *
     * @return the number of the magazine's yearly publications
     */
    public int getPublicationsYearlyFromUser() {
        Scanner reader = new Scanner(System.in);
        int publicationsYearly = 0;


        System.out.println("Please enter the number of yearly publications of the magazine:");


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
     * Gets the genre of the magazine provided by the user.
     *
     * @return the genre of this magazine
     */
    public String getGenreFromUser() {
        System.out.println("Please enter the genre of the magazine:");
        String genre = UIReader.getStringFromUserInput("genre");
        return genre;
    }
}