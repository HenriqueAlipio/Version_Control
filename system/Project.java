package system;

public interface Project extends Comparable<Project> {
	String getUsername();

	String getType();

	String getProjectName();

	int getValue();

	String getKeyWords();

}
