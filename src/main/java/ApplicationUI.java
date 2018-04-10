import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 2.0.1
 */
public class ApplicationUI {
    private static final int MAX_PUBLICATIONS_YEARLY = 365;

    private LiteratureRegister register;
    // The menu that will be displayed.
    private String[] menuItems = {
            "1. List all magazines",
            "2. Add new magazine",
            "3. Find a magazine by name and publisher",
            "4. Find all magazines by publisher",
    };

    /**
     * Creates an instance of the ApplicationUI user interface.
     */
    public ApplicationUI() {

    }

    /**
     * Starts the application so the user can input commands through the UI.
     * The application has 5 options.
     * The numbervalues are of data type int, and ranging from 1 to 5.
     * If a different number is inputted, a error message will be displayed.
     */
    public void start() {
        this.init();

        boolean quit = false;

        while (!quit) {
            try {
                int menuSelection = this.showMenu();
                switch (menuSelection) {
                    case 1:
                        this.listAllMagazines();
                        break;

                    case 2:
                        this.addNewMagazine();
                        break;

                    case 3:
                        this.findMagazineByTitleAndPublisher();
                        break;

                    case 4:
                        this.findMagazinesByPublisher();
                        break;

                    case 5:
                        System.out.println("\nThank you for using Application v2.0.1. Bye!\n");
                        quit = true;
                        break;

                    default:
                }
            } catch (InputMismatchException ime) {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.menuItems.length + ".\n");
            }
        }
    }


    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items.
     * If the user inputs anything else, an InputMismatchException is thrown.
     * The method returns the valid input from the user.
     *
     * @return the menu number (between 1 and max menu item number) provided by the user.
     * @throws InputMismatchException if user enters an invalid number/menu choice
     */
    private int showMenu() throws InputMismatchException {
        System.out.println("\n**** Application v2.0.1 ****\n");
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
    // ------ All these methods are made privat, since they are only used by the menu ---


    /**
     * Initializes the application.
     */
    private void init() {
        register = new LiteratureRegister();
    }


    /**
     * Lists all the products/literature in the register.
     * Tells you to add a magazine if the list is empty.
     */
    private void listAllMagazines() {
        Iterator<Literature> it = register.getAllMagazines();

        if (it.hasNext()) {
            System.out.println("\nCurrent magazines in register:");

            while (it.hasNext()) {
                Literature literature = it.next();
                printAllMagazineDetails(literature);
            }
        } else {
            System.out.println("The registry is empty. Please add magazines.");
        }
    }


    /**
     * Adds a new magazine based on the information provided by the user.
     * This includes title, publisher, yearly publications, type and genre.
     * If the user inputs a faulty value, an error message will be displayed.
     */
    private void addNewMagazine() {
        Scanner reader = new Scanner(System.in);

        int publicationsYearly = 0;

        System.out.println("Please enter the title of the magazine:");
        String title = getStringFromUserInput("title");

        System.out.println("Please enter the publisher of the magazine:");
        String publisher = getStringFromUserInput("publisher");

        System.out.println("Please enter the number of yearly publications of the magazine:");


        boolean quit = false;
        while (!quit) {
            if (reader.hasNextInt()) {
                publicationsYearly = reader.nextInt();
                reader.nextLine();

                if ((1 > publicationsYearly) || (MAX_PUBLICATIONS_YEARLY < publicationsYearly)) {
                    System.out.println("Please enter a valid number between 1 and " + MAX_PUBLICATIONS_YEARLY + ":");
                } else {
                    quit = true;
                }

            } else {
                System.out.println("Please enter a valid number between 1 and " + MAX_PUBLICATIONS_YEARLY + ":");
                reader.next();
            }
        }


        System.out.println("Please enter the type of the magazine:");
        String type = getStringFromUserInput("type");

        System.out.println("Please enter the genre of the magazine:");
        String genre = getStringFromUserInput("genre");


        Literature literature = new Magazine(title, publisher, publicationsYearly, type, genre);

        register.addMagazine(literature);
        System.out.println("\nYour added magazine:");
        printAllMagazineDetails(literature);

    }

    /**
     * Finds and prints all information about a magazine, given the title and the publisher
     * provided as a user input.
     */
    private void findMagazineByTitleAndPublisher() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter the title of the magazine:");
        String title = getStringFromUserInput("title");

        System.out.println("Enter the publisher of the magazine:");
        String publisher = getStringFromUserInput("publisher");

        Literature foundLiterature = register.findMagazineByTitleAndPublisher(title, publisher);

        if (null != foundLiterature) {
            System.out.println("\nThe magazine matching the title \"" + title + "\" and the publisher \"" + publisher + "\" is:");
            printAllMagazineDetails(foundLiterature);
        } else {
            System.out.println("There are no magazine in the kiosk matching the title \"" + title + "\" and the publisher \"" + publisher + "\".");
        }
    }


    /**
     * Finds and prints all magazines matching the publisher provided by the input from the user.
     * If there are no magazines in the literature registry matching the
     * publisher given, an error message is printed.
     */
    private void findMagazinesByPublisher() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter the publisher of the magazine(s) here");
        String publisher = getStringFromUserInput("publisher");

        Iterator<Literature> magazineIt = register.getMagazineByPublisherAsCollection(publisher);
        if (magazineIt.hasNext()) {
            System.out.println("\nThe magazines matching the publisher \"" + publisher + "\" is:");
            while (magazineIt.hasNext()) {
                printAllMagazineDetails(magazineIt.next());
            }
        } else {
            System.out.println("There are no magazines in the kiosk matching the publisher \"" + publisher + "\".");
        }
    }


    /**
     * Gets the string input from the terminal passed by the user.
     *
     * @param magazineAttribute the magazine attribute to be presented in the error message to the user
     * @return the string input from the terminal passed by the user
     */
    private String getStringFromUserInput(String magazineAttribute) {
        Scanner reader = new Scanner(System.in);
        boolean quit = false;
        String input = null;

        while (!quit) {
            input = reader.nextLine();
            if (!isValidString(input)) {
                System.out.println("Please enter a valid " + magazineAttribute + ":");
            } else {
                quit = true;
            }
        }
        return input;
    }


    /**
     * Validates a string passed as parameter to ensure it is not null or empty.
     *
     * @param string the string to be validated
     * @return true if the string passed as parameter is valid, otherwise false is returned.
     */
    private boolean isValidString(String string) {
        boolean valid = false;
        if (!(string == null) && !(string.trim().isEmpty())) {
            valid = true;
        }
        return valid;
    }

    /**
     * Prints a formatted string containing all the details about the magazine, on the form
     * "Title: title, Publisher: publisher, Number of yearly publications: publicationsYearly,
     * Type of reading material: type, Genre: genre".
     */
    private void printAllMagazineDetails(Literature literature) {
        System.out.println("Title: " + literature.getTitle() + ", Publisher: " + literature.getPublisher()
                + ", Publications yearly: " + literature.getPublicationsYearly()
                + ", Type: " + literature.getType() + ", Genre: " + literature.getGenre());
    }

}