package system;

import java.time.LocalDate;
import java.util.Iterator;

public interface InHouse extends Project {
	int getLevel();

	void addMembers(User member);

	boolean isAMember(User member);

	int getNrMembers();

	int getNrArtefacts();

	int getNrRevisions();

	LocalDate getLastRevision();

	boolean hasArtefact(String artefactName);

	void addArtefact(Artefact newArtefact);

	Iterator<User> listMembers();

	Iterator<Artefact> listArtefacts();

	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment);

	Artefact getInfoOfArtefact(String artefactName);
}
