import java.util.Iterator;

public class BookSeriesView implements LiteratureView {

    private UserInputReader UIReader;


    public BookSeriesView() {
        UIReader = new UserInputReader();
    }

    /**
     * Prints a formatted string containing all the details about the bookSeries, on the form
     * "Title: title, Publisher: publisher, Author: author, Genre: genre".
     * and the books in the series on the form
     * "[Title: titel, Publisher: publisher]"
     */
    public void printAllDetails(Literature literature) {
        if (literature instanceof BookSeries) {
            BookSeries bookSeries = (BookSeries) literature;

            System.out.println("Title: " + bookSeries.getTitle()
                    + ", Publisher: " + bookSeries.getPublisher()
                    + ", Author: " + bookSeries.getAuthor()
                    + ", Type: " + bookSeries.getType()
                    + ", Genre: " + bookSeries.getGenre());


            System.out.print(" - This series consists of the book(s):");

            Iterator<Book> it = bookSeries.getAllBooksInSeriesAsCollection();
            while (it.hasNext()) {
                Book book = it.next();
                System.out.println(" [Title: " + book.getTitle()
                        + ", Publisher: " + book.getDateOfRelease() + "] ");
            }
        }
    }

    /**
     * Gets the title of the book series provided by the user.
     *
     * @return the title of this book.
     */
    public String getTitleFromUser() {
        System.out.println("Please enter the title of the book series:");
        String title = UIReader.getStringFromUserInput("title");
        return title;
    }

    /**
     * Gets the publisher of the book seriesprovided by the user.
     *
     * @return the publisher of this book.
     */
    public String getPublisherFromUser() {
        System.out.println("Please enter the publisher of the book series:");
        String publisher = UIReader.getStringFromUserInput("publisher");
        return publisher;
    }

    /**
     * Gets the genre of the book seriesprovided by the user.
     *
     * @return the genre of this book.
     */
    public String getGenreFromUser() {
        System.out.println("Please enter the genre of the book series:");
        String genre = UIReader.getStringFromUserInput("genre");
        return genre;
    }

    /**
     * Gets the author of the book seriesprovided by the user.
     *
     * @return the author of this book.
     */
    public String getAuthorFromUser() {
        System.out.println("Please enter the author of the book series:");
        String author = UIReader.getStringFromUserInput("author");
        return author;
    }
}