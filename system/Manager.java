package system;

import java.util.Iterator;

public interface Manager extends User {
	void addDevToManager(String devName);

	void addProjectsManager(String projectName);

	int getNumProjMan();

	int getNumDev();

	Iterator<String> listDev();
}
