package system;

import java.time.LocalDate;

/**
 * A revision, with associated characteristics and actions.
 * 
 * @author Henrique Alípio / João Marques
 *
 */
public interface Revision extends Comparable<Revision> {
    /**
     * 
     * @return name of the team member associated.
     */
    String getUsername();

    /**
     * 
     * @return the name of the project associated.
     */
    String getProjectName();

    /**
     * 
     * @return the name of the artefact associated.
     */
    String getArtefactName();

    /**
     * 
     * @return the comment describing the revision.
     */
    String getComment();

    /**
     * 
     * @return the number of the revision.
     */
    int getRevisionNumber();

    /**
     * 
     * @return the date if the revision.
     */
    LocalDate getDate();
}
