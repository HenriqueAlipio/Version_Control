package system;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class InHouseClass extends ProjectClass implements InHouse {
	public int confidentialityLevel;
	private Set<User> members;
	private Map<String, Artefact> artefacts;
	private Set<Revision> revisions;

	public InHouseClass(String projectType, String username, String projectName, int value, String keywords,
			int confidentialityLevel) {
		super(projectType, username, projectName, value, keywords);
		this.confidentialityLevel = confidentialityLevel;
		members = new LinkedHashSet<User>();
		artefacts = new LinkedHashMap<String, Artefact>();
		revisions = new LinkedHashSet<Revision>();
	}

	public int getLevel() {
		return confidentialityLevel;
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

	public boolean hasArtefact(String artefactName) {
		return artefacts.containsKey(artefactName);
	}

	public Artefact getInfoOfArtefact(String artefactName) {
		return artefacts.get(artefactName);
	}

	public void addArtefact(Artefact artefact) {
		artefacts.put(artefact.getArtefactName(), artefact);
	}

	public LocalDate getLastRevision() { // o map esta organizado com o mais recente primeiro
		return listArtefacts().next().getNewestRevisionDate();

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
}
