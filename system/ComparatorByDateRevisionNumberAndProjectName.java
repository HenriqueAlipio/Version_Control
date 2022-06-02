package system;

import java.util.Comparator;

public class ComparatorByDateRevisionNumberAndProjectName implements Comparator<InHouse> {// Comparador do objeto
																							// projeto

	public int compare(InHouse o1, InHouse o2) {
		if (!o1.getLastRevision().equals(o2.getLastRevision())) {
			return -o1.getLastRevision().compareTo(o2.getLastRevision()); // sinal menos para ordenar do mais recente
																			// para o mais antigo
		}
		if (o1.getNrRevisions() < (o2.getNrRevisions())) {
			return -o1.getNrRevisions() - (o2.getNrRevisions());

		}
		return o1.getProjectName().compareTo(o2.getProjectName());
	}

}
