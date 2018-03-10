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
        Scanner reader = new Scanner(System.in);
        Iterator<Magazine> it = register.getAllMagazines();
        
        if (it.hasNext()) {
            System.out.println("Current magazines in register:");

            while (it.hasNext()){
                Magazine magazine = it.next();
                System.out.println("Title: " + magazine.getTitle() + ", Publisher: " + magazine.getPublisher()
                        + ", Publications yearly: " + magazine.getPublicationsYearly()
                        + ", Type: " + magazine.getType() + ", Genre: " + magazine.getGenre());
            }
        } else {
            System.out.println("The registry is empty. Please add magazines.");
        }
    }


    /**
     * Adds a new magazine based on the information given by the
     * user.
     */
    private void addNewMagazine() {
        Scanner reader = new Scanner(System.in);

            System.out.println("Please specify the title of the magazine.");
            String title = reader.nextLine();

            System.out.println("Please specify the publisher of the magazine.");
            String publisher = reader.nextLine();

            System.out.println("Please specify the publication by year of the magazine.");
            int publicationsYearly = reader.nextInt();
            reader.nextLine();

            System.out.println("Please specify the type of the magazine.");
            String type = reader.nextLine();
            System.out.println("Please specify the genre of the magazine.");
            String genre = reader.nextLine();

            Magazine magazine = new Magazine(title, publisher, publicationsYearly, type, genre);

            register.addMagazine(magazine);
            System.out.println("Your added magazine:");
            System.out.println(magazine.getAllDetailsAsString());

    }

    /**
     * Finds a magazine based on the title and the publisher
     * given as a user input.
     */
    private void findMagazineByName() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the title of the magazine here");
        String title = reader.nextLine();
        System.out.println("Enter the publisher of the magazine here");
        String publisher = reader.nextLine();
        Magazine foundMagazine = register.findMagazineByTitleAndPublisher(title, publisher);
        if (foundMagazine != null) {
            System.out.println(foundMagazine.getAllDetailsAsString());
        } else {
            System.out.println("This is not an existing magazine in the kiosk");
            System.out.println("Check back in another week if it is available");
        }
    }

}
