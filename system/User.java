package system;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * A user (Manager/Developer) with associated in common characteristics and
 * actions.
 * 
 * @author Henrique Alípio / João Marques.
 *
 */
public interface User extends Comparable<User> {

	/**
	 * 
	 * @return the userName associated to a user.
	 */
	String getUserName();

	/**
	 * 
	 * @return returns the job position of a user.
	 */
	String getJob();

	/**
	 *
	 * @return the integer corresponding to the user's clearance level.
	 */
	int getLevel();

	/**
	 * 
	 * @return the number of developed projects of a user.
	 */
	int getNumProjDev();

	/**
	 * 
	 * @return the number of a user has made.
	 */
	int getNumUpdates();

	/**
	 * 
	 * @param user2 - user to compare the projects with.
	 * @return number of projects in common between two users.
	 */

	int getCommonProjects(User user2);

	/**
	 * Adds a project to a developers SortedSet containing all of his projects
	 * names.
	 * 
	 * @param projectName - name of the project.
	 */
	void addProjectsDeveloper(String projectName);

	/**
	 * Adds a new project to a SortedSet containing all projects names;
	 * 
	 * @param projectName - name of the project.
	 */
	void addProject(String projectName);

	/**
	 * Adds a new revision to the user.
	 * 
	 * @param username       - name of the user.
	 * @param projectName    - name of the project.
	 * @param artefactName   - name of the artefact.
	 * @param date           - date associated.
	 * @param comment        - comment associated.
	 * @param revisionNumber - number associated.
	 */
	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment,
			int revisionNumber);

	/**
	 * 
	 * @return the date of the last revisions made by a user.
	 */

	LocalDate getLastUpdateDate();

	/**
	 * 
	 * @return an iterator which contains all the revisions made by a user.
	 */
	Iterator<Revision> listRevisions();

	/**
	 * 
	 * @return an iterator which contains all the existing projects names of a user.
	 */
	Iterator<String> listProjects();

	/**
	 * 
	 * @return SortedSet which contains all the existing projects of a user.
	 */
	SortedSet<String> getSetProjects();

}
