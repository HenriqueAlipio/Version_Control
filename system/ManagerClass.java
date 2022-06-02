package system;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ManagerClass extends UserClass implements Manager {
	private SortedSet<String> devNames, projectsNamesOfMan;

	public ManagerClass(String jobPosition, String username, int level) {
		super(jobPosition, username, level);
		projectsNamesOfMan = new TreeSet<String>();
		devNames = new TreeSet<String>();
	}

	public void addProjectsManager(String projectName) {
		projectsNamesOfMan.add(projectName);
	}

	public int getNumProjMan() {
		return projectsNamesOfMan.size();
	}

	public void addDevToManager(String devName) {
		devNames.add(devName);

	}

	public int getNumDev() {
		return devNames.size();
	}

	public Iterator<String> listDev() {
		return devNames.iterator();
	}

}
