import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 1.1
 */
public class ApplicationUI {
    private static final int MAX_PUBLICATIONS_YEARLY = 365;

    private LiteratureRegister register;
    // The menu that will be displayed.
    private String[] menuItems = {
            "1. List all magazines",
            "2. Add new magazine",
            "3. Find a magazine by name and publisher",
    };

    /**
     * Creates an instance of the ApplicationUI user interface.
     */
    public ApplicationUI() {

    }


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
                        System.out.println("\nThank you for using Application v0.1. Bye!\n");
                        quit = true;
                        break;

                    default:
                }
            } catch (InputMismatchException ime) {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.menuItems.length + "src/main\n");
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
        System.out.println("\n**** Application v1.1 ****\n");
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
     * Lists all the products/literature in the register
     * Returns information based on the contained magazines
     */
    private void listAllMagazines() {
        Iterator<Magazine> it = register.getAllMagazines();

        if (it.hasNext()) {
            System.out.println("Current magazines in register:");

            while (it.hasNext()) {
                Magazine magazine = it.next();
                printAllMagazineDetails(magazine);
            }
        } else {
            System.out.println("The registry is empty. Please add magazines.");
        }
    }


    private boolean validateUserInputString(String reading) {
        boolean valid = false;
        if (!(reading == null) && !(reading.trim().isEmpty())) {
            valid = true;
        }
        return valid;
    }

    /**
     * Adds a new magazine based on the information given by the
     * user.
     */

    private void addNewMagazine() {
        Scanner reader = new Scanner(System.in);

        String title = null, publisher = null, type = null, genre = null;
        int publicationsYearly = 0;
        boolean quit;

        quit = false;
        System.out.println("Please enter the title of the magazine:");
        while (!quit) {
            title = reader.nextLine();
            if (!validateUserInputString(title)) {
                System.out.println("Please enter a valid title:");
            } else {
                quit = true;
            }
        }

        quit = false;
        System.out.println("Please enter the publisher of the magazine:");
        while (!quit) {
            publisher = reader.nextLine();
            if (!validateUserInputString(publisher)) {
                System.out.println("Please enter a valid publisher:");
            } else {
                quit = true;
            }
        }

        quit = false;
        System.out.println("Please enter the number of yearly publications of the magazine:");
        while (!quit) {
            if (reader.hasNextInt()) {
                publicationsYearly = reader.nextInt();
                reader.nextLine();
            } else {
                System.out.println("Please enter a valid number between 1 and " + MAX_PUBLICATIONS_YEARLY + ":");
                reader.next();
                continue;
            }

            if ((1 > publicationsYearly) || (MAX_PUBLICATIONS_YEARLY < publicationsYearly)) {
                System.out.println("Please enter a valid number between 1 and " + MAX_PUBLICATIONS_YEARLY + ":");
            } else {
                quit = true;
            }
        }

        quit = false;
        System.out.println("Please enter the type of the magazine:");
        while (!quit) {
            type = reader.nextLine();
            if (!validateUserInputString(type)) {
                System.out.println("Please enter a valid type:");
            } else {
                quit = true;
            }
        }

        quit = false;
        System.out.println("Please enter the genre of the magazine:");
        while (!quit) {
            genre = reader.nextLine();
            if (!validateUserInputString(genre)) {
                System.out.println("Please enter a valid genre:");
            } else {
                quit = true;
            }
        }


        Magazine magazine = new Magazine(title, publisher, publicationsYearly, type, genre);

        register.addMagazine(magazine);
        System.out.println("\nYour added magazine:");
        printAllMagazineDetails(magazine);

    }

    /**
     * Finds a magazine based on the title and the publisher
     * given as a user input.
     */
    private void findMagazineByTitleAndPublisher() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter the title of the magazine here");
        String title = reader.nextLine();
        System.out.println("Enter the publisher of the magazine here");
        String publisher = reader.nextLine();

        Magazine foundMagazine = register.findMagazineByTitleAndPublisher(title, publisher);
        if (null != foundMagazine) {
            System.out.println("The magazine matching the title \"" + title + "\" and the publisher \"" + publisher + "\" is:");
            printAllMagazineDetails(foundMagazine);
        } else {
            System.out.println("This is not an existing magazine in the kiosk.");
        }
    }


    public void printAllMagazineDetails(Magazine magazine) {
        System.out.println("Title: " + magazine.getTitle() + ", Publisher: " + magazine.getPublisher()
                + ", Publications yearly: " + magazine.getPublicationsYearly()
                + ", Type: " + magazine.getType() + ", Genre: " + magazine.getGenre());
    }

}
