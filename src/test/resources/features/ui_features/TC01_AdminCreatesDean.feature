@ui
Feature: Admin creates Dean
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