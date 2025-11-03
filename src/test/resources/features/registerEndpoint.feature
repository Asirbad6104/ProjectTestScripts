Feature: Checking the Register endpoint

  Scenario: Registering a User
    Given the user details are provided
      | field    | value            |
      | name     | Akash            |
      | password | akash123         |
      | email    | akash@gmail.com  |
      | role     | ROLE_PATIENT     |
    When the user is successfully registered
    Then the user should receive a success message