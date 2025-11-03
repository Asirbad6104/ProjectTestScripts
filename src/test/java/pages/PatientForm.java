// pages.PatientForm.java
package pages;

import core.util.BasePage;
import core.util.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PatientForm extends BasePage {

    // Locators
    private By featureCard = By.xpath("//div[@class='feature-card']");
    private By formHeader = By.xpath("//h3[text()='Medical Consultation Form']");
    private By fullName = By.xpath("//input[@placeholder='Enter your full name']");
    private By age = By.xpath("//input[@placeholder='Enter your age']");
    private By diseaseDropdown = By.id("disease");
    private By severityDropdown = By.id("severity");
    private By descriptionBox = By.id("description");
    private By sendButton = By.cssSelector("button[type='submit']");
    private By title = By.xpath("//h3[text()='Medical Consultation']");

    // Constructor
    public PatientForm() {}

    // ✅ Reusable high-level method
    public boolean verifyFormAppeared() {
        click(waitForElementToBeClickable(featureCard));
        waitForVisibility(formHeader);
        return getText(formHeader).contains("Medical Consultation For");
    }

    // ✅ Reusable form filling using helper methods from BasePage
    public void fillForm(String name, String ageValue, String specialist, String level, String desc) {
        type(fullName, name);
        type(age, ageValue);
        selectByVisibleText(diseaseDropdown, specialist);
        selectByVisibleText(severityDropdown, level);
        type(descriptionBox, desc);
    }

    public void sendPrescription() {
        click(sendButton);
    }

    public boolean verifySendOrNot() {
        try {
            webWait().until(ExpectedConditions.alertIsPresent());
            Alert alert = DriverManager.get().switchTo().alert();
            alert.accept();

            // ✅ Add a short wait for page transition (especially in headless)
            Thread.sleep(1500); // 1.5 sec just to allow DOM update

            By confirmationTitle = By.xpath("//h3[contains(text(),'Medical Consultation')]");
            webWait().until(ExpectedConditions.visibilityOfElementLocated(confirmationTitle));

            String actualMessage = getText(confirmationTitle);
            return actualMessage.contains("Medical Consultation");
        } catch (Exception e) {
            System.out.println("❌ Verification failed: " + e.getMessage());
            return false;
        }
    }
}
