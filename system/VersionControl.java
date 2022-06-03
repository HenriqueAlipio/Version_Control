package system;

import java.time.LocalDate;
import java.util.Iterator;
import system.exceptions.*;

public interface VersionControl {
	/**
	 * 
	 * @return the number of the common projects
	 */
	int maxNumberCommon();

	/**
	 * 
	 * @param user - name of the user
	 * @return the given user object
	 */
	User getInfoOfUser(String user);

	/**
	 * 
	 * @param projectName - name of the in house project
	 * @return the given in house project object
	 */
	InHouse getInfoOfProjectInHouse(String projectName);

	/**
	 * 
	 * @param projectName - name of the project
	 * @return the given project object
	 */
	Project getInfoOfProject(String projectName);

	/**
	 * 
	 * @param projectName - name of the project
	 * @param username    - name of the user
	 * @return the artefact object
	 */
	Artefact getInfoOfArtefact(String projectName, String username);

	/**
	 * Regists a new User to the system
	 * 
	 * @param jobPosition - job position
	 * @param username    - name of the user
	 * @param managerName - name of the manager
	 * @param level       - level of the user
	 * @throws UnknownJobException          - if the job position is not manager or
	 *                                      developer
	 * @throws UserAlreadyExistsException   - if a user already exists with the
	 *                                      given name
	 * @throws ManagerDoesNotExistException - if no manager exists with the given
	 *                                      manager name
	 */
	void registUser(String jobPosition, String username, String managerName, int level)
			throws UnknownJobException, UserAlreadyExistsException, ManagerDoesNotExistException;

	/**
	 * Adds a new Project to the system
	 * 
	 * @param username             - name of the manager in charge
	 * @param projectType          - type of the project
	 * @param projectName          - name of the project
	 * @param value                - number of keywords
	 * @param keywords             - keywords
	 * @param confidentialityLevel - confidentility level, if is an in-house project
	 * @param companyName          - name of company, if is an outsourced project
	 * @throws UnknownProjectTypeException       - if the project type is not
	 *                                           in-house or outsourced
	 * @throws ManagerDoesNotExistException      - if no manager exixts with the
	 *                                           given name of the manager in charge
	 * @throws ProjectAlreadyExistsException     - if a project is already registed
	 *                                           with the given project name
	 * @throws InadequateClearanceLevelException - if the clearance level of the
	 *                                           project is bigger than the level of
	 *                                           the manager
	 */
	void addProject(String username, String projectType, String projectName, int value, String keywords,
			int confidentialityLevel, String companyName) throws UnknownProjectTypeException,
			ManagerDoesNotExistException, ProjectAlreadyExistsException, InadequateClearanceLevelException;

	/**
	 * Adds members to the project
	 * 
	 * @param username    - name of the manager in charge
	 * @param projectName - name of the project
	 * @param member      - name of the member to add
	 * @throws UserDoesNotExistException         - if no user is registed with the
	 *                                           user name
	 * @throws InsuficientClarenceLevelException - if the member level is lower than
	 *                                           the project clearance level
	 * @throws UserAlreadyAMemberException       - if a member of the project
	 *                                           already as the given member name
	 */
	void addTeamMembers(String username, String projectName, String member)
			throws UserDoesNotExistException, InsuficientClarenceLevelException, UserAlreadyAMemberException;

	/**
	 * Adds a artefact to the project
	 * 
	 * @param username     - name of the project member
	 * @param projectName  - name of the project
	 * @param realDate     - date of the artefact
	 * @param artefactName - name of the artefact
	 * @param level        - confidentiality level
	 * @param description  - description of the artefact aplication
	 * @throws ExceedsClarenceLevelException     - if the project as a level higher
	 *                                           than the artefact level
	 * @throws ArtefactAlreadyInProjectException - if a artefact already is in the
	 *                                           project with the given artefact
	 *                                           name
	 */
	void addArtefacts(String username, String projectName, LocalDate realDate, String artefactName, int level,
			String description) throws ExceedsClarenceLevelException, ArtefactAlreadyInProjectException;

	/**
	 * Adds revision to a artefact
	 * 
	 * @param username     - name of the project member
	 * @param projectName  - name of the project
	 * @param artefactName - name of the artefact
	 * @param date         - date of the revision
	 * @param comment      - description of the revision aplication
	 * @throws UserDoesNotExistsException             - if no user is registed with
	 *                                                the user name
	 * @throws ProjectDoesNotExistException           - if no project is registed
	 *                                                with the given project name
	 * @throws ArtefactDoesNotExistInProjectException - if no artefact with the
	 *                                                given name is registed in the
	 *                                                project
	 * @throws UserIsNotAMemberException              - if no member of the project
	 *                                                as the given member name
	 */
	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment)
			throws UserDoesNotExistsException, ProjectDoesNotExistException, ArtefactDoesNotExistInProjectException,
			UserIsNotAMemberException;

	/**
	 * Throws the artefact "firsts" exception to the main, if anyone succeed
	 * 
	 * @param username    - name of the project member
	 * @param projectName - name of the project
	 * @throws UserDoesNotExistsException    - if no user is registed with the user
	 *                                       name
	 * @throws ProjectDoesNotExistException- if no project is registed with the
	 *                                       given project name
	 * @throws UserIsNotAMemberException     - if no member of the project as the
	 *                                       given member name
	 */
	void artefatcsExceptions(String username, String projectName)
			throws UserDoesNotExistsException, ProjectDoesNotExistException, UserIsNotAMemberException;

	/**
	 * Throws the team "firsts" exception to the main, if anyone succeed
	 * 
	 * @param username    - name of the manager
	 * @param projectName - name of the project
	 * @throws ManagerDoesNotExistException       - if no manager is registed with
	 *                                            the given manager name
	 * @throws ProjectDoesNotExistException       - if no project is registed with
	 *                                            the given project name
	 * @throws ProjectIsNotManagedByUserException - if the given project is no
	 *                                            managed by the given manager
	 */
	void teamExceptions(String username, String projectName)
			throws ManagerDoesNotExistException, ProjectDoesNotExistException, ProjectIsNotManagedByUserException;

	/**
	 * Throws the project "firsts" exception to the main, if anyone succeed
	 * 
	 * @param projectName - name of the project
	 * @throws ProjectDoesNotExistException - if no project is registed with the
	 *                                      given project name
	 * @throws ProjectIsOutsourcedException - if the given project is of the
	 *                                      outsourced type
	 */
	void projectDetailsExceptions(String projectName) throws ProjectDoesNotExistException, ProjectIsOutsourcedException;

	/**
	 * 
	 * @param projectName - name of the project
	 * @return a iterator with the list of the members of a given project by
	 *         insertion order
	 */
	Iterator<User> listProjectMembers(String projectName);

	/**
	 * 
	 * @param projectName - name of the project
	 * @return a iterator with the list of the artefacts of a given project by date
	 */
	Iterator<Artefact> listProjectArtefacts(String projectName);

	/**
	 * 
	 * @param artefact - artefact object
	 * @return a iterator with the list of the revisions of a given artefact by date
	 */
	Iterator<Revision> listArtefactRevision(Artefact artefact);

	/**
	 * 
	 * @param managerName - name of the manager
	 * @return a iterator with the list of the developers of the given manager by
	 *         alphabetic order
	 * @throws ManagerDoesNotExistException - if no manager has the given manager
	 *                                      name
	 */
	Iterator<String> listDevOfMan(String managerName) throws ManagerDoesNotExistException;

	/**
	 * 
	 * @param devName - name of the developer
	 * @return a iterator with all the revisions of the given user by date
	 */
	Iterator<Revision> listAllUserRevisions(String devName);

	/**
	 * 
	 * @param keyword - word of the keyword
	 * @return a iterator with all projects that have the given keyword, first the
	 *         in-house and then the outsourced ones
	 * @throws NoProjectWithKeywordException - if no project has the given keyword
	 */
	Iterator<Project> listProjectsWithKeyword(String keyword) throws NoProjectWithKeywordException;

	/**
	 * 
	 * @param limit1 - lower level limit
	 * @param limit2 - higher level limit
	 * @return a iterator with the projects with level between the given limits by
	 *         alphabetic order
	 * @throws NoProjectsBetweenTheLimits - if no project is between the limits
	 */
	Iterator<Project> listProjectsByConfidentiality(int limit1, int limit2) throws NoProjectsBetweenTheLimits;

	/**
	 * 
	 * @return a iterator with maximum three users with more updates by update
	 *         number
	 * @throws NoWorkaholicsException - if no user has a single update
	 */
	Iterator<User> listWorkaholics() throws NoWorkaholicsException;

	/**
	 * 
	 * @return a iterator with the two users with more common projects by
	 *         maxCommonProjects number
	 * @throws NoCommonProjectsException - if no users have common projects
	 */
	Iterator<String> listCommonUser() throws NoCommonProjectsException;

	/**
	 * 
	 * @return a iterator with all users registed by alphabetic order
	 * @throws NoUserRegistedException - if no user is registed
	 */
	Iterator<User> listAllUsers() throws NoUserRegistedException;

	/**
	 * 
	 * @return iterator with all projects registed by alphabetic order
	 * @throws NoProjectAddedException - if no project is registed
	 */
	Iterator<Project> listAllProjects() throws NoProjectAddedException;

}
