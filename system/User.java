package system;

import java.time.LocalDate;
import java.util.Iterator;

public interface User extends Comparable<User> {
	String getUserName();

	String getJob();

	String getManagerName();

	int getLevel();

	void addDev(String devName);

	void addProjectsManager(String projectName);

	void addProjectsDeveloper(String projectName);

	int getNumDev();

	int getNumProjMan();

	int getNumProjDev();

	Iterator<String> listDev();

	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment,int revisionNumber);

	Iterator<Revision> listRevisions();

}
