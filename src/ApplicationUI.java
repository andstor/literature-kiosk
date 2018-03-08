import java.util.InputMismatchException;
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

    // The menu that will be displayed.
    private String[] menuItems = {
            "1. List all magazines",
            "2. Add new magazine",
            "3. Find a magazine by name",
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
                        this.findMagazineByName();
                        break;

                    case 4:
                        System.out.println("\nThank you for using Application v0.1. Bye!\n");
                        quit = true;
                        break;

                    default:
                }
            } catch (InputMismatchException ime) {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.menuItems.length + "..\n");
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
     * TODO:
     * Initializes the application.
     * Typically you would create the LiteratureRegistrer-instance here
     */
    private void init() {
        System.out.println("init() was called");

        LiteratureRegister register = new LiteratureRegister();

    }

    /**
     * TODO:
     * Lists all the products/literature in the register
     */
    private void listAllMagazines() {
        System.out.println("listAllMagazines() was called");
    }


    /**
     * TODO:
     * Add a new product/literature to the register.
     * In this method you have to add code to ask the
     * user for the necessary information you need to
     * create an instance of the product, which you
     * then send as a parameter to the addNewspaper()-
     * method of the register.
     * Remember to also handle invalid input from the
     * user!!
     */
    private void addNewMagazine() {

        System.out.println("addNewMagazine() was called");

    }

    /**
     * TODO:
     * Find and display a product based om name (title).
     * As with the addNewMagazine()-method, you have to
     * ask the user for the string (name/title/publisher)
     * to search for, and then use this string as input-
     * parameter to the method in the register-object.
     * Then, upon return from the register, you need
     * to print the details of the found item.
     */
    private void findMagazineByName() {
        System.out.println("findMagazineByName() was called");
    }

}
