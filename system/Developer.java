package system;

/**
 * A Developer with own associated actions and characteristics.
 * 
 * @author Henrique Alípio / João Marques.
 *
 */
public interface Developer extends User {
    /**
     * 
     * @return manager associated to the developer.
     */
    String getManagerName();

}