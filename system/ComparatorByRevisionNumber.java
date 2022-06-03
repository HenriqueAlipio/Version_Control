package system;

import java.util.Comparator;

/**
 * Class which implements the comparator of number between two objects.
 * 
 * @author Henrique Alípio / João Marques
 * @param <Revision> the type of objects to consider.
 */
public class ComparatorByRevisionNumber implements Comparator<Revision> {

    public int compare(Revision o1, Revision o2) {
        return -(o1.getRevisionNumber() - (o2.getRevisionNumber()));

    }

}