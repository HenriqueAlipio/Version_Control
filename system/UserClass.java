package system;

import java.time.LocalDate;
import java.util.*;

public class UserClass implements User {
	private String job, username, managerName;
	private int level;
	private SortedSet<String> devNames, projectsNamesOfMan, projectsNamesOfDev;
	private Set<Revision> revisions;

	public UserClass(String jobPosition, String username, String managerName, int level) {
		this.job = jobPosition;
		this.username = username;
		this.managerName = managerName;
		this.level = level;
		devNames = new TreeSet<>();
		projectsNamesOfMan = new TreeSet<>();
		projectsNamesOfDev = new TreeSet<>();
		revisions = new TreeSet<Revision>(new ComparatorByDateRevisionNumberAndName());
	}

	public void addDev(String devName) {
		devNames.add(devName);
	}

	public void addProjectsManager(String projectName) {
		projectsNamesOfMan.add(projectName);
	}

	public void addProjectsDeveloper(String projectName) {
		projectsNamesOfDev.add(projectName);
	}

	public String getJob() {
		return job;
	}

	public String getUserName() {
		return username;
	}

	public String getManagerName() {
		return managerName;
	}

	public int getLevel() {
		return level;
	}

	public int getNumDev() {
		return devNames.size();
	}

	public int getNumProjMan() {
		return projectsNamesOfMan.size();
	}

	public int getNumProjDev() { // projeto em que sao membros ou seja managers e/ou desenvolvidores
		return projectsNamesOfDev.size() + projectsNamesOfMan.size();
	}

	public int compareTo(User o) {
		return this.getUserName().compareTo(o.getUserName());
	}

	public Iterator<String> listDev() {
		return devNames.iterator();
	}

	public void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment,
			int revisionNumber) {
		Revision newRevision = new RevisionClass(username, projectName, artefactName, date, comment, revisionNumber);
		revisions.add(newRevision);
	}

	public Iterator<Revision> listRevisions() {
		return revisions.iterator();
	}

}
