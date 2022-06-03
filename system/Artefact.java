package system;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * 
 * @author Henrique Alípio / João Marques
 *
 */
public interface Artefact extends Comparable<Artefact> {
    /**
     * 
     * @return name of the associated user.
     */
    String getUsername();

    /**
     * 
     * @return name of the artefact.
     */
    String getArtefactName();

    /**
     * 
     * @return confidentiality level of the artefact.
     */
    int getLevel();

    /**
     * 
     * @return description of the artifact.
     */
    String getDescription();

    /**
     * 
     * @return date of the artifact.
     */
    LocalDate getDate();

    /**
     * 
     * @return date of the most recent revision.
     */
    LocalDate getNewestRevisionDate();

    /**
     * 
     * @return number of revisions of the artifact.
     */
    int getRevisionsNumberInArtefact();

    /**
     * Adds a new revision to the artefact.
     * 
     * @param username     - name of the user.
     * @param projectName  - name of the project.
     * @param artefactName - name of the artefact.
     * @param date         - date of the artefact.
     * @param comment      - comment of the artefact.
     */
    void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment);

    /**
     * 
     * @return Iterator containing all revision orderer by date.
     */
    Iterator<Revision> listRevisionsByDate();

}