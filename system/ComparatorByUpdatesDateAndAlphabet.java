package system;

import java.util.Comparator;

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
