package StepDefinitions;

import core.util.BasePage;
import core.util.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

import java.util.Map;

public class EndToEndSteps extends BasePage{
    LoginPage loginPage;
    PatientForm patientForm;
    EndToEnd endToEnd;
    DoctorDashboardPage dPage;
    String messageContent;

    @Given("user is on the landing page")
    public void user_is_on_the_landing_page() {
        loginPage = new LoginPage();
    }

    @When("user clicks the login_signup buttonn")
    public void user_clicks_the_login_signup_button() {
        loginPage.click_loginSignUpButton();
    }

    @Then("user should be redirected to the login page")
    public void userShouldBeRedirectedToTheLoginPage() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(loginPage.isOnLandingPage());
    }

    @When("user enter username2 {string} and {string}")
    public void user_enter_username_and(String username, String password) {
        webWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username")));
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("clicks the login button2")
    public void clicks_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user should see2 the messages {string}")
    public void the_user_should_see_the_message(String expectedMessage) {
        Assert.assertTrue(loginPage.verifyDashboard(expectedMessage));
    }

    @When("user clicks on feature card and redirects to consultation Form2")
    public void user_redirects_to_consultation_Form() {
        patientForm = new PatientForm();
        Assert.assertTrue(patientForm.verifyFormAppeared());
    }

    @Then("user fills the consultation form with2 {string}, {string}, {string}, {string}, and {string}" )
    public void user_fills_the_consultation_form(String fname , String age , String specialist , String level , String Description) {

        patientForm.filling_Form(fname, age, specialist, level, Description);
    }

    @And("clicks the send button2")
    public void clicks_the_send_button() {
        patientForm.sendPrescription();
    }

    @Then("successful pop-up should appear2")
    public void successful_popup_should_appear() {

        Assert.assertTrue(patientForm.verifySendOrNOt());
    }

    @And("clicks the logout button in the patient dashboard")
    public void clicks_the_logout_button() {
        endToEnd = new EndToEnd();
        endToEnd.click_logoutButtonPatient();
    }

    @When("user is on the landing Page3")
    public void user_is_on_landing_page() {
        loginPage = new LoginPage();
    }

    @Given("the Doctor is logged in and on the Dashboard page2")
    public void DoctorLoggingIN() {
        loginPage = new LoginPage();

        // Explicitly wait for the initial button to be clickable before clicking
        webWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/auth']")));
        loginPage.click_loginSignUpButton();

        loginPage.isOnLandingPage();
        loginPage.login("doctor999", "doctor999");
        loginPage.clickLoginButton();

        Assert.assertTrue("Failed to verify Doctor Dashboard presence after login.", loginPage.verifyDashboard("Status:"));
    }


    @When("the Doctor views the first Patient Message2")
    public void readFirstRequest() {
        dPage = new DoctorDashboardPage();
        WebElement card = dPage.getFirstMessageCard();
        Assert.assertNotNull("Expected at least one patient message to be present.", card);
        messageContent = dPage.getMessageContent(card); // store unique text
    }

    @And("the message has a severity2 of {string}")
    public void the_message_has_a_severity_of(String expectedSeverity) {
        String actualSeverity = dPage.getMessageSeverityByContent(messageContent);
        Assert.assertEquals("Expected message severity to be " + expectedSeverity + " but found " + actualSeverity,
                expectedSeverity, actualSeverity);
    }

    @And("the Doctor enters the diagnosis2 {string}")
    public void the_doctor_enters_the_diagnosis(String diagnosis) {
        dPage.enterDiagnosis(diagnosis);
    }

    @And("the Doctor adds a new medication2")
    public void the_doctor_adds_a_new_medication() {
        dPage.addMedication();
    }

    @And("the Doctor fills in Medication {int} details2:")
    public void the_doctor_fills_in_medication_details(int medicationNumber, DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String medName = data.get("Medication Name");
        String timing = data.get("Dosage Timing");
        String duration = data.get("Duration (Days)");

        dPage.fillMedicationDetails(medicationNumber - 1, medName, timing, duration);
    }

    @And("the Doctor clicks the {string} button2")
    public void the_doctor_clicks_the_send_prescription_button(String buttonName) {
        dPage.clickSendPrescription();
        try {
            Thread.sleep(1000); // optional wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("a success message {string} should be displayed2")
    public void a_success_message_should_be_displayed(String expectedMessage) {
        //String actualMessage = dPage.getToastMessage();
        // Assert.assertEquals("Expected success toast not found.", expectedMessage, actualMessage);
    }

    @And("clicks on the logout button in the doctor dashboard")
    public void clicksOnTheLogoutButtonInTheDoctorDashboard() {
        // Write code here that turns the phrase above into concrete actions
        endToEnd.click_logoutButtonDoctor();
    }

    @Then("the user should see the messages{int} {string}")
    public void theUserShouldSeeTheMessages(int arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(endToEnd.verifyMyPrescriptions());
    }

    @When("the Doctor views the last prescription and verifies the date as today's date")
    public void theDoctorViewsTheLastPrescriptionAndVerifiesTheDateAsTodaySDate() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(endToEnd.verifyLastPrescriptionDate());
    }


    @And("clicks on the Back to Dashboard button")
    public void clicksOnTheBackToDashboardButton() {
        // Write code here that turns the phrase above into concrete actions
        endToEnd.click_backToDashboardButton();
    }

    @Then("clicks on the logout button in the patient dashboard")
    public void clicks_the_logout_button_patient() {
        endToEnd = new EndToEnd();
        endToEnd.click_logoutButtonPatient();
    }


    @And("user clicks the login_signup button{int}")
    public void userClicksTheLogin_signupButton(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        loginPage.click_loginSignUpButton();
    }
}
