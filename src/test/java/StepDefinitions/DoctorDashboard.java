// StepDefinitions.DoctorDashboard.java (Already fixed in previous response, ensuring correctness)
package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.DoctorDashboardPage;
import pages.LoginPage;
import core.util.BasePage;

import java.util.Map;


public class DoctorDashboard extends  BasePage{

    LoginPage loginPage;
    DoctorDashboardPage dPage;
    String messageContent;

    @Given("the Doctor is logged in and on the Dashboard page")
    public void DoctorLoggingIN() {
        loginPage = new LoginPage();

        // Explicitly wait for the initial button to be clickable before clicking
        webWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/auth']")));
        loginPage.click_loginSignUpButton();

        loginPage.isOnLandingPage();
        loginPage.login("ppp", "ppp123");
        loginPage.clickLoginButton();

        Assert.assertTrue("Failed to verify Doctor Dashboard presence after login.", loginPage.verifyDashboard("Status:"));
    }

    @When("the Doctor views the first Patient Message")
    public void readFirstRequest() {
        dPage = new DoctorDashboardPage();
        WebElement card = dPage.getFirstMessageCard();
        Assert.assertNotNull("Expected at least one patient message to be present.", card);
        messageContent = dPage.getMessageContent(card); // store unique text
    }

    @And("the message has a severity of {string}")
    public void the_message_has_a_severity_of(String expectedSeverity) {
        String actualSeverity = dPage.getMessageSeverityByContent(messageContent);
        Assert.assertEquals("Expected message severity to be " + expectedSeverity + " but found " + actualSeverity,
                expectedSeverity, actualSeverity);
    }

    @And("the Doctor enters the diagnosis {string}")
    public void the_doctor_enters_the_diagnosis(String diagnosis) {
        dPage.enterDiagnosis(diagnosis);
    }

    @And("the Doctor adds a new medication")
    public void the_doctor_adds_a_new_medication() {
        dPage.addMedication();
    }

    @And("the Doctor fills in Medication {int} details:")
    public void the_doctor_fills_in_medication_details(int medicationNumber, DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String medName = data.get("Medication Name");
        String timing = data.get("Dosage Timing");
        String duration = data.get("Duration (Days)");

        dPage.fillMedicationDetails(medicationNumber - 1, medName, timing, duration);
    }

    @And("the Doctor clicks the {string} button")
    public void the_doctor_clicks_the_send_prescription_button(String buttonName) {
        dPage.clickSendPrescription();
        try {
            Thread.sleep(1000); // optional wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("a success message {string} should be displayed")
    public void a_success_message_should_be_displayed(String expectedMessage) {
        String actualMessage = dPage.getToastMessage();
        Assert.assertEquals("Expected success toast not found.", expectedMessage, actualMessage);
    }
}