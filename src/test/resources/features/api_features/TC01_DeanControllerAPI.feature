@api
Feature: TC01_DeanController
  Scenario: Admin Creates Dean Test
    Given User is authorized as "Admin"
    And User sets the Url for Dean Save
    And sets the payload for Dean Save
    When sends POST request and get response
    Then verifies status code is 200
    And verifies response body

  Scenario: Admin gets the information for the created Dean Test
    Given User is authorized as "Admin"
    And User gets id of the Dean with username "Kate"
    And User sets the Url for Get Dean By id
    And sets the expected data for Get Dean By id
    When sends GET request and get response
    Then verifies status code is 200
    And verifies response body for Get Dean By id


  Scenario: Admin deletes created Dean Test
    Given User is authorized as "Admin"
    And User gets id of the Dean with username "Kate"
    And User sets the Url for Get Dean By id
    And User deletes the created dean