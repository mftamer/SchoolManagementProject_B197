@e2e
Feature: Dean Controller E2E Validation
  Scenario: Admin should be able to create Dean
    Given admin goes to the url
    And clicks on login option
    And enters "AdminBatch197" in username input field
    And enters "Batch197+" in password field
    And clicks on the login button
    And clicks on the Menu button
    And clicks on Dean Management option
    And enters "Kate" in the name field
    And enters "Middelton" in the Surname field
    And enters "UK" in the Birth Place field
    And enters "Female" in Gender field
    And enters "24-09-1990" in DateOfBirth field
    And enters "226-662-2261" in Phone Number field
    And enters ssn in SSN field
    And enters "Kate" in Username field
    And enters password in Password field
    And clicks on Submit button
    Then verifies Dean is created

  Scenario: Admin gets the information for the created Dean Test
    Given User is authorized as "Admin"
    And User gets id of the Dean with username "Kate"
    And User sets the Url for Get Dean By id
    And sets the expected data for Get Dean By id
    When sends GET request and get response
    Then verifies status code is 200
    And verifies response body for Get Dean By id


  Scenario: User Validates Created Dean From DB
    Given User sets connection
    And User creates statement
    When User executes query for created dean
    Then validates result set
    And User terminates connection
    And User deletes the created dean