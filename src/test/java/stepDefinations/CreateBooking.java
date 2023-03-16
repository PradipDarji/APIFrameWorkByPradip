package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

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

public class CreateBooking extends Utils{
	
	public String createBookURL;
	public static String bookingID;
	RequestSpecification requestSpec;
	ResponseSpecification responsespec;
	static Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("user has access to create book endpoint {string}")
	public void user_has_access_to_create_book_endpoint(String resource) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI = APIResources.valueOf(resource);
		createBookURL = resourceAPI.getResource();
	}

	@When("user creates book with valid data")
	public void user_creates_book_with_valid_data() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		requestSpec = given().log().all().spec(requestSpecification())
				.body(TestDataBuild.createBookRequestPayload());
	}

	@Then("user should get the response code {int} for createBooking")
	public void user_should_get_the_response_code_for_create_booking(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		response = requestSpec.when().log().all().post(createBookURL);
		assertEquals(response.getStatusCode(), 200);
	}

	@And("user should get the bookingId")
	public void user_should_get_the_booking_id() {
	    // Write code here that turns the phrase above into concrete actions
		bookingID = getJsonPath(response, "bookingid");
		System.out.println("Booking id is: " + bookingID);
	}
}
