package no.ntnu.litreg;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * Represents a magazine details dialog.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public class MagazineDetailsDialog extends Dialog<Magazine> {
    public MagazineDetailsDialog() {
        super();
        setTitle("Magazine details");

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        VBox titleBox = new VBox();
        Label titleL = new Label("Title");
        titleBox.getChildren().add(titleL);
        TextField titleTF = new TextField();
        titleBox.getChildren().add(titleTF);

        VBox publisherBox = new VBox();
        Label publisherL = new Label("Publisher");
        publisherBox.getChildren().add(publisherL);
        TextField publisherTF = new TextField();
        publisherBox.getChildren().add(publisherTF);

        VBox genreBox = new VBox();
        Label genreL = new Label("Genre");
        genreBox.getChildren().add(genreL);
        TextField genreTF = new TextField();
        genreBox.getChildren().add(genreTF);

        VBox yearlyPublicationsBox = new VBox();
        Label yearlyPublicationsL = new Label("Yearly publications");
        yearlyPublicationsBox.getChildren().add(yearlyPublicationsL);
        Spinner<Integer> yearlyPublicationsS = createNumericSpinner(0);
        yearlyPublicationsBox.getChildren().add(yearlyPublicationsS);

        VBox vBox = new VBox();
        vBox.setSpacing(10.0d);
        vBox.getChildren().addAll(titleBox, publisherBox, genreBox, yearlyPublicationsBox);

        getDialogPane().setContent(vBox);

        setResultConverter(new Callback<ButtonType, Magazine>() {
            @Override
            public Magazine call(ButtonType button) {
                if (button == ButtonType.OK) {
                    String title = titleTF.getText();
                    String publisher = publisherTF.getText();
                    String genre = genreTF.getText();
                    int publicationsYearly = yearlyPublicationsS.getValue();
                    return new Magazine(title, publisher, publicationsYearly, genre);

                }
                return null;
            }
        });
    }

    private Spinner<Integer> createNumericSpinner(int initialValue) {
        final Spinner<Integer> spinner = new Spinner<>();


        // Value factory.
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, initialValue);

        spinner.setValueFactory(valueFactory);

        return spinner;
    }

}