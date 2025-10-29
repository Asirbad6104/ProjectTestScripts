package StepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.LoginPage;
import pages.MedicationReminder;
import pages.PatientForm;


public class PatientDashboardSmokeSteps {

    LoginPage loginPage;
    PatientForm patientForm;
    MedicationReminder medicationReminder;

    @Given("user is on the landing Pages")
    public void user_is_on_the_landing_page() {
        loginPage = new LoginPage();
    }

    @When("user clicks the login_signup buttons")
    public void user_clicks_the_login_signup_button() {
        loginPage.click_loginSignUpButton();
    }

    @When("user enter usernam {string} and {string}")
    public void user_enter_username_and(String username, String password) {
        loginPage.login(username, password);
    }
    @And("clicks the login buttons")
    public void clicks_the_login_buttons() {
        loginPage.clickLoginButton();
    }
    @Then("the user should see the messages {string}")
    public void the_user_should_see_the_message(String expectedMessage) {
        loginPage.verifyDashboard(expectedMessage);
    }



    @When("user clicks on feature card and redirects to consultation Form")
    public void user_redirects_to_consultation_Form() {
        patientForm = new PatientForm();
        Assert.assertTrue(patientForm.verifyFormAppeared());
    }

    @Then("user fills the consultation form with {string}, {string}, {string}, {string}, and {string}" )
    public void user_fills_the_consultation_form(String fname , String age , String specialist , String level , String Description) {

        patientForm.filling_Form(fname, age, specialist, level, Description);
    }

    @And("clicks the send button")
    public void clicks_the_send_button() {
        patientForm.sendPrescription();
    }

    @Then("successful pop-up should appear")
    public void successful_popup_should_appear() {

        Assert.assertTrue(patientForm.verifySendOrNOt());

    }

    @When("user clicks on medication reminder in dashboard and redirect to reminder page")
    public void user_clicks_on_medication_reminder_in_dashboard() {

        medicationReminder = new MedicationReminder();
        medicationReminder.clickOnReminder();

        Assert.assertTrue(medicationReminder.verifyReminderPage());

    }
    @And("clicks on Mark as taken button")
    public void clicks_on_Mark_as_taken_button() {
        medicationReminder.clickTakenButton();
    }
}