import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class LiteratureRegisterApp extends Application {

    private Stage primaryStage;
    private BorderPane root;
    private TableView literatureTable;

    private LiteratureRegister register;
    private UserInputReader UIReader;


    // The menu that will be displayed.
    private String[] menuItems = {
            "1. List all literature",
            "2. Add new literature",
            "3. Find a literature by name and publisher",
            "4. Find all literature by publisher",
            "5. Add a book to a book series",
            "6. Convert a book to a book series",

    };

    // List of literature types.
    private String[] types = {
            "magazine",
            "newspaper",
            "journal",
            "book",
            "book series",
            "comic",
    };


    /**
     * Creates an instance of the LiteratureRegisterApp user interface.
     */
    public LiteratureRegisterApp() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application so the user can input commands through the UI.
     * The application has 5 options.
     * The number values are of data type int, and ranging from 1 to 5.
     * If a different number is inputted, a error message will be displayed.
     */
    public void start() {
        this.setup();

        boolean quit = false;

        while (!quit) {
            try {
                int menuSelection = this.showMenu();
                switch (menuSelection) {
                    case 1:
                        listAllLiterature();
                        break;

                    case 2:
                        this.addNewLiterature();
                        break;

                    case 3:
                        this.findLiteratureByTitleAndPublisher();
                        break;

                    case 4:
                        this.findLiteratureByPublisher();
                        break;

                    case 5:
                        this.addBookToBookSeries();
                        break;

                    case 6:
                        this.convertBookToSeries();
                        break;

                    case 7:
                        System.out.println("\nThank you for using Application v3.0.0. Bye!\n");
                        quit = true;
                        break;

                    default:
                }
            } catch (InputMismatchException ime) {
                System.out.println("\nERROR: Please provide a number between 1 and " + (this.menuItems.length + 1) + ".\n");
            }
        }
    }


    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items.
     * If the user inputs anything else, an InputMismatchException is thrown.
     * The method returns the valid input from the user.
     *
     * @return the menu number (between 1 and max menu item number) provided by the user
     * @throws InputMismatchException if user enters an invalid number/menu choice
     */
    private int showMenu() throws InputMismatchException {
        System.out.println("\n**** Application v3.0.0 ****\n");
        // Display the menu
        for (String menuItem : menuItems) {
            System.out.println(menuItem);
        }
        int maxMenuItemNumber = menuItems.length + 1;
        // Add the "Exit"-choice to the menu
        System.out.println(maxMenuItemNumber + ". Exit\n");
        System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
        // Read input from user
        Scanner reader = new Scanner(System.in);
        int menuSelection = reader.nextInt();
        if ((menuSelection < 1) || (menuSelection > maxMenuItemNumber)) {
            throw new InputMismatchException();
        }
        return menuSelection;
    }

    // ------ The methods below this line are "helper"-methods, used from the menu ----
    // ------ All these methods are made private, since they are only used by the menu ---


    /**
     * Initializes the application.
     */
    private void setup() {
        register = new LiteratureRegister();
        UIReader = new UserInputReader();
    }


    @Override
    public void start(Stage stage) {
        setup();

        this.primaryStage = stage;
        stage.setTitle("Literature register");

        this.root = new BorderPane();

        // Build Menu Bar
        this.root.setTop(buildMenuBar());

        // Build literature table
        ObservableList<Literature> data = FXCollections.observableList(register.getAllLiterature());
        this.root.setCenter(createTable(data));

        // Build tree view


        // Show UI
        Scene scene = new Scene(this.root, 300, 300);
        stage.setScene(scene);
        scene.getStylesheets().add(LiteratureRegisterApp.class.getResource("main.css").toExternalForm());


        stage.show();
    }


    /**
     * Returns a menu bar.
     *
     * @return a menu bar
     */
    private MenuBar buildMenuBar() {

        // Create the File menu
        final Menu menu1 = new Menu("File");

        MenuItem menuItem11 = new MenuItem("Open");
        menuItem11.setOnAction(e -> System.out.println("Open menu item pressed."));

        Menu subMenu21 = new Menu("Export as");
        MenuItem menuItem211 = new MenuItem("Text file");
        menuItem211.setOnAction(e -> System.out.println("Export as text file menu item pressed."));
        subMenu21.getItems().addAll(menuItem211);

        menu1.getItems().addAll(menuItem11, subMenu21);


        // Create Edit menu
        final Menu menu2 = new Menu("Edit");


        //Create Help menu
        final Menu menu3 = new Menu("Help");

        MenuItem menuItem21 = new MenuItem("About");
        menuItem21.setOnAction(e -> System.out.println("About menu item pressed."));
        menu3.getItems().addAll(menuItem21);


        // Create menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        menuBar.getMenus().addAll(menu1, menu2, menu3);

        return menuBar;
    }


    private TableView<Literature> createTable(ObservableList<Literature> data) {
        TableView<Literature> table = new TableView<>();
        table.setItems(data);
        table.setEditable(false);

        TableColumn<Literature, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Literature, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Literature, String> publisherCol = new TableColumn<>("Publisher");
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        TableColumn<Literature, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        table.getColumns().setAll(typeCol, titleCol, publisherCol, genreCol);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        return table;
    }


    private String getTypeFromUser() {

        String typeInput = null;


        System.out.println("Which type of literature do you want to add?");
        for (String type : types) {
            System.out.print(type + ", ");
        }
        System.out.println();

        boolean quit = false;
        while (!quit) {
            typeInput = UIReader.getStringFromUserInput("literature type");

            boolean match = false;

            for (String type : types) {
                if (typeInput.equals(type)) {
                    quit = true;
                }
            }

            if (!quit) {
                System.out.println("Please enter a valid literature type:");
            }
        }

        return typeInput;
    }

    /**
     * Lists all the products/literature in the register.
     * Tells you to add a literature if the list is empty.
     */
    private void listAllLiterature() {
        System.out.println("\nCurrent literature in register: ");

        for (Literature literature : register) {
            if (literature != null) {
                String type = literature.getType();
                LiteratureView view = LiteratureViewFactory.create(type);
                view.printAllDetails(literature);
            } else {
                System.out.println("The are no literature in the register.");
            }
        }
    }

    /**
     * Adds a new literature to the literature register based on the information provided by the user.
     */
    private void addNewLiterature() {
        String type = getTypeFromUser();

        Literature newLiterature = LiteratureFactory.createLiterature(type);
        register.addLiterature(newLiterature);

        System.out.println("Literature successfully added!");
    }


    /**
     * Finds and prints all information about a literature, given the title and the publisher
     * provided as a user input.
     */
    private void findLiteratureByTitleAndPublisher() {
        System.out.println("Enter the title of the literature:");
        String title = UIReader.getStringFromUserInput("title");

        System.out.println("Enter the publisher of the literature:");
        String publisher = UIReader.getStringFromUserInput("publisher");

        Literature foundLiterature = register.getLiteratureByTitleAndPublisher(title, publisher);

        if (null != foundLiterature) {
            String type = foundLiterature.getType();
            System.out.println("\nThe literature matching the title \"" + title + "\" and the publisher \"" + publisher + "\" is:");
            LiteratureView view = LiteratureViewFactory.create(type);
            view.printAllDetails(foundLiterature);
        } else {
            System.out.println("There are no literature in the kiosk matching the title \"" + title + "\" and the publisher \"" + publisher + "\".");
        }
    }


    /**
     * Finds and prints all literature matching the publisher provided by the input from the user.
     * If there are no literature in the literature registry matching the
     * publisher given, an error message is printed.
     */
    private void findLiteratureByPublisher() {
        System.out.println("Enter the publisher of the literature(s) here:");
        String publisher = UIReader.getStringFromUserInput("publisher");

        Iterator<Literature> literatureIt = register.getLiteratureByPublisherAsCollection(publisher);
        if (literatureIt.hasNext()) {
            System.out.println("\nThe literature published by the publisher \"" + publisher + "\" is: ");
            while (literatureIt.hasNext()) {
                Literature literature = literatureIt.next();
                String type = literature.getType();

                LiteratureView view = LiteratureViewFactory.create(type);
                view.printAllDetails(literature);
            }
        } else {
            System.out.println("There are no literature in the kiosk matching the publisher \"" + publisher + "\".");
        }
    }


    /**
     * Adds a existing book matching the title and publisher provided by the user,
     * to a existing book series also matching the provided title and publisher.
     */
    private void addBookToBookSeries() {
        System.out.println("Enter the title of the book series:");
        String bookSeriesTitle = UIReader.getStringFromUserInput("title");

        System.out.println("Enter the publisher of the book series here:");
        String bookSeriesPublisher = UIReader.getStringFromUserInput("publisher");

        Literature foundBookSeries = register.getLiteratureByTitleAndPublisher(bookSeriesTitle, bookSeriesPublisher);


        if (null != foundBookSeries) {

            if (foundBookSeries instanceof BookSeries) {
                BookSeries bookSeries = (BookSeries) foundBookSeries;


                System.out.println("Enter the title of the book to be added to the series:");
                String bookTitle = UIReader.getStringFromUserInput("title");

                System.out.println("Enter the publisher of the book to be added to the series:");
                String bookPublisher = UIReader.getStringFromUserInput("publisher");

                Literature foundBook = register.getLiteratureByTitleAndPublisher(bookTitle, bookPublisher);
                if (null != foundBook) {
                    if (foundBook instanceof Book) {
                        Book book = (Book) foundBook;
                        bookSeries.addBookToSeries(book);
                    } else {
                        System.out.println("That is not a book in the register!");
                    }
                } else {
                    System.out.println("There are no book  in the kiosk matching the title \"" + bookTitle + "\" and the publisher \"" + bookPublisher + "\".");
                }
            } else {
                System.out.println("That is not a book series in the register!");
            }
        } else {
            System.out.println("There are no book series in the kiosk matching the title \"" + bookSeriesTitle + "\" and the publisher \"" + bookSeriesPublisher + "\".");
        }
    }

    /**
     * Converts a book provided by the user matching the title and publisher, to a new book series.
     */
    private void convertBookToSeries() {
        System.out.println("Enter the title of the book to be added to the series:");
        String bookTitle = UIReader.getStringFromUserInput("title");

        System.out.println("Enter the publisher of the book to be added to the series:");
        String bookPublisher = UIReader.getStringFromUserInput("publisher");

        Literature foundBook = register.getLiteratureByTitleAndPublisher(bookTitle, bookPublisher);
        if (null != foundBook) {
            if (foundBook instanceof Book) {
                Book book = (Book) foundBook;
                Literature bookSeries = book.convertToSeries();

                // Print the book series
                LiteratureViewFactory.create("book series").printAllDetails(bookSeries);

                // Add the book series to the register.
                register.addLiterature(bookSeries);


            } else {
                System.out.println("That is not a book in the register!");
            }
        } else {
            System.out.println("There are no book in the kiosk matching the title \"" + bookTitle + "\" and the publisher \"" + bookPublisher + "\".");
        }
    }




    private class RowSelectChangeListener implements ChangeListener {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            System.out.println(newValue);
        }
    }


}