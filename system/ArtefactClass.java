package system;

import java.time.LocalDate;
import java.util.*;

public class ArtefactClass implements Artefact {
	private String artefactName, description, username;
	private int level;
	private LocalDate date, olderRevisionDate;
	private SortedSet<Revision> revisions;

	public ArtefactClass(String username, String artefactName, int level, String description, LocalDate realDate) {
		this.username = username;
		this.artefactName = artefactName;
		this.level = level;
		this.description = description;
		this.date = realDate;
		revisions = new TreeSet<Revision>(new ComparatorByRevisionNumber());
		olderRevisionDate = date;

	}

	public String getUsername() {
		return username;
	}

	public String getArtefactName() {
		return artefactName;
	}

	public int getLevel() {
		return level;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalDate getNewestRevisionDate() {
		return olderRevisionDate;
	}

	public int compareTo(Artefact o) {
		return this.getArtefactName().compareTo(o.getArtefactName());
	}

	public int getRevisionsNumberInArtefact() {
		return revisions.size();
	}

	public void addRevision(String username, String projectName, String artefactName, LocalDate date, String comment) {
		Revision newRevision = new RevisionClass(username,projectName, artefactName, date, comment, revisions.size()+1);
		if (date.isAfter(olderRevisionDate)) {
			olderRevisionDate = date;
		}
		revisions.add(newRevision);

	}

	public Iterator<Revision> listRevisionsByDate() {
		return revisions.iterator();
	}

}
