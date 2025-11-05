Feature: Submit Consultation Form

  Scenario: Submit consultation form
    Given the user login credentials are provided for prescription access
      | username | patient123    |
      | password | patient1234   |
    When the user submits the consultation form with details
      | specialist  | Cardiologist |
      | level       | LOW          |
      | description | TestRest     |
    Then the user should receive a confirmation for consultation