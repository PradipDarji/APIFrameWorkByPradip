package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class CreateToken extends Utils {

	public String authURL;
	public String authToken;
	public String actulErrorMessage;
	RequestSpecification requestSpec;
	ResponseSpecification responsespec;
	static Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("user has access to endpoint {string}")
	public void user_has_access_to_endpoint(String resource) {
		// Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI = APIResources.valueOf(resource);
		authURL = resourceAPI.getResource();
	}

	@When("user creates a auth token with credential {string} & {string}")
	public void user_creates_a_auth_token_with_credential(String username, String password) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		requestSpec = given().log().all().spec(requestSpecification())
				.body(TestDataBuild.addAuthCredeantials(username, password));
	}

	@Then("user should get the response code {int}")
	public void user_should_get_the_response_code(Integer int1) {

		// Write code here that turns the phrase above into concrete actions
		response = requestSpec.when().log().all().post(authURL);
		assertEquals(response.getStatusCode(), 200);
	}

	@And("user should get the auth token")
	public void user_should_get_the_auth_token() { // Write code here that turns the phrase above into concrete actions
		authToken = getJsonPath(response, "token");
		System.out.println("Auth token is: " + authToken);
	}

	@When("user creates a auth token with invalid credential {string} & {string}")
	public void user_creates_a_auth_token_with_invalid_credential(String username, String password) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		requestSpec = given().log().all().spec(requestSpecification())
				.body(TestDataBuild.addAuthCredeantials(username, password));
	}

	@Then("user should get the error message {string}")
	public void user_should_get_the_error_message(String expectedErrorMessage) {
		// Write code here that turns the phrase above into concrete actions
		actulErrorMessage = getJsonPath(response, "reason");
		assertEquals(actulErrorMessage, expectedErrorMessage);
		
	}

}
