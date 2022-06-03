package system;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * An InHouseProject, with own associated characteristics and actions.
 * 
 * @author Henrique Alípio / João Marques
 *
 */
public interface InHouse extends Project {
	/**
	 * 
	 * @return the confidentiality level of the InHouseProject.
	 */
	int getLevel();

	/**
	 * 
	 * @return the number of members associated.
	 */
	int getNrMembers();

	/**
	 * 
	 * @return the number of artefacts associated.
	 */
	int getNrArtefacts();

	/**
	 * 
	 * @return the number of revisions associated.
	 */
	int getNrRevisions();

	/**
	 * 
	 * @return the date of the last associated revision.
	 */
	LocalDate getLastRevision();

	/**
	 * Verifies is a user is enrolled in the InHouseProject.
	 * 
	 * @param member - user to verify.
	 * @return true if the user "member" is enrolled in the InHouseProject.
	 */
	boolean isAMember(User member);

	/**
	 * Verifies if a artefact is part of the InHouseProject.
	 * 
	 * @param artefactName - name of the artefact.
	 * @return true if the artefact "artefactName" is part of the InHouseProject.
	 */
	boolean hasArtefact(String artefactName);

	/**
	 * Adds a member to the InHouseProject.
	 * 
	 * @param member - member to add.
	 */
	void addMembers(User member);

	/**
	 * Adds an artefact to the InHouseProject.
	 * 
	 * @param newArtefact - artefact to add.
	 */
	void addArtefact(Artefact newArtefact);

	/**
	 * Adds a revision to the InHouseProject.
	 * 
	 * @param username     - name of the user.
	 * @param projectName  - name of the project.
	 * @param artefactName - name of the artefact.
	 * @param date         - date associated.
	 * @param comment      - comment associated.
	 */
	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment);

	/**
	 * 
	 * @return Iterator containing all member associated.
	 */
	Iterator<User> listMembers();

	/**
	 * 
	 * @return Iterator containing all artefacts associated.
	 */
	Iterator<Artefact> listArtefacts();

	/**
	 * 
	 * @param artefactName - name.
	 * @return Artefact with equal name.
	 */
	Artefact getInfoOfArtefact(String artefactName);
}
