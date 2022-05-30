package system;

import java.time.LocalDate;
import java.util.Iterator;

public interface Artefact extends Comparable<Artefact> {

	String getUsername();

	String getArtefactName();

	int getLevel();

	String getDescription();

	LocalDate getDate();

	LocalDate getNewestRevisionDate();

	int getRevisionsNumberInArtefact();

	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment);

	Iterator<Revision> listRevisionsByDate();

}
