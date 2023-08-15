Feature: Home Page Verification for patent renewal
  As a user i want to verify the home page functionality
  So that i can use the website without any issues

  @smoke
  Scenario Outline: Verify Landing Page
    Given the user spins up the "<browserInfo>" browser
    When user should be land on home page
    Then user is able to chat with the support team

    Examples:
      | browserInfo |
      | browser3 |


  @smoke
  Scenario Outline: Verify Landing Page
    Given the user spins up the "<browserInfo>" browser
    And user should be land on home page
    When user clicks on Get Started button

    Examples:
      | browserInfo |
      | browser5 |
