package system;

public class DeveloperClass extends UserClass implements Developer {

	private String managerName;

	public DeveloperClass(String jobPosition, String username, String managerName, int level) {
		super(jobPosition, username, level);
		this.managerName = managerName;

	}

	public String getManagerName() {
		return managerName;
	}

}
