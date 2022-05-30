package system;

import java.util.Comparator;

public class ComparatorByArtefactOlderDate implements Comparator<Artefact>{

	public int compare(Artefact o1, Artefact o2) {
		if (!o1.getNewestRevisionDate().equals(o2.getNewestRevisionDate())) {
			return -o1.getNewestRevisionDate().compareTo(o2.getNewestRevisionDate()); //sinal menos para ordenar do mais recente para o mais antigo
		}
		return o1.getArtefactName().compareTo(o2.getArtefactName());
	}
}
