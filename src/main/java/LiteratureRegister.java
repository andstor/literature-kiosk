import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a registry containing a number of
 * magazines.
 *
 * @author André Storhaug and Vebjørn Tomren
 * @version 3.0.0
 */
public class LiteratureRegister implements Iterable<Literature> {

    private ArrayList<Literature> listOfLiterature;

    /**
     * Constructor for objects of class LiteratureRegister.
     */
    public LiteratureRegister() {
        this.listOfLiterature = new ArrayList<>();
    }


    @Override
    public Iterator<Literature> iterator() {
        return listOfLiterature.iterator();
    }


    /**
     * Adds a magazine to the literature registry.
     *
     * @param literature the magazine to add to the literature register
     * @return true if magazine was successfully added to the register. false otherwise.
     */
    public boolean addLiterature(Literature literature) {
        if (null != literature) {
            this.listOfLiterature.add(literature);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a magazine from the literature registry.
     *
     * @param literature the magazine to remove from the literature registry
     * @return true if magazine was successfully removed from the register; false otherwise.
     */
    public boolean removeLiterature(Literature literature) {
        boolean removeSuccessful = false;
        if (null != literature) {
            removeSuccessful = this.listOfLiterature.remove(literature);
        } else {
            removeSuccessful = false;
        }
        return removeSuccessful;
    }

    /**
     * Returns a list with all the literature in the literature registry.
     *
     * @return an list with all the literature in the literature registry.
     */
    public List<Literature> getAllLiterature() {
        return listOfLiterature;
    }

    /**
     * Finds and returns one magazine with a title matching both the parameters title and publisher.
     * If there are no magazines in the literature registry matching the
     * parameters given, <code>null</code> is returned.
     *
     * @param title     the title of the magazine to find in the literature registry
     * @param publisher the publisher of the magazine to find in the literature registry
     * @return the magazine found in the literature registry matching the parameters provided; otherwise,
     * <code>null</code> is returned
     */
    public Literature getLiteratureByTitleAndPublisher(String title, String publisher) {
        Literature foundLiterature = null;

        Iterator<Literature> it = this.listOfLiterature.iterator();
        while ((null == foundLiterature) && (it.hasNext())) {
            Literature literature = it.next();
            if ((literature.getTitle().equals(title)) && (literature.getPublisher().equals(publisher))) {
                foundLiterature = literature;
            }
        }
        return foundLiterature;
    }

    /**
     * Finds and returns a Iterator with literature
     * with a publisher matching the parameter publisher.
     * If there are no literature in the literature registry matching the
     * publisher given, an empty Iterator object is returned.
     *
     * @param publisher The publisher of the literature to find in the literature registry
     * @return an <code>Iterator</code> of literature objects found in the literature
     * registry matching the publisher provided
     */
    public Iterator<Literature> getLiteratureByPublisherAsCollection(String publisher) {
        ArrayList<Literature> listOfFoundLiterature = new ArrayList<>();

        for (Literature literature : this.listOfLiterature) {
            if (publisher.equals(literature.getPublisher())) {
                listOfFoundLiterature.add(literature);
            }
        }
        return listOfFoundLiterature.iterator();
    }

}