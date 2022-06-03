package system;

import java.util.Comparator;

/**
 * Class which implements the comparator of number of revisions, number of
 * associated developers, revision date and alaphabetically, respectively,
 * between two objects.
 * 
 * @author Henrique Alípio / João Marques
 * @param <User> the type of objects to consider.
 */
public class ComparatorByUpdatesDateAndAlphabet implements Comparator<User> {
    public int compare(User o1, User o2) {
        if (o1.getNumUpdates() < (o2.getNumUpdates())) {
            return 1;
        }
        if (o1.getNumUpdates() > (o2.getNumUpdates())) {
            return -1;
        } else {
            if (o1.getNumProjDev() < (o2.getNumProjDev())) {
                return 1;
            }
            if (o1.getNumProjDev() > (o2.getNumProjDev())) {
                return -1;
            } else {
                if (!o1.getLastUpdateDate().equals(o2.getLastUpdateDate())) {
                    return -(o1.getLastUpdateDate().compareTo(o2.getLastUpdateDate()));
                }
                return o1.getUserName().compareTo(o2.getUserName());
            }
        }
    }
}
