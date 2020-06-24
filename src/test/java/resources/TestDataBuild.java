package resources;

import pojo.AddUser;

public class TestDataBuild {

	public AddUser addUserPayLoad(String firstname,String lastname,String subjectId) {
		AddUser a1 = new AddUser();
		a1.setFirstname(firstname);
		a1.setLastname(lastname);
		a1.setSubjectId(subjectId);
		return a1;
	}
	
	public String deleteUserPayLoad(String id) {
		return "{\r\n    \"id\": "+id+"\r\n}";
	}
}
