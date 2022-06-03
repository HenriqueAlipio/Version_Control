package system;

import java.util.Comparator;

/**
 * Class which implements the comparator of date and alphabetically,
 * respectively, between two objects.
 * 
 * @author Henrique Alípio / João Marques
 * @param <Artefacts> the type of objects to consider.
 */
public class ComparatorByArtefactOlderDate implements Comparator<Artefact> {

    public int compare(Artefact o1, Artefact o2) {
        if (!o1.getNewestRevisionDate().equals(o2.getNewestRevisionDate())) {
            return -o1.getNewestRevisionDate().compareTo(o2.getNewestRevisionDate());

        }
        return o1.getArtefactName().compareTo(o2.getArtefactName());
    }
}
