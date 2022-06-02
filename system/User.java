package system;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.SortedSet;

public interface User extends Comparable<User> {
	String getUserName();

	String getJob();

	int getLevel();

	int getNumProjDev();

	int getNumUpdates();

	LocalDate getLastUpdateDate();

	void addProjectsDeveloper(String projectName);

	void addProject(String projectName);

	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment,
			int revisionNumber);

	Iterator<Revision> listRevisions();

	Iterator<String> listProjects();

	int getCommonProjects(User user2);
	SortedSet<String> getSetProjects();

}
