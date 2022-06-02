package system;

import java.time.LocalDate;
import java.util.*;

abstract class UserClass implements User {
	private String job, username;
	private int level;
	private LocalDate lastUpdateDate;
	private SortedSet<String> projectsNames;
	private SortedSet<String>  projectsNamesOfDev;
	private Map<Integer, Revision> revisions;

	public UserClass(String jobPosition, String username, int level) {
		this.job = jobPosition;
		this.username = username;
		this.level = level;
		lastUpdateDate = LocalDate.MIN;
		projectsNames = new TreeSet<String>();
		projectsNamesOfDev = new TreeSet<String>();
		revisions = new TreeMap<Integer, Revision>();
	}

	public String getJob() {
		return job;
	}

	public String getUserName() {
		return username;
	}

	public int getLevel() {
		return level;
	}
	public void addProjectsDeveloper(String projectName) {
		projectsNamesOfDev.add(projectName);
	}

	public void addProject(String projectName) {
		projectsNames.add(projectName);
	}

	public int getNumProjDev() { // projeto em que sao membros ou seja managers e/ou desenvolvidores
		return projectsNames.size();
	}

	public int getNumUpdates() {
		return revisions.size();
	}

	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
	}

	public int compareTo(User o) {
		return this.getUserName().compareTo(o.getUserName());
	}

	public void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment,
			int revisionNumber) {
		Revision newRevision = new RevisionClass(username, projectName, artefactName, date, comment, revisionNumber);
		revisions.put(revisions.size() + 1, newRevision);
		if (lastUpdateDate.isBefore(date)) {
			lastUpdateDate = date;
		}
	}

	public Iterator<Revision> listRevisions() {
		SortedSet<Revision> revisionsSorted = new TreeSet<Revision>(new ComparatorByDateRevisionNumberAndName());
		revisionsSorted.addAll(revisions.values());
		return revisionsSorted.iterator();
	}

	public Iterator<String> listProjects() {
		return projectsNames.iterator();
	}

}
