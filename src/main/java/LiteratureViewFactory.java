public class LiteratureViewFactory {

    public LiteratureViewFactory() {
    }

    /**
     * Creates the correct literature view based on the type passed as parameter.
     *
     * @param type the type of literature to be created.
     * @return the type of literature view. If no match is found, <code>null</code> is returned.
     */
    public static LiteratureView create(String type) {

        LiteratureView literatureView = null;

        if (type.equals("magazine")) {
            literatureView = new MagazineView();
        } else if (type.equals("newspaper")) {
            literatureView = new NewspaperView();
        } else if (type.equals("journal")) {
            literatureView = new JournalView();
        }else if (type.equals("comic")) {
            literatureView = new ComicView();
        } else if (type.equals("book")) {
            literatureView = new BookView();
        } else if (type.equals("book series")) {
            literatureView = new BookSeriesView();
        } else {
            System.out.println("Not a valid view type!");
        }

        return literatureView;
    }
}