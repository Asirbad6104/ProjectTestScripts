@PatientDashboard
Feature: Smoke Test for Patient Dashboard

  Background:
    Given user is on the landing Pages
    When user clicks the login_signup buttons
    When user enter usernam "kkk" and "kkk123"
    And clicks the login buttons
    Then the user should see the messages "Medical Consultation"

  Scenario Outline: Patient sends disease details to doctor

    When user clicks on feature card and redirects to consultation Form
   Then user fills the consultation form with "<Fullname>", "<Age>", "<Specialist>", "<Level>", and "<Description>"
   And clicks the send button
    Then successful pop-up should appear
    Examples:
      | Fullname | Age | Specialist          | Level       | Description |
      | Ashu     | 22  | Cardiology (Heart)  | Urgent Care  | Heart Fail   |

   Scenario: Medication Reminder Taken Button
     When user clicks on medication reminder in dashboard and redirect to reminder page
#     And clicks on Mark as taken button
#     Then It should be marked as taken
