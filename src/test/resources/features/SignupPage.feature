@Signup
Feature: Smoke testing for Login page

  Background:
    Given user is on the landing Page1
    When user clicks the login_signup button1
    Then user clicks on the Sign_Up button

  Scenario: Verify LoginPage is redirecting to SignUp Page

    Then user should redirected to the Sign_Up page


  Scenario Outline: The patient registers

    When the user enters a valid "<username>" in the Username field
    And enters "<email>" in the Email field
    And selects a role "<role>" from the dropdown
    And enters a secure "<password>" in the Password field
    And enters the same "<confirm_password>" in the Confirm Password field
    And clicks the Create Account Button
    Then the user should redirected to the LoginPage1

    Examples:
      | username  | email               | role    | password  | confirm_password |
      | patient22 | patient22@gmail.com | Patient | patient22 | patient22        |

  Scenario Outline: The doctor registers

    When the user enters a valid "<username>" in the Username field
    And enters "<email>" in the Email field
    And selects a role "<role>" from the dropdown
    And handles specialization "<specialization>"
    And enters a secure "<password>" in the Password field
    And enters the same "<confirm_password>" in the Confirm Password field
    And clicks the Create Account Button
    Then the user should redirected to the LoginPage1

    Examples:
      | username   | email                 | role      | specialization   |password    | confirm_password   |
      | doctor7    | doctor7@gmail.com     | Doctor    | Cardiologist     |doctor7     | doctor7            |
