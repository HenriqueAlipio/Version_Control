package system;

public class ProjectClass implements Project {
	public String username, type, projectName, keywords;
	public int value;

	public ProjectClass(String projectType, String username, String projectName, int value, String keywords) {
		this.username = username;
		this.type = projectType;
		this.projectName = projectName;
		this.value = value;
		this.keywords = keywords;
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

	/**
	 * Returns the compared ProjectNames of two projects different.
	 */
	public int compareTo(Project o) {
		return this.getProjectName().compareTo(o.getProjectName());
	}

}
