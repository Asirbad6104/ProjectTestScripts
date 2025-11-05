Feature: Doctor Prescription Response

  Scenario: Doctor sends prescription to patient
    Given the doctor login credentials are provided
      | username | doctor    |
      | password | doctor123 |
    When the doctor sends a prescription to the first message
    Then the prescription should be successfully sent
