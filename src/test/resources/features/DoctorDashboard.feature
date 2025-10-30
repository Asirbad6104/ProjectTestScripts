 @new
Feature: Doctor Dashboard Prescription Workflow

  Scenario: 1. Successful Prescription Submission for a High Severity Message


    Given the Doctor is logged in and on the Dashboard page

    # When: Actions taken by the user
    When the Doctor views the first Patient Message
    And the message has a severity of "HIGH"
    And the Doctor enters the diagnosis "Acne Vulgaris - Mild"
    And the Doctor adds a new medication
    And the Doctor fills in Medication 1 details:
      | Field | Value |
      | Medication Name | Clindamycin |
      | Dosage Timing | Morning |
      | Duration (Days) | 14 |
    And the Doctor fills in Medication 2 details:
      | Field | Value |
      | Medication Name | Benzoyl Peroxide Cream |
      | Dosage Timing | Night |
      | Duration (Days) | 28 |
    And the Doctor clicks the "Send Prescription" button

    # Then: Expected outcomes
    Then a success message "✅ Prescription sent successfully!" should be displayed
#    And the Patient Message should no longer be visible on the dashboard
#    And the prescription form should be reset to its initial state