package system;

import java.time.LocalDate;
import java.util.*;

public class UserClass implements User {
	private String job, username, managerName;
	private int level;
	private LocalDate lastUpdateDate;
	private SortedSet<String> devNames, projectsNamesOfMan, projectsNamesOfDev,projectsNames;
	private Map<Integer,Revision> revisions;

	public UserClass(String jobPosition, String username, String managerName, int level) {
		this.job = jobPosition;
		this.username = username;
		this.managerName = managerName;
		this.level = level;
		lastUpdateDate = LocalDate.MIN;
		devNames = new TreeSet<String>();
		projectsNamesOfMan = new TreeSet<String>();
		projectsNamesOfDev = new TreeSet<String>();
		projectsNames=new TreeSet<String>();
		revisions = new TreeMap<Integer,Revision>();
	}

	public void addDev(String devName) {
		devNames.add(devName);
	}

	public void addProjectsManager(String projectName) {
		projectsNamesOfMan.add(projectName);
		projectsNames.add(projectName);
	}

	public void addProjectsDeveloper(String projectName) {
		projectsNamesOfDev.add(projectName);
		projectsNames.add(projectName);
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

	public int getNumUpdates() {
		return revisions.size();
	}

	public LocalDate getLastUpdateDate() {
		return lastUpdateDate;
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
		revisions.put(revisions.size()+1,newRevision);
		if (lastUpdateDate.isBefore(date)) {
			lastUpdateDate = date;
		}
	}

	public Iterator<Revision> listRevisions() {
		SortedSet<Revision>revisionsSorted=new TreeSet<Revision>(new ComparatorByDateRevisionNumberAndName());
		revisionsSorted.addAll(revisions.values());
		return revisionsSorted.iterator();
	}

	public Iterator<String> listProjects() {
		return projectsNames.iterator();
	}
	

}
