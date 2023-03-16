@bookerAPI
Feature: Create Auth token API:

 Scenario: The purpose of this test case is to verify login API functionality with a valid username & password.
    Given user has access to endpoint "createAuthToken"
    When user creates a auth token with credential "admin" & "password123"
    Then user should get the response code 200
    And  user should get the auth token
    
    
 Scenario: The purpose of this test case is to verify login API functionality with a invalid username & password.
    Given user has access to endpoint "createAuthToken"
    When user creates a auth token with invalid credential "adminn" & "password1234"
    Then user should get the response code 200
    And  user should get the error message "Bad credentials"
    
 Scenario: The purpose of this test case is to verify login API functionality with a valid username & invalid password.
    Given user has access to endpoint "createAuthToken"
    When user creates a auth token with invalid credential "admin" & "password1234"
    Then user should get the response code 200
    And  user should get the error message "Bad credentials"
    
 Scenario: The purpose of this test case is to verify login API functionality with a invalid username & valid password.
    Given user has access to endpoint "createAuthToken"
    When user creates a auth token with invalid credential "adminn" & "password123"
    Then user should get the response code 200
    And  user should get the error message "Bad credentials"
    
 Scenario: The purpose of this testcase is to verify the login API functionality, while user set blank value for username and password field.
    Given user has access to endpoint "createAuthToken"
    When user creates a auth token with invalid credential "" & ""
    Then user should get the response code 200
    And  user should get the error message "Bad credentials"
    
 Scenario: The purpose of this testcase is to verify the login API functionality, while user set valid value for username and blank value password field.
    Given user has access to endpoint "createAuthToken"
    When user creates a auth token with invalid credential "admin" & ""
    Then user should get the response code 200
    And  user should get the error message "Bad credentials"
    
 Scenario: The purpose of this testcase is to verify the login API functionality, while user set blank value for username and valid value for password field.
    Given user has access to endpoint "createAuthToken"
    When user creates a auth token with invalid credential "" & "password123"
    Then user should get the response code 200
    And  user should get the error message "Bad credentials"