@Login
Feature: Smoke testing for Login page

  Background:
    Given user is on the landing Page
    When user clicks the login_signup button

    Scenario: Verify landing page is redirecting to LoginPage

      Then user should redirected to the login page

    Scenario Outline: Verify valid and invalid credentials

      When user enter username "<username>" and "<password>"
      And clicks the login button
      Then the user should see the message "<expectedMessage>"

      Examples:
        | username | password   | expectedMessage                        |
        | patient  | patient123 | Medical Consultation                   |
        | patient  | kkk1234    | Invalid credentials. Please try again. |
        | doctor   | doctor123  | Status:                                |


#  Scenario: Verify navigation from Login to Register page
#    When user clicks on "Sign Up" link
#    Then user should be redirected to registration page
