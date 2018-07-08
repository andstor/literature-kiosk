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

import java.util.Scanner;

/**
 * Represents a newspaper view.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class NewspaperView implements LiteratureView {

    private static final int MAX_PUBLICATIONS_YEARLY = 365;

    /**
     * The mode of the view. INFO if viewing details of Newspaper, EDIT if edit existing
     * Newspaper.
     */
    private Mode mode;

    private LiteratureEventGenerator changeEventHandler;
    private LiteratureEventGenerator newEventHandler;
    private LiteratureEventGenerator deleteEventHandler;


    /**
     * Constructor for objects of class NewspaperView.
     */
    public NewspaperView() {
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

        if (literature instanceof Newspaper) {
            Newspaper newspaper = (Newspaper) literature;


            VBox titleBox = new VBox();
            Label titleL = new Label("Title");
            titleBox.getChildren().add(titleL);
            TextField titleTF = new TextField(newspaper.getTitle());
            titleBox.getChildren().add(titleTF);

            VBox publisherBox = new VBox();
            Label publisherL = new Label("Publisher");
            publisherBox.getChildren().add(publisherL);
            TextField publisherTF = new TextField(newspaper.getPublisher());
            publisherBox.getChildren().add(publisherTF);

            VBox genreBox = new VBox();
            Label genreL = new Label("Genre");
            genreBox.getChildren().add(genreL);
            TextField genreTF = new TextField(newspaper.getGenre());
            genreBox.getChildren().add(genreTF);

            VBox yearlyPublicationsBox = new VBox();
            Label yearlyPublicationsL = new Label("Yearly publications");
            yearlyPublicationsBox.getChildren().add(yearlyPublicationsL);
            Spinner<Integer> yearlyPublicationsS = createNumericSpinner(newspaper.getPublicationsYearly());
            yearlyPublicationsBox.getChildren().add(yearlyPublicationsS);


            if (mode == Mode.INFO) {
                titleTF.setEditable(false);
                publisherTF.setEditable(false);
                genreTF.setEditable(false);
                yearlyPublicationsS.setEditable(false);
            } else if (mode == Mode.EDIT) {
                titleTF.setEditable(true);
                publisherTF.setEditable(true);
                genreTF.setEditable(true);
                yearlyPublicationsS.setEditable(true);
            }


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

                        newspaper.setTitle(titleTF.getText());
                        newspaper.setPublisher(publisherTF.getText());
                        newspaper.setGenre(genreTF.getText());
                        newspaper.setPublicationsYearly(yearlyPublicationsS.getValue());

                        changeEventHandler.fireEvent(new LiteratureChangeEvent(this, literature, isEditable()) {
                        });
                    }
                });

                buttons.getChildren().add(update);
            }

            vBox.getChildren().addAll(titleBox, publisherBox, genreBox, yearlyPublicationsBox, buttons);
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