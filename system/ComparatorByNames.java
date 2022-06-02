package system;

import java.util.Comparator;
import java.util.SortedSet;

public class ComparatorByNames implements Comparator<SortedSet<String>> {

	public int compare(SortedSet<String> o1, SortedSet<String> o2) {
		String name1 = o1.iterator().next();
		String name2 = o2.iterator().next();
		if (!name1.equals(name2)) {
			return -name1.compareTo(name2);
		}
		return o1.iterator().next().compareTo(o2.iterator().next());
	}

}
