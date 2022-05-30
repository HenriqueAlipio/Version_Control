package system;

import java.time.LocalDate;

public interface Revision extends Comparable<Revision> {

	String getUsername();

	String getProjectName();

	String getArtefactName();

	String getComment();

	int getRevisionNumber();

	LocalDate getDate();
}
