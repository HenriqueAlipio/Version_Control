package system;

import java.time.LocalDate;

public class RevisionClass implements Revision {
	private String username,projectName,artefactName, comment;
	private int revisionNumber;
	private LocalDate date;

	public RevisionClass(String username, String projectName, String artefactName, LocalDate date, String comment, int revisionNumber) {
		this.username = username;
		this.projectName=projectName;
		this.artefactName=artefactName;
		this.comment = comment;
		this.revisionNumber = revisionNumber;
		this.date = date;
	}

	public RevisionClass(String username, String projectName, String artefactName, LocalDate date, String comment) {//construtor para a classe projectClass
		this.username = username;
		this.projectName=projectName;
		this.artefactName=artefactName;
		this.comment = comment;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getArtefactName() {
		return artefactName;
	}
	public String getComment() {
		return comment;
	}

	public int getRevisionNumber() {
		return revisionNumber;
	}

	public LocalDate getDate() {
		return date;
	}

	/**
     * Returns the compared RevisionNames of two different revisions.
     */
    public int compareTo(Revision r) {
        int comparacao = this.getDate().compareTo(r.getDate());
        if (comparacao == 0) {
            return this.getUsername().compareTo(r.getUsername());

        }
        return comparacao;
    }

}
