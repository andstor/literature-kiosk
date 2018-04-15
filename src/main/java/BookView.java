import java.util.Scanner;

public class BookView implements LiteratureView {

    private static final int MAX_PAGES = 10000;
    private static final int MAX_EDITION_NUMBER = 1000;

    private UserInputReader UIReader;


    public BookView() {
        UIReader = new UserInputReader();
    }

    /**
     * Prints a formatted string containing all the details about the book, on the form
     * "Title: title, Publisher: publisher, Number of yearly publications: publicationsYearly,
     * Type of reading material: type, Genre: genre".
     */
    public void printAllDetails(Literature literature) {
        if (literature instanceof Book) {
            Book book = (Book) literature;
            System.out.println("Title: " + book.getTitle()
                    + ", Publisher: " + book.getPublisher()
                    + ", Author: " + book.getAuthor()
                    + ", Book edition: " + book.getEdition()
                    + ", Type: " + book.getType()
                    + ", Edition: " + book.getEdition()
                    + ", Date of release: " + book.getDateOfRelease()
                    + ", Pages : " + book.getPages()
                    + ", Genre: " + book.getGenre());
        }
    }

    /**
     * Gets the title of the book provided by the user.
     *
     * @return the title of this book.
     */
    public String getTitleFromUser() {
        System.out.println("Please enter the title of the book:");
        String title = UIReader.getStringFromUserInput("title");
        return title;
    }

    /**
     * Gets the publisher of the book provided by the user.
     *
     * @return the publisher of this book.
     */
    public String getPublisherFromUser() {
        System.out.println("Please enter the publisher of the book:");
        String publisher = UIReader.getStringFromUserInput("publisher");
        return publisher;
    }

    /**
     * Gets the genre of the book provided by the user.
     *
     * @return the genre of this book.
     */
    public String getGenreFromUser() {
        System.out.println("Please enter the genre of the book:");
        String genre = UIReader.getStringFromUserInput("genre");
        return genre;
    }

    /**
     * Gets the author of the book provided by the user.
     *
     * @return the author of this book.
     */
    public String getAuthorFromUser() {
        System.out.println("Please enter the author of the book:");
        String author = UIReader.getStringFromUserInput("author");
        return author;
    }

    /**
     * Gets the edition of the book provided by the user.
     *
     * @return the edition of this book.
     */
    public int getEditionFromUser() {
        Scanner reader = new Scanner(System.in);
        int edition = 1;


        System.out.println("Please enter the book's edition number :");

        boolean quit = false;
        while (!quit) {
            if (reader.hasNextInt()) {
                edition = reader.nextInt();
                reader.nextLine();

                if ((1 > edition) || (MAX_EDITION_NUMBER < edition)) {
                    System.out.println("Please enter a valid number between 1 and " + MAX_EDITION_NUMBER + ":");
                } else {
                    quit = true;
                }

            } else {
                System.out.println("Please enter a valid number between 1 and " + MAX_EDITION_NUMBER + ":");
                reader.next();
            }
        }
        return edition;
    }


    /**
     * Gets the date of the book provided by the user formatted as dd/mm/yyyy.
     *
     * @return the date of this book formatted as dd/mm/yyyy.
     */
    public String getDateOfRelease() {
        System.out.println("Please enter the book's release date (dd/mm/yyyy):");
        String date = UIReader.getStringFromUserInput("release date");
        return date;
    }

    /**
     * Gets the number of the book's pages provided by the user.
     *
     * @return the number of the book's pages.
     */
    public int getPagesFromUser() {
        Scanner reader = new Scanner(System.in);
        int pages = 1;

        System.out.println("Please enter the book's number of pages :");

        boolean quit = false;
        while (!quit) {
            if (reader.hasNextInt()) {
                pages = reader.nextInt();
                reader.nextLine();

                if ((1 > pages) || (MAX_PAGES < pages)) {
                    System.out.println("Please enter a valid number between 1 and " + MAX_PAGES + ":");
                } else {
                    quit = true;
                }

            } else {
                System.out.println("Please enter a valid number between 1 and " + MAX_PAGES + ":");
                reader.next();
            }
        }
        return pages;
    }

}