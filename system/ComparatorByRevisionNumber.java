package system;

import java.util.Comparator;

public class ComparatorByRevisionNumber implements Comparator<Revision> {

	public int compare(Revision o1, Revision o2) {
		return -(o1.getRevisionNumber()-(o2.getRevisionNumber())); // sinal menos para ordenar do maior número para o menor
	}

}
