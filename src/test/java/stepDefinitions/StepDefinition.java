package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.IOException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	
	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	static String user_id;
	TestDataBuild data = new TestDataBuild();
	@Given("Add User Payload with {string} {string} {string}")
	public void add_User_Payload_with(String firstname, String lastname, String subjectId) throws IOException {
	
		res = given().spec(requestSpecification())
			.body(data.addUserPayLoad(firstname,lastname,subjectId));
		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource,String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resSpec = new ResponseSpecBuilder().expectStatusCode(201)
				.expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());
		}else if(method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());
		}else if(method.equalsIgnoreCase("DELETE")) {
			response = res.when().delete(resourceAPI.getResource());
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(), 201);
	}
	
	@Then("verify user id created maps to {string} using {string}")
	public void verify_user_id_created_maps_to_using(String expectedName,String resource ) throws IOException {
		user_id = getJsonPath(response, "id");
		res = given().spec(requestSpecification()).queryParam("id", user_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "firstname");
		System.out.println(actualName);
		//assertEquals(actualName, expectedName);
	}
	
	@Given("Delete User Payload")
	public void delete_User_Payload() throws IOException {
		res = given().spec(requestSpecification());
	}
	
	@Then("the API call get success with status code {int}")
	public void the_API_call_get_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);
	}
}
