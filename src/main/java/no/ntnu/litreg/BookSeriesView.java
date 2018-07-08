package no.ntnu.litreg;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import no.ntnu.litreg.event.*;

import java.util.Iterator;

/**
 * Represents a book series view.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public class BookSeriesView implements LiteratureView {


    /**
     * The mode of the view. INFO if viewing details of BookSeries, EDIT if edit existing
     * BookSeries.
     */
    private Mode mode;

    private LiteratureEventGenerator changeEventHandler;
    private LiteratureEventGenerator newEventHandler;
    private LiteratureEventGenerator deleteEventHandler;


    /**
     * Constructor for objects of class BookSeriesView.
     */
    public BookSeriesView() {
        this.mode = Mode.INFO;

        changeEventHandler = new LiteratureEventGenerator();
        newEventHandler = new LiteratureEventGenerator();
        deleteEventHandler = new LiteratureEventGenerator();
    }

    @Override
    public void addEventHandler(LiteratureEventListener listener) {
        if (listener instanceof LiteratureChangeEventListener) {
            changeEventHandler.addListener(listener);
        } else if (listener instanceof LiteratureNewEventListener) {
            newEventHandler.addListener(listener);
        } else if (listener instanceof LiteratureDeleteEventListener) {
            deleteEventHandler.addListener(listener);
        }
    }

    public Pane buildUIView(Literature literature) { // TODO add literature as parameter
        VBox vBox = new VBox();

        vBox.setAlignment(Pos.TOP_RIGHT);
        vBox.setSpacing(10.0d);

        if (literature instanceof BookSeries) {
            BookSeries bookSeries = (BookSeries) literature;


            VBox titleBox = new VBox();
            Label titleL = new Label("Title");
            titleBox.getChildren().add(titleL);
            TextField titleTF = new TextField(bookSeries.getTitle());
            titleBox.getChildren().add(titleTF);

            VBox publisherBox = new VBox();
            Label publisherL = new Label("Publisher");
            publisherBox.getChildren().add(publisherL);
            TextField publisherTF = new TextField(bookSeries.getPublisher());
            publisherBox.getChildren().add(publisherTF);

            VBox genreBox = new VBox();
            Label genreL = new Label("Genre");
            genreBox.getChildren().add(genreL);
            TextField genreTF = new TextField(bookSeries.getGenre());
            genreBox.getChildren().add(genreTF);

            VBox authorBox = new VBox();
            Label authorL = new Label("Author");
            authorBox.getChildren().add(authorL);
            TextField authorTF = new TextField(bookSeries.getAuthor());
            authorBox.getChildren().add(authorTF);

            FlowPane buttons = new FlowPane();
            buttons.setPadding(new Insets(10.0d, 0, 0, 0));
            buttons.setVgap(10.0d);
            buttons.setHgap(10.0d);



            if (mode == Mode.INFO) {
                Button btnDelete = new Button("Delete");
                btnDelete.setOnAction(e -> System.out.println("Delete!!!!"));
                btnDelete.getStyleClass().add("btn-delete");
                btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteEventHandler.fireEvent(new LiteratureDeleteEvent(this, literature) {
                        });
                    }
                });
                buttons.getChildren().add(btnDelete);

                Button edit = new Button("Edit");
                edit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        changeEventHandler.fireEvent(new LiteratureChangeEvent(this, literature, isEditable()) {
                        });
                    }
                });

                buttons.getChildren().add(edit);
            }


            if (mode == Mode.EDIT) {
                Button update = new Button("Update");
                update.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        bookSeries.setTitle(titleTF.getText());
                        bookSeries.setPublisher(publisherTF.getText());
                        bookSeries.setGenre(genreTF.getText());
                        bookSeries.setAuthor(authorTF.getText());

                        changeEventHandler.fireEvent(new LiteratureChangeEvent(this, literature, isEditable()) {
                        });
                    }
                });

                buttons.getChildren().add(update);
            }


            vBox.getChildren().addAll(titleBox, publisherBox, genreBox, authorBox);
            vBox.getChildren().add(new Separator());

            VBox books = new VBox();

            VBox header = new VBox();
            Label booksL = new Label("Books in register:");
            header.getChildren().add(booksL);
            vBox.getChildren().add(header);

            Iterator<Book> it = bookSeries.getAllBooksInSeriesAsCollection();
            while (it.hasNext()) {
                Book book = it.next();

                VBox bookTitleBox = new VBox();
                Label bookTitleL = new Label("Title");
                bookTitleBox.getChildren().add(bookTitleL);
                TextField bookTitleTF = new TextField(book.getTitle());
                bookTitleBox.getChildren().add(bookTitleTF);

                VBox bookAuthorBox = new VBox();
                Label bookAuthorL = new Label("Author");
                bookAuthorBox.getChildren().add(bookAuthorL);
                TextField bookAuthorTF = new TextField(book.getAuthor());
                bookAuthorBox.getChildren().add(bookAuthorTF);

                vBox.getChildren().addAll(bookTitleBox, bookAuthorBox);

            }

            vBox.getChildren().add(buttons);

        }
        return vBox;

    }

    private boolean isEditable() {
        if (mode == Mode.EDIT) {
            return true;
        }
        return false;
    }

    public void setEditable(Boolean editable) {
        if (editable) {
            this.mode = Mode.EDIT;
        } else {
            this.mode = Mode.INFO;
        }
    }

    private Spinner<Integer> createNumericSpinner(int initialValue) {
        final Spinner<Integer> spinner = new Spinner<>();


        // Value factory.
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, initialValue);

        spinner.setValueFactory(valueFactory);

        return spinner;
    }

    private enum Mode {
        EDIT, INFO
    }
}