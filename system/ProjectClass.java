package system;

import java.time.LocalDate;
import java.util.*;

public class ProjectClass implements Project {
	public String username, type, projectName, keywords, companyName;
	public int value, confidentialityLevel;
	private Set<User> members;
	private Map<String, Artefact> artefacts;
	private Set<Revision> revisions;

	public ProjectClass(String projectType, String username, String projectName, int value, String keywords,
			int confidentialityLevel, String companyName) {
		this.username = username;
		this.type = projectType;
		this.projectName = projectName;
		this.value = value;
		this.keywords = keywords;
		this.confidentialityLevel = confidentialityLevel;
		this.companyName = companyName;
		members = new LinkedHashSet<User>();
		artefacts = new LinkedHashMap<String, Artefact>();
		revisions = new LinkedHashSet<Revision>();
	}

	public String getUsername() {
		return username;
	}

	public String getType() {
		return type;
	}

	public String getProjectName() {
		return projectName;
	}

	public int getValue() {
		return value;
	}

	public String getKeyWords() {
		return keywords;
	}

	public int getLevel() {
		return confidentialityLevel;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void addMembers(User member) {
		members.add(member);
	}

	public boolean isAMember(User member) {
		return members.contains(member);
	}

	public int getNrMembers() {
		return members.size();
	}

	public int getNrArtefacts() {
		return artefacts.size();
	}

	public int getNrRevisions() {
		return revisions.size();
	}

	public LocalDate getLastRevision() { // o map esta organizado com o mais recente primeiro
		return listArtefacts().next().getNewestRevisionDate();

	}

	public boolean hasArtefact(String artefactName) {
		return artefacts.containsKey(artefactName);
	}

	public Artefact getInfoOfArtefact(String artefactName) {
		return artefacts.get(artefactName);
	}

	public void addArtefact(Artefact artefact) {
		artefacts.put(artefact.getArtefactName(), artefact);
	}

	public Iterator<User> listMembers() {
		return members.iterator();
	}

	public Iterator<Artefact> listArtefacts() {
		SortedSet<Artefact> allArtefacts = new TreeSet<Artefact>(new ComparatorByArtefactOlderDate());
		allArtefacts.addAll(artefacts.values());
		return allArtefacts.iterator();
	}

	public void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment) {
		Revision newRevision = new RevisionClass(username, projectName, artefactName, date, comment);
		revisions.add(newRevision);

	}

	public int compareTo(Project o) {
		return this.getProjectName().compareTo(o.getProjectName());
	}

}
