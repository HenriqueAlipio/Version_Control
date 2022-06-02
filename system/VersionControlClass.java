package system;

import java.time.LocalDate;
import java.util.*;
import system.exceptions.*;

public class VersionControlClass implements VersionControl {
	private static final String MANAGER = "manager";
	private static final String DEVELOPER = "developer";
	private static final String IN_HOUSE = "inhouse";
	private static final String OUTSOURCED = "outsourced";

	private SortedMap<String, User> users;
	private Map<String, Project> projects;
	private int maxCommon;

	public VersionControlClass() {
		this.users = new TreeMap<String, User>();
		this.projects = new LinkedHashMap<String, Project>();
		this.maxCommon = 0;
	}

	private boolean isManager(String job) {
		return job.equals(MANAGER);
	}

	private boolean isDeveloper(String job) {
		return job.equals(DEVELOPER);
	}

	private Manager getInfoOfMan(String managerName) {
		return (Manager) users.get(managerName);

	}

	public User getInfoOfUser(String user) {
		return users.get(user);
	}

	public void registUser(String jobPosition, String username, String managerName, int level)
			throws UnknownJobException, UserAlreadyExistsException, ManagerDoesNotExistException {
		if (!isManager(jobPosition) && !isDeveloper(jobPosition)) {
			throw new UnknownJobException();
		}

		if (users.containsKey(username)) {
			throw new UserAlreadyExistsException();
		}
		if (isDeveloper(jobPosition)) {

			if (!users.containsKey(managerName) || (getInfoOfUser(managerName) instanceof Developer)) {
				throw new ManagerDoesNotExistException();
			}

			getInfoOfMan(managerName).addDevToManager(username); // se um desenvolvidar tem manager adiciona o
																	// desenvolvidor ao
			// manager
			Developer newUser = new DeveloperClass(jobPosition, username, managerName, level);
			users.put(username, newUser);
		} else {
			Manager newUser = new ManagerClass(jobPosition, username, level);
			users.put(username, newUser);
		}

	}

	public Iterator<User> listAllUsers() throws NoUserRegistedException {
		if (users.size() == 0) {
			throw new NoUserRegistedException();
		}
		return users.values().iterator();
	}

	private boolean isInHouse(String type) {
		return type.equals(IN_HOUSE);
	}

	private boolean isOutsourced(String type) {
		return type.equals(OUTSOURCED);
	}

	public InHouse getInfoOfProjectInHouse(String projectName) {
		return (InHouse) projects.get(projectName);
	}

	public Project getInfoOfProject(String projectName) {
		return projects.get(projectName);
	}

	public void addProject(String username, String projectType, String projectName, int value, String keywords,
			int confidentialityLevel, String companyName) throws UnknownProjectTypeException,
			ManagerDoesNotExistException, ProjectAlreadyExistsException, InadequateClearanceLevelException {
		if (!isInHouse(projectType) && !isOutsourced(projectType)) {
			throw new UnknownProjectTypeException();
		}
		if (!users.containsKey(username) || (getInfoOfUser(username) instanceof Developer)) {
			throw new ManagerDoesNotExistException();
		}
		if (projects.containsKey(projectName)) {
			throw new ProjectAlreadyExistsException();
		}
		if (getInfoOfUser(username).getLevel() < confidentialityLevel && confidentialityLevel >= 0) {
			throw new InadequateClearanceLevelException();
		}
		if (isInHouse(projectType)) {
			InHouse newProject = new InHouseClass(projectType, username, projectName, value, keywords,
					confidentialityLevel);
			projects.put(projectName, newProject);
			getInfoOfMan(username).addProjectsManager(projectName);
			getInfoOfUser(username).addProject(projectName);
		} else {
			OutSourced newProject = new OutSourcedClass(projectType, username, projectName, value, keywords,
					companyName);
			projects.put(projectName, newProject);
			getInfoOfMan(username).addProjectsManager(projectName);
			getInfoOfUser(username).addProject(projectName);

		}
	}

	public Iterator<Project> listAllProjects() throws NoProjectAddedException {
		if (projects.size() == 0) {
			throw new NoProjectAddedException();
		}
		return projects.values().iterator();
	}

	public void teamExceptions(String username, String projectName)
			throws ManagerDoesNotExistException, ProjectDoesNotExistException, ProjectIsNotManagedByUserException {
		if (!projects.containsKey(projectName) || getInfoOfProject(projectName) instanceof OutSourced) {
			throw new ProjectDoesNotExistException();
		}
		if (!users.containsKey(username) || getInfoOfUser(username) instanceof Developer) {
			throw new ManagerDoesNotExistException();
		}
		if (!getInfoOfProject(projectName).getUsername().equals(username)) {
			throw new ProjectIsNotManagedByUserException();
		}
	}

	public void addTeamMembers(String username, String projectName, String member)
			throws UserDoesNotExistException, InsuficientClarenceLevelException, UserAlreadyAMemberException {
		if (!users.containsKey(member)) {
			throw new UserDoesNotExistException();
		}
		if (getInfoOfProjectInHouse(projectName).isAMember(users.get(member)) || username.equals(member)) {
			throw new UserAlreadyAMemberException();
		}
		if (getInfoOfUser(member).getLevel() < getInfoOfProjectInHouse(projectName).getLevel()) {
			throw new InsuficientClarenceLevelException();
		}
		getInfoOfUser(member).addProjectsDeveloper(projectName);
		getInfoOfUser(member).addProject(projectName);
		getInfoOfProjectInHouse(projectName).addMembers(users.get(member));

	}

	public Artefact getInfoOfArtefact(String projectName, String artefactName) {
		return getInfoOfProjectInHouse(projectName).getInfoOfArtefact(artefactName);
	}

	public void artefatcsExceptions(String username, String projectName)
			throws UserDoesNotExistsException, ProjectDoesNotExistException, UserIsNotAMemberException {
		if (!users.containsKey(username)) {
			throw new UserDoesNotExistsException();
		}
		if (!projects.containsKey(projectName) || getInfoOfProject(projectName) instanceof OutSourced) {
			throw new ProjectDoesNotExistException();
		}
		if (!getInfoOfProjectInHouse(projectName).isAMember(users.get(username))
				&& !getInfoOfProject(projectName).getUsername().equals(username)) {
			throw new UserIsNotAMemberException();
		}
	}

	public void addArtefacts(String username, String projectName, LocalDate realDate, String artefactName, int level,
			String description) throws ExceedsClarenceLevelException, ArtefactAlreadyInProjectException {
		if (getInfoOfProjectInHouse(projectName).hasArtefact(artefactName)) {
			throw new ArtefactAlreadyInProjectException();
		}
		if (getInfoOfProjectInHouse(projectName).getLevel() < level) {
			throw new ExceedsClarenceLevelException();
		}
		Artefact newArtefact = new ArtefactClass(username, artefactName, level, description, realDate);
		getInfoOfProjectInHouse(projectName).addArtefact(newArtefact);
		getInfoOfArtefact(projectName, artefactName).addRevision(username, projectName, artefactName, realDate,
				description);
		getInfoOfUser(username).addRevision(username, projectName, artefactName, realDate, description, 1);
	}

	public void projectDetailsExceptions(String projectName)
			throws ProjectDoesNotExistException, ProjectIsOutsourcedException {
		if (!projects.containsKey(projectName)) {
			throw new ProjectDoesNotExistException();
		}
		if (getInfoOfProject(projectName) instanceof OutSourced) {
			throw new ProjectIsOutsourcedException();
		}

	}

	public Iterator<User> listProjectMembers(String projectName) {
		return getInfoOfProjectInHouse(projectName).listMembers();
	}

	public Iterator<Artefact> listProjectArtefacts(String projectName) {
		return getInfoOfProjectInHouse(projectName).listArtefacts();
	}

	public Iterator<Revision> listArtefactRevision(Artefact artefact) {
		return artefact.listRevisionsByDate();
	}

	public void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment)
			throws UserDoesNotExistsException, ProjectDoesNotExistException, ArtefactDoesNotExistInProjectException,
			UserIsNotAMemberException {
		if (!users.containsKey(username)) {
			throw new UserDoesNotExistsException();
		}
		if (!projects.containsKey(projectName) || getInfoOfProject(projectName) instanceof OutSourced) {
			throw new ProjectDoesNotExistException();
		}
		if (!getInfoOfProjectInHouse(projectName).hasArtefact(artefactName)) {
			throw new ArtefactDoesNotExistInProjectException();
		}
		if (!getInfoOfProjectInHouse(projectName).isAMember(users.get(username))
				&& !projects.get(projectName).getUsername().equals(username)) {
			throw new UserIsNotAMemberException();
		}
		getInfoOfUser(username).addRevision(username, projectName, artefactName, date, comment,
				getInfoOfArtefact(projectName, artefactName).getRevisionsNumberInArtefact() + 1);
		getInfoOfProjectInHouse(projectName).addRevision(username, projectName, artefactName, date, comment);
		getInfoOfArtefact(projectName, artefactName).addRevision(username, projectName, artefactName, date, comment);
	}

	public Iterator<String> listDevOfMan(String managerName) throws ManagerDoesNotExistException {
		if (!users.containsKey(managerName) || getInfoOfUser(managerName) instanceof Developer) {
			throw new ManagerDoesNotExistException();
		}
		return getInfoOfMan(managerName).listDev();
	}

	public Iterator<Revision> listAllUserRevisions(String devName) {
		return getInfoOfUser(devName).listRevisions();
	}

	public Iterator<Project> listProjectsWithKeyword(String keyword) throws NoProjectWithKeywordException {
		Iterator<Project> itAllProjects = projects.values().iterator();

		Set<InHouse> keywordInHouse = new TreeSet<InHouse>(new ComparatorByDateRevisionNumberAndProjectName()); // para
																												// o
																												// inhouse
		SortedSet<Project> keywordOutsourced = new TreeSet<Project>(); // para o outsourced

		while (itAllProjects.hasNext()) {
			Project project = itAllProjects.next();
			if (project.getKeyWords().contains(keyword)) {

				if (project instanceof InHouse) {
					InHouse projectInHouse = (InHouse) project;
					keywordInHouse.add(projectInHouse);
				} else {
					keywordOutsourced.add(project);
				}
			}
		}
		Set<Project> keywordProjects = new LinkedHashSet<Project>();
		keywordProjects.addAll(keywordInHouse);
		keywordProjects.addAll(keywordOutsourced);

		if (keywordProjects.size() == 0) {
			throw new NoProjectWithKeywordException();
		}
		return keywordProjects.iterator();
	}

	public Iterator<Project> listProjectsByConfidentiality(int limit1, int limit2) throws NoProjectsBetweenTheLimits {
		Iterator<Project> itAllProjects = projects.values().iterator(); // nao se pode usar o metodo porque nao tem se
																		// essa exception
		SortedSet<Project> confidentialityProjects = new TreeSet<Project>();
		while (itAllProjects.hasNext()) {
			Project project = itAllProjects.next();
			if (project instanceof InHouse) {
				InHouse inHouse = (InHouse) project;

				if (inHouse.getLevel() >= limit1 && inHouse.getLevel() <= limit2) {
					confidentialityProjects.add(project);
				}
			}
		}
		if (confidentialityProjects.size() == 0) {
			throw new NoProjectsBetweenTheLimits();
		}
		return confidentialityProjects.iterator();
	}

	public Iterator<User> listWorkaholics() throws NoWorkaholicsException {
		SortedSet<User> usersSorted = new TreeSet<User>(new ComparatorByUpdatesDateAndAlphabet());

		usersSorted.addAll(users.values());

		Set<User> workaholics = new LinkedHashSet<User>();
		Iterator<User> itUsers = usersSorted.iterator();
		int counter = 0;
		while (itUsers.hasNext() && counter < 3) {
			User user = itUsers.next();
			workaholics.add(user);
			counter++;
		}
		if (users.size() == 0 || workaholics.iterator().next().getNumUpdates() == 0) {
			throw new NoWorkaholicsException();
		}
		return workaholics.iterator();

	}

	public Iterator<String> listCommonUser() throws NoCommonProjectsException {
		int common = 0;
		Set<SortedSet<String>> commonUsers = new HashSet<SortedSet<String>>();
		SortedSet<String> lista = new TreeSet<String>();
		SortedSet<String> lista2 = new TreeSet<String>();
		ArrayList<User> usersInfo = new ArrayList<User>();
		usersInfo.addAll(users.values());
		for (int i = 0; i < usersInfo.size(); i++) {
			User user1 = usersInfo.get(i);
			for (int j = i + 1; j < usersInfo.size(); j++) {
				User user2 = usersInfo.get(j);
				common = user1.getCommonProjects(user2);
				if (common > maxCommon) {
					maxCommon = common;
					commonUsers.clear();
					lista.clear();
					lista.add(user1.getUserName());
					lista.add(user2.getUserName());
					commonUsers.add(lista);
				}
				/*if (common == maxCommon && common != 0) {
					lista2.add(user1.getUserName());
					lista2.add(user2.getUserName());
					commonUsers.add(lista2);
					lista.clear();
					lista2.clear();
					lista=commonUsers.iterator().next();
				}*/
			}
		}

		if (lista.size() == 0) {
			throw new NoCommonProjectsException();
		}
		return lista.iterator();
	}

	public int maxNumberCommon() {
		return maxCommon;
	}

}
