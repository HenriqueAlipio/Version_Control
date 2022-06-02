import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import system.*;
import system.exceptions.*;

public class Main {
	private static final String UNKNOWNCOM = "Unknown command. Type help to see available commands.";

	private static final String BYE = "Bye!";

	private static final String HELP_INFO = "Available commands:%n";
	private static final String HELP_1 = "register - adds a new user%n";
	private static final String HELP_2 = "users - lists all registered users%n";
	private static final String HELP_3 = "create - creates a new project%n";
	private static final String HELP_4 = "projects - lists all projects%n";
	private static final String HELP_5 = "team - adds team members to a project%n";
	private static final String HELP_6 = "artefacts - adds artefacts to a project%n";
	private static final String HELP_7 = "project - shows detailed project information%n";
	private static final String HELP_8 = "revision - revises an artefact%n";
	private static final String HELP_9 = "manages - lists developers of a manager%n";
	private static final String HELP_10 = "keyword - filters projects by keyword%n";
	private static final String HELP_11 = "confidentiality - filters projects by confidentiality level%n";
	private static final String HELP_12 = "workaholics - top 3 employees with more artefacts updates%n";
	private static final String HELP_13 = "common - employees with more projects in common%n";
	private static final String HELP_14 = "help - shows the available commands%n";
	private static final String HELP_15 = "exit - terminates the execution of the program%n";

	private static final String USER_INFO = "User ";
	private static final String REGIST_AS = " was registered as ";
	private static final String LEVEL_REIST = " with clearance level ";
	private static final String UNK_JOB_POSITION = "Unknown job position.";
	private static final String EXISTS = " already exists.";
	private static final String MANAGER_INFO1 = "Project manager ";
	private static final String NOT_EXISTS = " does not exist.";

	private static final String NO_USERS_REGIST = "No users registered.";
	private static final String LIST_USERS = "All registered users:";
	private static final String MANAGER_INFO2 = "manager";
	private static final String DEVELOPER_INFO = "developer";
	private static final String MANAGED_BY1 = " is managed by ";

	private static final String PROJECT_CREATED = " project was created.";
	private static final String UNK_PROJECT = "Unknown project type.";
	private static final String PROJECT_EXISTS = " project already exists.";
	private static final String HAS_LEVEL = " has clearance level ";

	private static final String NO_PROJECTS_ADD = "No projects added.";
	private static final String LIST_PROJECTS = "All projects:";
	private static final String IN_HOUSE_INFO1 = "inhouse";
	private static final String IN_HOUSE_INFO2 = "in-house ";
	private static final String OUTSOURCED_INFO = "outsourced";
	private static final String DEV_BY = " and developed by ";

	private static final String LIST_TEAM = "Latest team members:";
	private static final String ADDED_TEAM = ": added to the team.";
	private static final String ALREADY_IN_TEAM = ": already a member.";
	private static final String NO_LEVEL_FOR_TEAM = ": insufficient clearance level.";
	private static final String USER_NOT_EXIST = ": does not exist.";
	private static final String PROJECT_DOES_NOT_EXIST = " project does not exist.";

	private static final String LIST_ARTEFACTS = "Latest project artefacts:";
	private static final String EXCEEDS_LEVEL = ": exceeds project confidentiality level.";
	private static final String ALREADY_IN_PROJECT = ": already in the project.";
	private static final String ADDED_TO_PROJECT = ": added to the project.";
	private static final String IS_NOT_IN_TEAM = " does not belong to the team of ";

	private static final String MANAGED_BY2 = " managed by";
	private static final String OUTSOURCED_PROJECT = " is an outsourced project.";

	private static final String REVISION_INFO1 = "Revision ";
	private static final String REVISION_INFO2 = "revision ";
	private static final String OF_ARTEFACT = " of artefact ";
	private static final String SUBMITEED = " was submitted.";
	private static final String DOES_NOT_EXIST_IN_PROJECT = " does not exist in the project.";

	private static final String NO_PROJECT_WITH_KEYWORD = "No projects with keyword ";
	private static final String LIST_KEYWORD_PROJECTS = "All projects with keyword ";

	private static final String NO_PROJECTS_WITHOUT_LEVEL = "No projects within levels ";
	private static final String AND = " and ";
	private static final String ALL_PROJECT_WITH_LEVEL = "All projects within levels ";
	private static final String HAS_KEYWORDS = " and has keywords ";

	private static final String MANAGER_INFO3 = "Manager ";
	private static final String BREAK = " ";
	private static final String DOT = ".";
	private static final String COMMA = ", ";
	private static final String TWO_POINTS = ":";
	private static final String SQUARE_BRACKETS1 = " [";
	private static final String SQUARE_BRACKETS2 = "]";
	private static final String WRITE_DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * Enumerado que define os comandos do utilizador
	 */
	private enum Command {
		UNKNOWN, EXIT, HELP, REGISTER, USERS, CREATE, PROJECTS, TEAM, ARTEFACTS, PROJECT, REVISION, MANAGES, KEYWORD,
		CONFIDENTIALITY, WORKAHOLICS, COMMON

	}

	public static void main(String[] args) {
		Main.interpreter();

	}

	private static Command getCommand(Scanner input) {
		try {
			return Command.valueOf(input.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}

	private static void interpreter() {
		VersionControl vc = new VersionControlClass();
		Scanner input = new Scanner(System.in);
		Command c = getCommand(input);
		while (!c.equals(Command.EXIT)) {
			switch (c) {
			case UNKNOWN:
				System.out.println(UNKNOWNCOM);
				break;
			case HELP:
				helpCommand();
				break;
			case REGISTER:
				registerCommand(input, vc);
				break;
			case USERS:
				listUsers(vc);
				break;
			case CREATE:
				createProject(input, vc);
				break;
			case PROJECTS:
				listProjects(vc);
				break;
			case TEAM:
				addTeam(input, vc);
				break;
			case ARTEFACTS:
				addArtefacts(input, vc);
				break;
			case PROJECT:
				listProjectDetails(input, vc);
				break;
			case REVISION:
				addRevision(input, vc);
				break;
			case MANAGES:
				listDevInfo(input, vc);
				break;
			case KEYWORD:
				listKeyword(input, vc);
				break;
			case CONFIDENTIALITY:
				listByConfidentiality(input, vc);
				break;
			case WORKAHOLICS:
				listWorkaholics(vc);
				break;
			case COMMON:
				listCommon(vc);
				break;
			default:
				break;
			}
			c = getCommand(input);
		}
		System.out.println(BYE);
		input.close();
	}

	private static void helpCommand() {
		System.out.printf(HELP_INFO + HELP_1 + HELP_2 + HELP_3 + HELP_4 + HELP_5 + HELP_6 + HELP_7 + HELP_8 + HELP_9
				+ HELP_10 + HELP_11 + HELP_12 + HELP_13 + HELP_14 + HELP_15);

	}

	private static void registerCommand(Scanner input, VersionControl vc) {
		String jobPosition = input.next();
		String username = input.next();
		String managerName = null;
		if (jobPosition.equals(DEVELOPER_INFO)) {
			managerName = input.next();
		}
		int level = input.nextInt();

		try {
			vc.registUser(jobPosition, username, managerName, level);
			System.out.println(USER_INFO + username + REGIST_AS + jobPosition + LEVEL_REIST + level + DOT);
		} catch (UserAlreadyExistsException e) {
			System.out.println(USER_INFO + username + EXISTS);
		} catch (ManagerDoesNotExistException e) {
			System.out.println(MANAGER_INFO1 + managerName + NOT_EXISTS);
		} catch (UnknownJobException e) {
			System.out.println(UNK_JOB_POSITION);
		}
	}

	private static void listUsers(VersionControl vc) {
		try {
			Iterator<User> it = vc.listAllUsers();
			System.out.println(LIST_USERS);
			while (it.hasNext()) {
				User userInfo = (User) it.next();

				if (userInfo instanceof Manager) {
					Manager userInfo2 = (Manager) userInfo;
					System.out.println(MANAGER_INFO2 + BREAK + userInfo.getUserName() + SQUARE_BRACKETS1
							+ userInfo2.getNumDev() + COMMA + userInfo2.getNumProjMan() + COMMA
							+ userInfo.getNumProjDev() + SQUARE_BRACKETS2);
				} else {
					Developer userInfo3 = (Developer) userInfo;
					System.out.println(
							DEVELOPER_INFO + BREAK + userInfo.getUserName() + MANAGED_BY1 + userInfo3.getManagerName()
									+ SQUARE_BRACKETS1 + userInfo.getNumProjDev() + SQUARE_BRACKETS2);
				}

			}
		} catch (NoUserRegistedException e) {
			System.out.println(NO_USERS_REGIST);
		}

	}

	private static void createProject(Scanner input, VersionControl vc) {
		String username = input.next();
		String projectType = input.next();
		String projectName = input.nextLine().trim();
		int value = input.nextInt();
		String keywords = input.nextLine().trim();
		int confidentialityLevel = -1;
		String companyName = null;
		if (projectType.equals(IN_HOUSE_INFO1)) {
			confidentialityLevel = input.nextInt();
		} else {
			companyName = input.nextLine();
		}
		try {
			vc.addProject(username, projectType, projectName, value, keywords, confidentialityLevel, companyName);
			System.out.println(projectName + PROJECT_CREATED);
		} catch (UnknownProjectTypeException e) {
			System.out.println(UNK_PROJECT);
		} catch (ManagerDoesNotExistException e) {
			System.out.println(MANAGER_INFO1 + username + NOT_EXISTS);
		} catch (ProjectAlreadyExistsException e) {
			System.out.println(projectName + PROJECT_EXISTS);
		} catch (InadequateClearanceLevelException e) {
			System.out.println(MANAGER_INFO1 + username + HAS_LEVEL + vc.getInfoOfUser(username).getLevel() + DOT);
		}
	}

	private static void listProjects(VersionControl vc) {
		try {
			Iterator<Project> it = vc.listAllProjects();
			System.out.println(LIST_PROJECTS);
			while (it.hasNext()) {
				Project projectInfo = it.next();

				if (projectInfo instanceof InHouse) {
					InHouse inHouseInfo = (InHouse) projectInfo;
					System.out.println(IN_HOUSE_INFO2 + projectInfo.getProjectName() + MANAGED_BY1
							+ projectInfo.getUsername() + SQUARE_BRACKETS1 + inHouseInfo.getLevel() + COMMA
							+ inHouseInfo.getNrMembers() + COMMA + inHouseInfo.getNrArtefacts() + COMMA
							+ inHouseInfo.getNrRevisions() + SQUARE_BRACKETS2);

				} else {
					OutSourced outsourcedInfo = (OutSourced) projectInfo;
					System.out.println(OUTSOURCED_INFO + BREAK + projectInfo.getProjectName() + MANAGED_BY1
							+ projectInfo.getUsername() + DEV_BY + outsourcedInfo.getCompanyName());

				}
			}

		} catch (NoProjectAddedException e) {
			System.out.println(NO_PROJECTS_ADD);
		}
	}

	private static void addTeam(Scanner input, VersionControl vc) {
		ArrayList<String> members = new ArrayList<String>();
		String username = input.next();
		String projectName = input.nextLine().trim();
		int value = input.nextInt();
		for (int i = 0; i < value; i++) {
			String memberName = input.next();
			members.add(memberName);
		}

		try {
			Iterator<String> it = members.iterator();
			if (it.hasNext()) {
				vc.teamExceptions(username, projectName);
				System.out.println(LIST_TEAM);
				while (it.hasNext()) {
					String member = it.next();
					try {
						vc.addTeamMembers(username, projectName, member);
						System.out.println(member + ADDED_TEAM);
					} catch (UserDoesNotExistException e) {
						System.out.println(member + USER_NOT_EXIST);
					} catch (InsuficientClarenceLevelException e) {
						System.out.println(member + NO_LEVEL_FOR_TEAM);
					} catch (UserAlreadyAMemberException e) {
						System.out.println(member + ALREADY_IN_TEAM);
					}
				}
			}
		} catch (ManagerDoesNotExistException e) {
			System.out.println(MANAGER_INFO1 + username + NOT_EXISTS);
		} catch (ProjectDoesNotExistException e) {
			System.out.println(projectName + PROJECT_DOES_NOT_EXIST);
		} catch (ProjectIsNotManagedByUserException e) {
			System.out.println(projectName + MANAGED_BY1 + vc.getInfoOfProject(projectName).getUsername() + DOT);
		}
	}

	private static void addArtefacts(Scanner input, VersionControl vc) {
		ArrayList<String> artefactsNames = new ArrayList<String>();
		ArrayList<Integer> artefactsLevels = new ArrayList<Integer>();
		ArrayList<String> artefactsDescription = new ArrayList<String>();
		String username = input.next();
		String projectName = input.nextLine().trim();
		String date = input.nextLine().trim();
		int value = input.nextInt();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WRITE_DATE_FORMAT); // passar para LocalDate
		LocalDate realDate = LocalDate.parse(date, formatter);

		for (int i = 0; i < value; i++) { // ciclo for para colocar em vetores os argumentos dos artefacts
			String artefactName = input.next();
			int level = input.nextInt();
			String description = input.nextLine().trim();

			artefactsNames.add(artefactName);
			artefactsLevels.add(level);
			artefactsDescription.add(description);
		}
		try {
			Iterator<String> itNames = artefactsNames.iterator();
			Iterator<Integer> itLevels = artefactsLevels.iterator();
			Iterator<String> itDescription = artefactsDescription.iterator();
			if (itNames.hasNext()) {
				vc.artefatcsExceptions(username, projectName);
				System.out.println(LIST_ARTEFACTS);
				while (itNames.hasNext()) {
					String artefactName = itNames.next();
					int level = itLevels.next();
					String description = itDescription.next();
					try {
						vc.addArtefacts(username, projectName, realDate, artefactName, level, description);
						System.out.println(artefactName + ADDED_TO_PROJECT);
					} catch (ExceedsClarenceLevelException e) {
						System.out.println(artefactName + EXCEEDS_LEVEL);
					} catch (ArtefactAlreadyInProjectException e) {
						System.out.println(artefactName + ALREADY_IN_PROJECT);
					}
				}
			}

		} catch (UserDoesNotExistsException e) {
			System.out.println(USER_INFO + username + NOT_EXISTS);
		} catch (ProjectDoesNotExistException e) {
			System.out.println(projectName + PROJECT_DOES_NOT_EXIST);
		} catch (UserIsNotAMemberException e) {
			System.out.println(USER_INFO + username + IS_NOT_IN_TEAM + projectName + DOT);
		}
	}

	private static void listProjectDetails(Scanner input, VersionControl vc) {
		String projectName = input.nextLine().trim();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WRITE_DATE_FORMAT);
		try {
			vc.projectDetailsExceptions(projectName); // ou no metodo listProjectMembers
			System.out.println(projectName + SQUARE_BRACKETS1 + vc.getInfoOfProjectInHouse(projectName).getLevel()
					+ SQUARE_BRACKETS2 + MANAGED_BY2 + BREAK + vc.getInfoOfProject(projectName).getUsername()
					+ SQUARE_BRACKETS1 + vc.getInfoOfUser(vc.getInfoOfProject(projectName).getUsername()).getLevel()
					+ SQUARE_BRACKETS2 + TWO_POINTS);

			Iterator<User> itMembers = vc.listProjectMembers(projectName);
			while (itMembers.hasNext()) {
				User user = itMembers.next();
				System.out.println(user.getUserName() + SQUARE_BRACKETS1 + user.getLevel() + SQUARE_BRACKETS2);
			}

			Iterator<Artefact> itArtefact = vc.listProjectArtefacts(projectName);
			while (itArtefact.hasNext()) {
				Artefact artefact = itArtefact.next();
				System.out.println(
						artefact.getArtefactName() + SQUARE_BRACKETS1 + artefact.getLevel() + SQUARE_BRACKETS2);

				Iterator<Revision> itRevision = vc.listArtefactRevision(artefact);
				while (itRevision.hasNext()) {
					Revision revision = itRevision.next();
					System.out.println(REVISION_INFO2 + revision.getRevisionNumber() + BREAK + revision.getUsername()
							+ BREAK + formatter.format(revision.getDate()) + BREAK + revision.getComment());
				}
			}

		} catch (ProjectDoesNotExistException e) {
			System.out.println(projectName + PROJECT_DOES_NOT_EXIST);
		} catch (ProjectIsOutsourcedException e) {
			System.out.println(projectName + OUTSOURCED_PROJECT);
		}
	}

	private static void addRevision(Scanner input, VersionControl vc) {
		String username = input.next();
		String projectName = input.nextLine().trim();
		String artefactName = input.next();
		String date = input.next();
		String comment = input.nextLine().trim();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WRITE_DATE_FORMAT); // passar para LocalDate
		LocalDate realDate = LocalDate.parse(date, formatter);

		try {
			vc.addRevision(username, projectName, artefactName, realDate, comment);
			System.out.println(
					REVISION_INFO1 + vc.getInfoOfArtefact(projectName, artefactName).getRevisionsNumberInArtefact()
							+ OF_ARTEFACT + artefactName + SUBMITEED);

		} catch (UserDoesNotExistsException e) {
			System.out.println(USER_INFO + username + NOT_EXISTS);
		} catch (ProjectDoesNotExistException e) {
			System.out.println(projectName + PROJECT_DOES_NOT_EXIST);
		} catch (ArtefactDoesNotExistInProjectException e) {
			System.out.println(artefactName + DOES_NOT_EXIST_IN_PROJECT);
		} catch (UserIsNotAMemberException e) {
			System.out.println(USER_INFO + username + IS_NOT_IN_TEAM + projectName + DOT);
		}
	}

	private static void listDevInfo(Scanner input, VersionControl vc) {
		String managerName = input.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WRITE_DATE_FORMAT);
		try {
			Iterator<String> itDevNames = vc.listDevOfMan(managerName); // ou fazer um metodo auxiliar
			System.out.println(MANAGER_INFO3 + managerName + TWO_POINTS);
			while (itDevNames.hasNext()) {
				String devName = itDevNames.next();
				System.out.println(devName);
				Iterator<Revision> itRevisions = vc.listAllUserRevisions(devName);
				while (itRevisions.hasNext()) {
					Revision revision = itRevisions.next();
					System.out.println(revision.getProjectName() + COMMA + revision.getArtefactName() + COMMA
							+ REVISION_INFO2 + revision.getRevisionNumber() + COMMA
							+ formatter.format(revision.getDate()) + COMMA + revision.getComment());
				}
			}

		} catch (ManagerDoesNotExistException e) {
			System.out.println(MANAGER_INFO1 + managerName + NOT_EXISTS);
		}
	}

	private static void listKeyword(Scanner input, VersionControl vc) {
		String keyword = input.nextLine().trim();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WRITE_DATE_FORMAT);
		try {
			Iterator<Project> itProjects = vc.listProjectsWithKeyword(keyword);
			System.out.println(LIST_KEYWORD_PROJECTS + keyword + TWO_POINTS);
			while (itProjects.hasNext()) {
				Project project = itProjects.next();
				if (project instanceof InHouse) {
					InHouse inHouse = (InHouse) project;
					System.out.println(IN_HOUSE_INFO2 + project.getProjectName() + MANAGED_BY1 + project.getUsername()
							+ SQUARE_BRACKETS1 + inHouse.getLevel() + COMMA + inHouse.getNrMembers() + COMMA
							+ inHouse.getNrArtefacts() + COMMA + inHouse.getNrRevisions() + COMMA
							+ formatter.format(inHouse.getLastRevision()) + SQUARE_BRACKETS2);
				} else {
					OutSourced outSourced=(OutSourced) project;
					System.out.println(OUTSOURCED_INFO + BREAK + project.getProjectName() + MANAGED_BY1
							+ project.getUsername() + DEV_BY + outSourced.getCompanyName());
				}
			}
		} catch (NoProjectWithKeywordException e) {
			System.out.println(NO_PROJECT_WITH_KEYWORD + keyword + DOT);
		}
	}

	private static void listByConfidentiality(Scanner input, VersionControl vc) {
		int limit1 = input.nextInt();
		int limit2 = input.nextInt();
		int lowerLimit = -1;
		int upperLimit = -1;
		if (limit1 <= limit2) {
			lowerLimit = limit1;
			upperLimit = limit2;
		} else {
			lowerLimit = limit2;
			upperLimit = limit1;
		}
		try {
			Iterator<Project> itProjects = vc.listProjectsByConfidentiality(lowerLimit, upperLimit);

			System.out.println(ALL_PROJECT_WITH_LEVEL + lowerLimit + AND + upperLimit + TWO_POINTS);
			while (itProjects.hasNext()) {
				Project project = itProjects.next();
				String[] itKeywords = project.getKeyWords().split(BREAK);
				String listOfKeywords = null;
				boolean firstKeyword = true;
				for (String keyword : itKeywords) {
					if (firstKeyword) {
						listOfKeywords = keyword;
						firstKeyword = false;
					} else {
						listOfKeywords = listOfKeywords.concat(", ");
						listOfKeywords = listOfKeywords.concat(keyword);

					}
				}
				System.out.println(project.getProjectName() + MANAGED_BY1 + project.getUsername() + HAS_KEYWORDS
						+ listOfKeywords + DOT);
			}

		} catch (

		NoProjectsBetweenTheLimits e) {
			System.out.println(NO_PROJECTS_WITHOUT_LEVEL + lowerLimit + AND + upperLimit + DOT);
		}
	}

	private static void listWorkaholics(VersionControl vc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(WRITE_DATE_FORMAT);
		try {
			Iterator<User> itUser = vc.listWorkaholics();
			while (itUser.hasNext()) {
				User user = itUser.next();
				System.out.println(user.getUserName() + TWO_POINTS + BREAK + user.getNumUpdates() + " updates, "
						+ user.getNumProjDev() + " projects, " + "last update on "
						+ formatter.format(user.getLastUpdateDate()));
			}
		} catch (NoWorkaholicsException e) {
			System.out.println("There are no workaholics.");
		}
	}

	private static void listCommon(VersionControl vc) {
		try {
			Iterator<String> itCommon = vc.listCommonUser();
			String user1=itCommon.next();
			String user2=itCommon.next();
			System.out.println(user1 +" "+ user2+ " have " + vc.maxNumberCommon()+" projects in common.");
		} catch (NoCommonProjectsException e) {
			System.out.println("Cannot determine employees with common projects.");
		}
	}
}
