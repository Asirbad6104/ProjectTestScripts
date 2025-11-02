Feature: User Login

  Scenario: Logging in with valid credentials
    Given the user login credentials are provided
      | field    | value     |
      | username | doc1      |
      | password | doc123    |
    When the user attempts to login
    Then the user should receive a login success message