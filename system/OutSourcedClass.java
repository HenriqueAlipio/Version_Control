package system;

public class OutSourcedClass extends ProjectClass implements OutSourced {
	private String companyName;

	public OutSourcedClass(String projectType, String username, String projectName, int value, String keywords,
			String companyName) {
		super(projectType, username, projectName, value, keywords);
		this.companyName = companyName;
	}
	public String getCompanyName() {
		return companyName;
	}
}
