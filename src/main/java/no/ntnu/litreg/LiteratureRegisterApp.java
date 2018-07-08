package no.ntnu.litreg;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import no.ntnu.litreg.event.*;

import java.util.Optional;

/**
 * Makes up the user interface of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 4.0.0
 */
public class LiteratureRegisterApp extends Application {

    private static final String TREE_ROOT_NAME = "Literature";


    private SplitPane root;
    private FilteredList<Literature> tableData;

    private LiteratureRegister register;
    private LiteratureViewFactory viewFactory;

    private BorderPane detailsView;
    private TextArea terminal;
    private SplitPane centerSPane;
    private VBox treeContainer;
    /**
     * An ObservableList used to "wrap" the real register to enable the link
     * between the TableView and the LiteratureRegister.
     */
    private ObservableList<Literature> literatures;
    private TableView<Literature> table;

    /**
     * Creates an instance of the LiteratureRegisterApp user interface.
     */
    public LiteratureRegisterApp() {

    }

    /**
     * Main method for starting the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the application.
     */
    @Override
    public void init() {

        register = new LiteratureRegister();
        fillWithDummyData(); // Fill register with dummy data.

        this.literatures = getInitialTableData();
        this.viewFactory = LiteratureViewFactory.getInstance();
    }

    /**
     * Starts JavaFX and builds the main GUI. Gui is based upon javaFX.
     *
     * @param primaryStage the primary stage for the JavaFX app.
     */
    @Override
    public void start(final Stage primaryStage) {
        init();

        this.root = new SplitPane();
        root.setOrientation(Orientation.VERTICAL);
        root.setDividerPosition(0, 0.5);

        SplitPane mainSplit = new SplitPane();
        mainSplit.setOrientation(Orientation.VERTICAL);
        mainSplit.setDividerPosition(0, 0.90f);

        SplitPane centerContent = new SplitPane();
        this.centerSPane = centerContent;
        centerContent.setOrientation(Orientation.HORIZONTAL);
        centerContent.setDividerPosition(0, 0.2f);
        centerContent.setDividerPosition(1, 1);

        // Top menu and toolbar
        VBox vBox = new VBox();
        BorderPane bPane = new BorderPane();
        // Build Menu Bar
        MenuBar menuBar = buildMenuBar();
        // Build Toolbar
        ToolBar toolBar = buildToolbar();
        vBox.getChildren().addAll(menuBar, toolBar);
        bPane.setTop(vBox);

        // Build literature table
        TableView<Literature> table = buildTable(this.literatures);
        this.table = table;
        Platform.runLater(table::refresh);

        // Build the left sidebar
        VBox sidebarLeft = buildLeftSidebar();
        SplitPane.setResizableWithParent(sidebarLeft, Boolean.FALSE);


        // Build the right pane
        Pane paneRight = buildRightPane();
        SplitPane.setResizableWithParent(paneRight, Boolean.FALSE);
        hideDetailsPane();


        // Add leftSidebar, table and right pane to the center content
        centerContent.getItems().addAll(sidebarLeft, table, paneRight);
        // Add the centerContent split-pane to the center of the border-pane bPane
        bPane.setCenter(centerContent);


        // Build bottom literature view
        Pane paneBottom = buildBottomPane();
        SplitPane.setResizableWithParent(paneBottom, Boolean.FALSE);

        // Add the upper main border pane bPane, and the bottom pane to the mainSplit split-pane
        mainSplit.getItems().addAll(bPane, paneBottom);

        this.root.getItems().add(mainSplit);

        // Create the scene, adding the rootNode and setting the size
        Scene scene = new Scene(this.root, 800, 800);
        scene.getStylesheets().add(LiteratureRegisterApp.class.getResource("/main.css").toExternalForm());
        // Set the window title
        primaryStage.setTitle("Literature register");
        // Add icons
        primaryStage.getIcons().addAll(
                new Image(LiteratureRegisterApp.class.getResourceAsStream("/images/research16.png")),
                new Image(LiteratureRegisterApp.class.getResourceAsStream("/images/research24.png")),
                new Image(LiteratureRegisterApp.class.getResourceAsStream("/images/research32.png")),
                new Image(LiteratureRegisterApp.class.getResourceAsStream("/images/research512.png")));
        // Add the scene to the stage
        primaryStage.setScene(scene);
        // Make the stage (window) visible
        primaryStage.show();

    }


    /**
     * Hide the right details pane
     */
    private void hideDetailsPane() {
        this.centerSPane.setDividerPosition(1, 1);
        this.detailsView.setVisible(false);
        this.detailsView.setManaged(false);
    }

    /**
     * Show the right details pane
     */
    private void showDetailsPane() {
        if (!(this.centerSPane.getDividerPositions()[1] < 0.75f)) {
            this.centerSPane.setDividerPosition(1, 0.75f);
        }
        this.detailsView.setVisible(true);
        this.detailsView.setManaged(true);

    }

    /**
     * Returns an ObservableList holding the literature's to display.
     *
     * @return an ObservableList holding the literature's to display.
     */
    private ObservableList<Literature> getInitialTableData() {
        // Create an ObservableArrayList wrapping the LiteratureRegister
        ObservableList<Literature> data
                = FXCollections.observableArrayList(this.register.getAllLiterature());

        return data;
    }


    /**
     * Fill register with dummy data.
     */
    private void fillWithDummyData() {
        for (int i = 0; i < 20; i++) {
            int j = i % 3;
            register.addLiterature(new Magazine("title" + i, "publisher", 100, "genre" + j));
            register.addLiterature(new Book("title" + i, "publisher", "genre" + j, "author", 1, "01/01/2000", 100));
            register.addLiterature(new Comic("title" + i, "publisher", 100, "genre" + j));
            register.addLiterature(new Journal("title" + i, "publisher", 100, "genre" + j));
            register.addLiterature(new Newspaper("title" + i, "publisher", 100, "genre" + j));
            register.addLiterature(new BookSeries("title" + i, "publisher", "genre" + j, "author"));

        }
    }


    /**
     * Returns a menu bar.
     *
     * @return a menu bar
     */
    private MenuBar buildMenuBar() {
        // Create Menu Bar to hold all the menus
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);


        // Create the File-menu
        Menu menuFile = new Menu("File");
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(e -> System.out.println("Open menu item pressed."));

        Menu exportFile = new Menu("Export as");
        MenuItem textFileExport = new MenuItem("Text file");
        textFileExport.setOnAction(e -> System.out.println("Export as text file menu item pressed."));
        exportFile.getItems().addAll(textFileExport);


        MenuItem print = new MenuItem("Print");
        print.setOnAction(e -> System.out.println("Print out literature list!"));

        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(e -> LiteratureRegisterApp.this.doExitApplication());

        menuFile.getItems().addAll(openFile, exportFile);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(print);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(exitApp);


        // Create Edit-menu
        Menu menuEdit = new Menu("Edit");
        MenuItem copy = new MenuItem("Copy");
        copy.setOnAction(e -> System.out.println("Copy"));

        MenuItem paste = new MenuItem("Paste");
        paste.setOnAction(e -> System.out.println("Paste"));
        menuEdit.getItems().addAll(copy, paste);

        //Create Help-menu
        final Menu menuHelp = new Menu("Help");
        MenuItem aboutHelp = new MenuItem("About");
        aboutHelp.setOnAction(e -> doShowAboutDialog());
        menuHelp.getItems().addAll(aboutHelp);


        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);

        return menuBar;
    }

    private ToolBar buildToolbar() {
        ToolBar toolBar = new ToolBar();
        Button btnNew = new Button("New");
        btnNew.setOnAction(event -> addNewLiterature());

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(event -> {
            Literature literature = LiteratureRegisterApp.this.table.getSelectionModel().getSelectedItem();
            LiteratureRegisterApp.this.deleteLiterature(literature);
        });

        toolBar.getItems().addAll(btnNew, btnDelete);
        return toolBar;
    }


    /**
     * @param data
     * @return
     */
    private TableView<Literature> buildTable(ObservableList<Literature> data) {

        TableView<Literature> table = new TableView<>();

        tableData = new FilteredList<>(data);

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Literature> sortedData = new SortedList<>(tableData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());


        table.setItems(sortedData);

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

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (e.getClickCount() % 2 == 0 && e.getButton().equals(MouseButton.PRIMARY)) {
                            updateLiteratureDetailsView(table.getSelectionModel().getSelectedItem(), false);
                        }
                    }
                }
        );

        return table;
    }

    /**
     * @param filterQuery
     * @param filterParentQuery
     */
    private void filterTable(String filterQuery, String filterParentQuery) {
        // 2. Set the filter Predicate whenever the filter changes.

        tableData.setPredicate(literature -> {
            // If filter text is empty, display all persons.
            if (filterQuery == null || filterQuery.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = filterQuery.toLowerCase();
            if (filterQuery.equals(TREE_ROOT_NAME)) {
                return true;
            }

            if ((filterParentQuery == null) || filterParentQuery.equals(TREE_ROOT_NAME)) {

                if (literature.getType().toLowerCase().equals(lowerCaseFilter)) {
                    return true; // Filter matches type.
                }
            } else {

                String lowerCaseParentFilter = filterParentQuery.toLowerCase();
                if (literature.getType().toLowerCase().equals(lowerCaseParentFilter)
                        && literature.getGenre().toLowerCase().equals(lowerCaseFilter)) {
                    return true; // Filter matches type and genre.
                }
            }
            return false; // Does not match.
        });

    }

    /**
     * Creates and returns a treeView containing literature, types and genre.
     *
     * @return a treeView containing literature, types and genre
     */
    private TreeView<String> buildTree() {
        TreeItem<String> rootItem = new TreeItem<>(TREE_ROOT_NAME);
        rootItem.setExpanded(true);


        for (String type : register.getUniqueTypes()) {
            TreeItem<String> treeItem = new TreeItem<>(type);
            rootItem.getChildren().add(treeItem);
            treeItem.setExpanded(true);

            for (String genre : register.getGenreForLiteratureType(type)) {
                TreeItem<String> treeLeaf = new TreeItem<>(genre);
                treeItem.getChildren().add(treeLeaf);
            }
        }

        TreeView<String> treeView = new TreeView<>(rootItem);

        treeView.setCellFactory(param -> {
            TreeCell<String> treeCell = new TreeCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item);
                    }
                }
            };

            treeCell.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

                if (treeCell.getText() != null) {
                    TreeItem<String> parent = treeCell.getTreeItem().getParent();

                    String parentValue = null;
                    String value = treeCell.getTreeItem().getValue();

                    if (parent != null) {
                        parentValue = parent.getValue();
                    }

                    filterTable(value, parentValue);
                }
            });

            return treeCell;
        });

        return treeView;
    }


    private VBox buildLeftSidebar() {
        VBox sidebarLeft = new VBox();

        // Create progress-bar
        ProgressBar progressBar = new ProgressBar();
        progressBar.prefWidthProperty().bind(sidebarLeft.widthProperty());
        progressBar.setProgress(0.5);//TODO: set to 0.
//        progressBar.progressProperty().bind();//TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!

        // Create tree-view
        VBox treeContainer = new VBox();
        TreeView<String> tree = buildTree();
        treeContainer.getChildren().add(tree);

        VBox.setVgrow(treeContainer, Priority.ALWAYS);
        tree.prefHeightProperty().bind(treeContainer.heightProperty());
        this.treeContainer = treeContainer;

        sidebarLeft.getChildren().addAll(progressBar, treeContainer);

        return sidebarLeft;
    }


    private Pane buildRightPane() {
        BorderPane borderPane = new BorderPane();

        HBox hBox = new HBox();
        hBox.getStyleClass().add("top-bar");
        hBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button();
        button.getStyleClass().add("btn-hide");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LiteratureRegisterApp.this.hideDetailsPane();
            }
        });

        hBox.getChildren().add(button);

        borderPane.setTop(hBox);
        this.detailsView = borderPane;


        return borderPane;

    }

    private VBox buildBottomPane() {
        VBox pane = new VBox();

        // Create TextArea
        TextArea textArea = new TextArea();
        textArea.editableProperty().setValue(false);

        this.terminal = textArea;
        pane.getChildren().add(textArea);

        return pane;
    }


    private void updateLiteratureDetailsView(Literature literature, Boolean editable) {

        LiteratureView view = this.viewFactory.create(literature);
        view.addEventHandler(new literatureUpdateEventHandler());
        view.addEventHandler(new literatureDeleteEventHandler());
        view.addEventHandler(new literatureNewEventHandler());

        if (editable) view.setEditable(true);
        Pane literatureDetailsPane = view.buildUIView(literature);
        literatureDetailsPane.getStyleClass().add("literatureDetails");

        this.detailsView.setCenter(literatureDetailsPane);

        showDetailsPane();
    }


    private void updateTreeView() {
        this.treeContainer.getChildren().clear();
        // Create tree-view

        TreeView<String> tree = buildTree();
        this.treeContainer.getChildren().add(tree);
        tree.prefHeightProperty().bind(this.treeContainer.heightProperty());
    }


    private void updateObservableList() {
        this.literatures.setAll(this.register.getAllLiterature());
    }

    /**
     * Adds a new literature to the literature register based on the information provided by the user.
     */

    // TODO add type-getting method or something...
    private void addNewLiterature() {
        String type = "magazine";

        Literature newLiterature = LiteratureFactory.createLiterature(type);
        register.addLiterature(newLiterature);
        updateObservableList();
        updateTreeView();
        printToTerminalWindow("Literature successfully added!\n");
    }


    /**
     * Adds a new literature to the literature register based on the information provided by the user.
     */

    // TODO add type-getting method or something...
    private void deleteLiterature(Literature literature) {
        if (null != literature) {
            register.removeLiterature(literature);
            hideDetailsPane();
            updateObservableList();
            updateTreeView();
            printToTerminalWindow("Literature successfully removed!\n");
        } else {
            printToTerminalWindow("Error: Literature was not successfully removed!\n");
        }
    }

    private void printToTerminalWindow(String string) {

        this.terminal.appendText(string);
    }

    /**
     * Show a dialog window with "Aboute" info.
     */
    private void doShowAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Literature register - About");
        alert.setHeaderText("Literature register v4.0");
        alert.setContentText("An application created by \n" +
                "André Storhaug and Vebjørn Tomren.\n\n" +
                "© 2017-05-04. All rights reserved.");

        alert.showAndWait();
    }

    /**
     * Show a dialog window for exiting the application.
     */
    private void doExitApplication() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Exit Literature register?");
        alert.setContentText("Are you sure you want to exit this application?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            // user chose OK
            Platform.exit();
        } else {
            // user chooses to CANCEL og closed the dialog.
            // do nothing
        }
    }

    /**
     * Event handler for handling literature updates in external GUI class.
     */
    private class literatureUpdateEventHandler implements LiteratureChangeEventListener {

        public void updateLiterature(LiteratureEvent event) {
            if (event instanceof LiteratureChangeEvent) {
                LiteratureChangeEvent changeEvent = (LiteratureChangeEvent) event;

                Literature literature = changeEvent.getUpdatedLiterature();
                Boolean isEditable = changeEvent.isEditable();

                updateObservableList();
                updateLiteratureDetailsView(literature, !isEditable);
                updateTreeView();
            }
        }
    }


    /**
     * Event handler for handling new literature created in external GUI class.
     */
    private class literatureNewEventHandler implements LiteratureNewEventListener {

        @Override
        public void updateLiterature(LiteratureEvent event) {
            if (event instanceof LiteratureNewEvent) {
                LiteratureNewEvent newEvent = (LiteratureNewEvent) event;

                Literature literature = newEvent.getNewLiterature();
                register.addLiterature(literature);


                updateObservableList();
                updateLiteratureDetailsView(literature, false);
                updateTreeView();
            }
        }

    }

    /**
     * Event handler for handling deleted literature triggered in external GUI class.
     */
    private class literatureDeleteEventHandler implements LiteratureDeleteEventListener {

        @Override
        public void updateLiterature(LiteratureEvent event) {
            if (event instanceof LiteratureDeleteEvent) {
                LiteratureDeleteEvent deleteEvent = (LiteratureDeleteEvent) event;

                Literature deletedLiterature = deleteEvent.getDeletedLiterature();
                deleteLiterature(deletedLiterature);

            }

        }
    }
}