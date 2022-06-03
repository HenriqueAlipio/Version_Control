package system;

/**
 * A project, with associated common characteristics and actions.
 * 
 * @author Henrique Alípio / João Marques
 *
 */
public interface Project extends Comparable<Project> {
    /**
     * 
     * @return name of the user associated.
     */
    String getUsername();

    /**
     * 
     * @return type of the project.
     */
    String getType();

    /**
     * 
     * @return name of the project.
     */
    String getProjectName();

    /**
     * 
     * @return number of keywords associated.
     */
    int getValue();

    /**
     * 
     * @return keywords associated.
     */
    String getKeyWords();

}