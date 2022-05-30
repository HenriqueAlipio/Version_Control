package system;

import java.time.LocalDate;
import java.util.Iterator;

import system.exceptions.*;

public interface VersionControl {

	void registUser(String jobPosition, String username, String managerName, int level)
			throws UnknownJobException, UserAlreadyExistsException, ManagerDoesNotExistException;

	void addProject(String username, String projectType, String projectName, int value, String keywords,
			int confidentialityLevel, String companyName) throws UnknownProjectTypeException,
			ManagerDoesNotExistException, ProjectAlreadyExistsException, InadequateClearanceLevelException;

	User getInfoOfUser(String user);

	Project getInfoOfProject(String projectName);

	Iterator<User> listAllUsers() throws NoUserRegistedException;

	Iterator<Project> listAllProjects() throws NoProjectAddedException;

	void teamExceptions(String username, String projectName)
			throws ManagerDoesNotExistException, ProjectDoesNotExistException, ProjectIsNotManagedByUserException;

	void addTeamMembers(String username, String projectName, String member)
			throws UserDoesNotExistException, InsuficientClarenceLevelException, UserAlreadyAMemberException;

	Artefact getInfoOfArtefact(String projectName, String username);

	void artefatcsExceptions(String username, String projectName)
			throws UserDoesNotExistsException, ProjectDoesNotExistException, UserIsNotAMemberException;

	void addArtefacts(String username, String projectName, LocalDate realDate, String artefactName, int level,
			String description) throws ExceedsClarenceLevelException, ArtefactAlreadyInProjectException;

	void projectDetailsExceptions(String projectName) throws ProjectDoesNotExistException, ProjectIsOutsourcedException;

	Iterator<User> listProjectMembers(String projectName);

	Iterator<Artefact> listProjectArtefacts(String projectName);

	Iterator<Revision> listArtefactRevision(Artefact artefact);

	void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment)
			throws UserDoesNotExistsException, ProjectDoesNotExistException, ArtefactDoesNotExistInProjectException,
			UserIsNotAMemberException;

	Iterator<String> listDevOfMan(String managerName) throws ManagerDoesNotExistException;

	Iterator<Revision> listAllUserRevisions(String devName);

	Iterator<Project> listProjectsWithKeyword(String keyword) throws NoProjectWithKeywordException;

}
