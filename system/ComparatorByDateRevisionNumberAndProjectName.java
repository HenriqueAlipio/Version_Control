package system;

import java.util.Comparator;

/**
 * Class which implements the comparator of date, revision number and project
 * name, respectively, between two objects.
 * 
 * @author Henrique Alípio / João Marques
 * @param <InHouse> the type of object to consider.
 */
public class ComparatorByDateRevisionNumberAndProjectName implements Comparator<InHouse> {
    public int compare(InHouse o1, InHouse o2) {
        if (!o1.getLastRevision().equals(o2.getLastRevision())) {
            return -o1.getLastRevision().compareTo(o2.getLastRevision());

        }
        if (o1.getNrRevisions() < (o2.getNrRevisions())) {
            return -o1.getNrRevisions() - (o2.getNrRevisions());

        }
        return o1.getProjectName().compareTo(o2.getProjectName());
    }

}