package system;

/**
 * An OutSourcedProject, with own associated characteristics and actions.
 * 
 * @author Henrique Alípio / João Marques
 *
 */
public interface OutSourced extends Project {
    /**
     * 
     * @return the name of the associated company.
     */
    String getCompanyName();
}
