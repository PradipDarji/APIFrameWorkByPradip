@bookerAPI @GetBookDetailsByBookID
Feature: Get Book Details By Booking ID:

Scenario: The purpose of this test case is to verify the get book details by valid book Id.
    Given user has access to get booking details by Booking Id endpoint "GetBookingDetailsById"
    When user pass the valid booking Id as a path parameter
    Then user should get the response code 200 for GetBookingDetailsById API 
    And  user verify the response body data
    
Scenario: The purpose of this test case is to verify the get book details by invalid book Id.
    Given user has access to get booking details by Booking Id endpoint "GetBookingDetailsById"
    When user pass the invalid booking Id as a path parameter
    Then user should get the response code 404 for invalid BookingID
    And  user verify the respose body should display "Not Found" as a error message
    