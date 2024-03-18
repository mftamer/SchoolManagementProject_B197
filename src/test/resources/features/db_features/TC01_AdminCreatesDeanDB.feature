@db
Feature: Dean Creation Validation DB
  Scenario: User Validates Created Dean From DB
    Given User sets connection
    And User creates statement
    When User executes query for created dean
    Then validates result set
    And User terminates connection