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

/**
 * Represents a single book view.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public class BookView implements LiteratureView {

    private static final int MAX_PAGES = 10000;
    private static final int MAX_EDITION_NUMBER = 1000;

    /**
     * The mode of the view. INFO if viewing details of Book, EDIT if edit existing
     * Book.
     */
    private Mode mode;

    private LiteratureEventGenerator changeEventHandler;
    private LiteratureEventGenerator newEventHandler;
    private LiteratureEventGenerator deleteEventHandler;


    /**
     * Constructor for objects of class BookView.
     */
    public BookView() {
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

    public Pane buildUIView(Literature literature) {
        VBox vBox = new VBox();

        vBox.setAlignment(Pos.TOP_RIGHT);
        vBox.setSpacing(10.0d);

        if (literature instanceof Book) {
            Book book = (Book) literature;


            VBox titleBox = new VBox();
            Label titleL = new Label("Title");
            titleBox.getChildren().add(titleL);
            TextField titleTF = new TextField(book.getTitle());
            titleBox.getChildren().add(titleTF);

            VBox publisherBox = new VBox();
            Label publisherL = new Label("Publisher");
            publisherBox.getChildren().add(publisherL);
            TextField publisherTF = new TextField(book.getPublisher());
            publisherBox.getChildren().add(publisherTF);

            VBox genreBox = new VBox();
            Label genreL = new Label("Genre");
            genreBox.getChildren().add(genreL);
            TextField genreTF = new TextField(book.getGenre());
            genreBox.getChildren().add(genreTF);

            VBox authorBox = new VBox();
            Label authorL = new Label("Author");
            authorBox.getChildren().add(authorL);
            TextField authorTF = new TextField(book.getAuthor());
            authorBox.getChildren().add(authorTF);

            VBox editionBox = new VBox();
            Label editionL = new Label("Edition");
            editionBox.getChildren().add(editionL);
            Spinner<Integer> editionS = createNumericSpinner(book.getEdition());
            editionBox.getChildren().add(editionS);

            VBox pagesBox = new VBox();
            Label pagesL = new Label("Pages");
            pagesBox.getChildren().add(pagesL);
            Spinner<Integer> pagesS = createNumericSpinner(book.getPages());
            pagesBox.getChildren().add(pagesS);

            if (mode == Mode.INFO) {
                titleTF.setEditable(false);
                publisherTF.setEditable(false);
                genreTF.setEditable(false);
                authorTF.setEditable(false);
                editionS.setEditable(false);
                pagesS.setEditable(false);
            } else if (mode == Mode.EDIT) {
                titleTF.setEditable(true);
                publisherTF.setEditable(true);
                genreTF.setEditable(true);
                authorTF.setEditable(false);
                editionS.setEditable(false);
                pagesS.setEditable(false);
            }


            FlowPane buttons = new FlowPane();
            buttons.setPadding(new Insets(10.0d, 0, 0, 0));
            buttons.setVgap(10.0d);
            buttons.setHgap(10.0d);


            if (mode == Mode.INFO) {
                Button convertToBookSeries = new Button("Convert to book series");
                convertToBookSeries.setOnAction(e -> System.out.println("Convert to book series"));
                convertToBookSeries.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        BookSeries newBookSeries = new BookSeries(
                                book.getTitle(),
                                book.getPublisher(),
                                book.getGenre(),
                                book.getAuthor()
                        );


                        newBookSeries.addBookToSeries(book);
                        newEventHandler.fireEvent(new LiteratureNewEvent(this, newBookSeries));
                    }
                });

                buttons.getChildren().add(convertToBookSeries);


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

                        book.setTitle(titleTF.getText());
                        book.setPublisher(publisherTF.getText());
                        book.setGenre(genreTF.getText());
                        book.setPages(pagesS.getValue());
                        book.setAuthor(authorTF.getText());
                        book.setEdition(editionS.getValue());
                        book.setPages(pagesS.getValue());

                        changeEventHandler.fireEvent(new LiteratureChangeEvent(this, literature, isEditable()) {
                        });
                    }
                });

                buttons.getChildren().add(update);
            }


            vBox.getChildren().addAll(titleBox, publisherBox, genreBox, authorBox, editionBox, pagesBox, buttons);
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