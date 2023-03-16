package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import Pojo.BookingDetailsDTO;
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

public class GetBookDetailByID extends Utils {

	public String getBookDetailById;
	RequestSpecification requestSpec;
	ResponseSpecification responsespec;
	static Response response;
	public static int invalidBookingID = 123456789;
	public BookingDetailsDTO bookingDetails = new BookingDetailsDTO();

	@Given("user has access to get booking details by Booking Id endpoint {string}")
	public void user_has_access_to_get_booking_details_by_booking_id_endpoint(String resource) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI = APIResources.valueOf(resource);
		getBookDetailById = resourceAPI.getResource();

		requestSpec = given().log().all().spec(requestSpecification());
	}

	@When("user pass the valid booking Id as a path parameter")
	public void user_pass_the_valid_booking_id_as_a_path_parameter() {
		// Write code here that turns the phrase above into concrete actions
		response = requestSpec.when().log().all().get(getBookDetailById + CreateBooking.bookingID);
	}

	@Then("user should get the response code {int} for GetBookingDetailsById API")
	public void user_should_get_the_response_code_for_get_booking_details_by_id_api(Integer code) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("user verify the response body data")
	public void user_verify_the_response_body_data() {
		// Write code here that turns the phrase above into concrete actions
		assertEquals("First Name did not match", getJsonPath(response, "firstname"), TestDataBuild.fName);
		assertEquals("Last Name did not match", getJsonPath(response, "lastname"), TestDataBuild.lName);
		int price = TestDataBuild.tPrice;
		assertEquals("Total Price did not match", getJsonPath(response, "totalprice"), String.valueOf(price));
		boolean depositpaid = TestDataBuild.paid;
		assertEquals("Deposit Paid did not match", getJsonPath(response, "depositpaid"), String.valueOf(depositpaid));
		assertEquals("Check in Date did not match", getJsonPath(response, "bookingdates.checkin"),
				TestDataBuild.checkin);
		assertEquals("Check out Date did not match", getJsonPath(response, "bookingdates.checkout"),
				TestDataBuild.checkOut);
		assertEquals("Additional Needs did not match", getJsonPath(response, "additionalneeds"),
				TestDataBuild.extraDetails);
	}

	@When("user pass the invalid booking Id as a path parameter")
	public void user_pass_the_invalid_booking_id_as_a_path_parameter() {
		// Write code here that turns the phrase above into concrete actions
		response = requestSpec.when().log().all().get(getBookDetailById + "/" + invalidBookingID);
	}

	@Then("user should get the response code {int} for invalid BookingID")
	public void user_should_get_the_response_code_for_invalid_booking_id(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 404);
	}

	@And("user verify the respose body should display {string} as a error message")
	public void user_verify_the_respose_body_should_display_as_a_error_message(String expectedErrorMessage) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(expectedErrorMessage,response.asString());
	}

}
