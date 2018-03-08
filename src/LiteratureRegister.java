import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a registry containing a number of
 * magazines.
 *
 * @author André Storhaug, Christan Leira and Vebjørn Tomren
 * @version 1.0
 */
public class LiteratureRegister {

    private ArrayList<Magazine> listOfMagazines;

    /**
     * Constructor for objects of class LiteratureRegister.
     */
    public LiteratureRegister() {
        this.listOfMagazines = new ArrayList<>();
    }


    /**
     * Adds a magazine to the literature registry.
     * @param magazine the magazine to add to the literature registry
     */
    public void addMagazine(Magazine magazine) {
        this.listOfMagazines.add(magazine);
    }

    /**
     * Removes a magazine from the literature registry.
     * @param magazine the magazine to remove from the literature registry
     */
    public void removeMagazine(Magazine magazine) {
        this.listOfMagazines.remove(magazine);
    }

    /**
     * Lists all magazines in the literature registry by printing to the
     * terminal window.
     */
    public void listAllMagazinesDetails() {
        for (Magazine m : this.listOfMagazines) {
            System.out.println(m.getAllDetailsAsString());
        }
    }

    /**
     * Finds and returns one magazine with a title matching both the parameters title and publisher.
     * If there are no magazines in the literature registry matching the
     * parameters given, <code>null</code> is returned.
     *
     * @param title the title of the magazine to find in the literature registry
     * @param publisher the publisher of the magazine to find in the literature registry
     * @return the magazine found in the literature registry matching the parameters provided; otherwise,
     *         <code>null</code> is returned.
     */
    public Magazine findMagazineByTitleAndPublisher(String title, String publisher) {
        Magazine foundMagazine = null;

        Iterator<Magazine> it = this.listOfMagazines.iterator();
        while ((null == foundMagazine) && (it.hasNext())) {
            Magazine magazine = it.next();
            if ((magazine.getTitle().equals(title)) && (magazine.getPublisher().equals(publisher))) {
                foundMagazine = magazine;
            }
        }
        return foundMagazine;
    }

    /**
     * Finds and returns a Iterator with magazines
     * with a publisher matching the parameter publisher.
     * If there are no magazines in the literature registry matching the
     * publisher given, an empty  is returned.
     *
     * @param publisher The publisher of the magazine to find in the literature registry.
     * @return an <code>Iterator</code> of magazine objects found in the literature
     * registry matching the publisher provided
     */
    public Iterator<Magazine> getMagazineByPublisherAsCollection(String publisher) {
        ArrayList<Magazine> listOfFoundMagazines = new ArrayList<>();

        for (Magazine m : this.listOfMagazines) {
            if (publisher.equals(m.getPublisher())){
                listOfFoundMagazines.add(m);
            }
        }
        return listOfFoundMagazines.iterator();
    }
}