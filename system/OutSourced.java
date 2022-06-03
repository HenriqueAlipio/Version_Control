package system;

/**
 * An OutSourcedProject, with own associated characteristics and actions.
 * 
 * @author Henrique Al�pio / Jo�o Marques
 *
 */
public interface OutSourced extends Project {
    /**
     * 
     * @return the name of the associated company.
     */
    String getCompanyName();
}
