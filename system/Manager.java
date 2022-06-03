package system;

import java.util.Iterator;

/**
 * A Manager with own associated actions and characteristics.
 * 
 * @author Henrique Alípio / João Marques.
 *
 */
public interface Manager extends User {
    /**
     * Adds a developer to the manager's developers TreeSet, containing all of
     * associated developers' names.
     * 
     * @param devName - name of the developer.
     */
    void addDevToManager(String devName);

    /**
     * Adds a project to the manager's projects TreeSet, containing all of
     * associated projects' names
     * 
     * @param projectName - name of the project.
     */
    void addProjectsManager(String projectName);

    /**
     * 
     * @return number of projects associated to a manager.
     */
    int getNumProjMan();

    /**
     * 
     * @return number of developers associated to a manager.
     */
    int getNumDev();

    /**
     * 
     * @return Iterator containing all of the names of the associated developers.
     */
    Iterator<String> listDev();
}
