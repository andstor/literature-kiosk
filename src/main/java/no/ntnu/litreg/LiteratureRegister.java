package no.ntnu.litreg;

import java.util.*;

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
    public void addLiterature(Literature literature) {
        if (null != literature) {
            this.listOfLiterature.add(literature);
        } else {
            throw new InputMismatchException("Not a valid literature type");
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
        if (null == literature) {
            throw new NullPointerException("Literature can't be null");
        }

        removeSuccessful = this.listOfLiterature.remove(literature);

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
     * @
     */
    public Literature getLiteratureByTitleAndPublisher(String title, String publisher) {
        Literature foundLiterature = null;

        try {
            Iterator<Literature> it = this.listOfLiterature.iterator();
            while ((null == foundLiterature) && (it.hasNext())) {
                Literature literature = it.next();
                if ((literature.getTitle().equals(title)) && (literature.getPublisher().equals(publisher))) {
                    foundLiterature = literature;
                }
            }

        } catch (NoSuchElementException error) {
            System.out.println("Error: " + error);
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


    /**
     * Retrieves a Iterator with all literature matching the provided genre.
     *
     * @param genre the genre to be searched for.
     * @return
     * @throws IllegalArgumentException if the genre is invalid.
     */
    public Iterator<Literature> getLiteratureByTypeAsCollection(String genre) {
        if (genre == null) {
            throw new IllegalArgumentException("null key in getLiteratureByTypeAsCollection");
        } else if (genre.trim().length() == 0) {
            throw new IllegalArgumentException("Empty key passed to getLiteratureByTypeAsCollection");

        }

        List<Literature> literature = null;
        Set<String> genres = getUniqueGenres();
        for (String g : genres) {

        }
        return literature.iterator();
    }


    /**
     * Retrieves a unique set of literature types currently in use the literature register.
     *
     * @return a unique set of literature types currently in use the literature register
     */
    public Set<String> getUniqueTypes() {
        Set<String> typesSet = new HashSet<>();

        for (Literature literature : this.listOfLiterature) {
            typesSet.add(literature.getType());
        }

        return typesSet;
    }


    /**
     * Retrieves a unique set of literature genres currently in use the literature register.
     *
     * @return a unique set of literature genres currently in use the literature register
     */
    public Set<String> getUniqueGenres() {
        Set<String> genresSet = new HashSet<>();

        for (Literature literature : this.listOfLiterature) {
            genresSet.add(literature.getGenre());
        }

        return genresSet;
    }


    /**
     * @param type
     * @return
     */
    public Set<String> getGenreForLiteratureType(String type) {
        Set<String> genresSet = new HashSet<>();

        for (Literature literature : listOfLiterature) {
            if (literature.getType().equals(type)) {
                genresSet.add(literature.getGenre());
            }
        }

        return genresSet;
    }


}