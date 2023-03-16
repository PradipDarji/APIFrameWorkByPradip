@bookerAPI @createBooking
Feature: Create Booking API:

Scenario: The purpose of this test case is to verify the create booking API functionality.
    Given user has access to create book endpoint "createBooking"
    When user creates book with valid data
    Then user should get the response code 200 for createBooking 
    And  user should get the bookingId