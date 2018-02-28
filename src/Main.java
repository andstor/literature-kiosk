import java.util.ArrayList;

/**
 * Main for running application.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 02022018
 */
public class Main {

    public static void main(String[] args) {
        LiteratureRegister register = new LiteratureRegister();

        register.addMagazine(new Magazine("TV Guide", "TVSM inc", 365, "Magazine", "Television"));
        register.addMagazine(new Magazine("US Weekly", "USA now", 30, "Magazine", "News"));
        register.addMagazine(new Magazine("Real Simple", "GVG", 52, "Magazine", "Crafting"));
        register.addMagazine(new Magazine("Cooking Light", "Roald inc", 12, "Magazine", "Cooking"));
        register.addMagazine(new Magazine("Good Housekeeping", "Sandford calc", 30, "Magazine", "Parenting"));
        register.addMagazine(new Magazine("Cherry pie", "Orange Banana", 30, "Magazine", "Cooking"));
        register.addMagazine(new Magazine("Banana split", "Orange Banana", 15, "Magazine", "Cooking"));


        System.out.println("Search for title and publisher:");
        System.out.println(register.findMagazineByTitleAndPublisher("US Weekly", "USA now"));

        System.out.println("\nSearch for publisher:");
        ArrayList<Magazine> searchResult = register.getMagazineByPublisherAsCollection("Orange Banana");
        System.out.println(searchResult);


        System.out.println("\nPrints each magazine title in returned collection:");
        for (Magazine magazine :
                searchResult) {
            System.out.println(magazine.getTitle());
        }

        System.out.println("\nPrints all magazines details:");
        register.listAllMagazinesDetails();
    }
}