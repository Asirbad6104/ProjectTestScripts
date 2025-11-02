@rew
Feature: Prescription Retrieval

  Scenario: Patient retrieves prescriptions with valid credentials
    Given the user login credentials are provided for prescription access
      | username | ioi     |
      | password | ioi123  |
    When the user sends a request to get prescriptions
    Then the user should receive a list of prescriptions