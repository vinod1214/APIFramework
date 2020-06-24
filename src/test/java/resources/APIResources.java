package resources;

public enum APIResources {

	AddUserAPI("/users"),
	GetUserAPI("/users"),
	DeleteUserAPI("/users/5");

	private String resource;
	 APIResources(String resource) {
		 this.resource = resource;

	}
	 
	 public String getResource() {
		 return resource;
	 }
	
}
