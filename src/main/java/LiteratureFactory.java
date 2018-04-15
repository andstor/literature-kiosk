public class LiteratureFactory {


    /**
     * Creates the correct literature view based on the type passed as parameter.
     *
     * @param type the type of literature to be created.
     * @return the type of literature view. If no match is found, <code>null</code> is returned.
     */
    public static Literature createLiterature(String type) {

        Literature newLiterature = null;

        if (type.equals("magazine")) {

            MagazineView magazineView = (MagazineView) LiteratureViewFactory.create(type);
            String title = magazineView.getTitleFromUser();
            String publisher = magazineView.getPublisherFromUser();
            int publicationsYearly = magazineView.getPublicationsYearlyFromUser();
            String genre = magazineView.getGenreFromUser();

            newLiterature = new Magazine(title, publisher, publicationsYearly, genre);

        } else if (type.equals("newspaper")) {
            NewspaperView newspaperView = (NewspaperView) LiteratureViewFactory.create(type);
            String title = newspaperView.getTitleFromUser();
            String publisher = newspaperView.getPublisherFromUser();
            int publicationsYearly = newspaperView.getPublicationsYearlyFromUser();
            String genre = newspaperView.getGenreFromUser();

            newLiterature = new Newspaper(title, publisher, publicationsYearly, genre);


        } else if (type.equals("journal")) {
            JournalView journalView = (JournalView) LiteratureViewFactory.create(type);
            String title = journalView.getTitleFromUser();
            String publisher = journalView.getPublisherFromUser();
            int publicationsYearly = journalView.getPublicationsYearlyFromUser();
            String genre = journalView.getGenreFromUser();

            newLiterature = new Journal(title, publisher, publicationsYearly, genre);

        } else if (type.equals("comic")) {
            ComicView comicView = (ComicView) LiteratureViewFactory.create(type);
            String title = comicView.getTitleFromUser();
            String publisher = comicView.getPublisherFromUser();
            int publicationsYearly = comicView.getPublicationsYearlyFromUser();
            String genre = comicView.getGenreFromUser();

            newLiterature = new Comic(title, publisher, publicationsYearly, genre);

        } else if (type.equals("book")) {
            BookView bookView = (BookView) LiteratureViewFactory.create(type);
            String title = bookView.getTitleFromUser();
            String publisher = bookView.getPublisherFromUser();
            String genre = bookView.getGenreFromUser();
            String author = bookView.getAuthorFromUser();
            int edition = bookView.getEditionFromUser();
            String dateOfRelease = bookView.getDateOfRelease();
            int pages = bookView.getPagesFromUser();

            newLiterature = new Book(title, publisher, genre, author, edition, dateOfRelease, pages);

        } else if (type.equals("book series")) {
            BookSeriesView bookSeriesView = (BookSeriesView) LiteratureViewFactory.create(type);
            String title = bookSeriesView.getTitleFromUser();
            String publisher = bookSeriesView.getPublisherFromUser();
            String genre = bookSeriesView.getGenreFromUser();
            String author = bookSeriesView.getAuthorFromUser();

            newLiterature = new BookSeries(title, publisher, genre, author);
        }

        return newLiterature;
    }
}