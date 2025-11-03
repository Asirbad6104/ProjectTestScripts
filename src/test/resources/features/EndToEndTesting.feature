
Feature: End to End Testing for all the pages

  Scenario: End To End Testing
    Given user is on the landing page
    When user clicks the login_signup buttonn
    Then user should be redirected to the login page
    When user enter username2 "patient99" and "patient99"
    And clicks the login button2
    Then the user should see2 the messages "Medical Consultation"
    When user clicks on feature card and redirects to consultation Form2
    Then user fills the consultation form with2 "Ashu", "22", "Gastroenterology (Digestive)", "Urgent Care", and "Digestion problem"
    And clicks the send button2
    Then successful pop-up should appear2
    And clicks the logout button in the patient dashboard
    Given the Doctor is logged in and on the Dashboard page2
    When the Doctor views the first Patient Message2
    And the message has a severity2 of "HIGH"
    And the Doctor enters the diagnosis2 "Acne Vulgaris - Mild"
    And the Doctor adds a new medication2
    And the Doctor fills in Medication 1 details2:
      | Field | Value |
      | Medication Name | Clindamycin |
      | Dosage Timing | Morning |
      | Duration (Days) | 14 |
    And the Doctor fills in Medication 2 details2:
      | Field | Value |
      | Medication Name | Benzoyl Peroxide Cream |
      | Dosage Timing | Night |
      | Duration (Days) | 28 |
    And the Doctor clicks the "Send Prescription" button2
    Then a success message "✅ Prescription sent successfully!" should be displayed2
    And clicks on the logout button in the doctor dashboard
    When user clicks the login_signup buttonn
    When user enter username2 "patient99" and "patient99"
    And clicks the login button2
    Then the user should see the messages3 "My Prescriptions"
    When the Doctor views the last prescription and verifies the date as today's date
    And clicks on the Back to Dashboard button
    Then clicks on the logout button in the patient dashboard
