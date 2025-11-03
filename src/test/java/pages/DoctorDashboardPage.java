package pages;

import core.util.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DoctorDashboardPage extends BasePage {

    private final By diagnosisField = By.xpath("//input[@placeholder='Enter patient diagnosis']");
    private final By addMedicationButton = By.xpath("//button[contains(text(),'Add Medication')]");
    private final By sendPrescriptionButton = By.xpath("//button[contains(text(),'Send Prescription')]");
    private final By notificationToast = By.cssSelector(".toast-message");
    private final By allMessageCards = By.cssSelector(".message-card");

    public DoctorDashboardPage() {}

    // ✅ Get first message card (safe version)
    public WebElement getFirstMessageCard() {
        List<WebElement> cards = waitForVisibilityOfAll(allMessageCards);
        return cards.isEmpty() ? null : cards.get(0);
    }

    // ✅ Get text inside a message card
    public String getMessageContent(WebElement card) {
        return card.findElement(By.cssSelector(".message-body p")).getText().trim();
    }

    // ✅ Find message severity by text content (no direct DriverManager)
    public String getMessageSeverityByContent(String content) {
        List<WebElement> cards = waitForVisibilityOfAll(allMessageCards);
        for (WebElement card : cards) {
            try {
                String body = card.findElement(By.cssSelector(".message-body p")).getText().trim();
                if (body.contains(content)) {
                    return card.findElement(By.cssSelector(".severity-tag")).getText().trim();
                }
            } catch (StaleElementReferenceException ignored) {}
        }
        return "";
    }

    // ✅ Check if message card is visible by its text
    public boolean isMessageCardVisibleByContent(String content) {
        List<WebElement> cards = waitForVisibilityOfAll(allMessageCards);
        for (WebElement card : cards) {
            try {
                String body = card.findElement(By.cssSelector(".message-body p")).getText().trim();
                if (body.contains(content)) {
                    return card.isDisplayed();
                }
            } catch (StaleElementReferenceException e) {
                return false;
            }
        }
        return false;
    }

    // ✅ Type diagnosis directly using BasePage helper
    public void enterDiagnosis(String diagnosis) {
        type(diagnosisField, diagnosis);
    }

    // ✅ Click "Add Medication"
    public void addMedication() {
        click(addMedicationButton);
    }

    // ✅ Fill medication details safely for a given card index
    public void fillMedicationDetails(int index, String name, String timing, String duration) {
        By medCardLocator = By.xpath("(//div[contains(@class,'medication-card')])[" + (index + 1) + "]");
        WebElement medCard = waitForVisibility(medCardLocator);

        // Relative locators inside the card
        WebElement nameInput = medCard.findElement(By.xpath(".//label[contains(text(),'Medication Name')]/following::input[1]"));
        nameInput.clear();
        nameInput.sendKeys(name);

        WebElement timingSelectElement = medCard.findElement(By.xpath(".//label[contains(text(),'Dosage Timing')]/following::select[1]"));
        new Select(timingSelectElement).selectByVisibleText(timing);

        WebElement durationInput = medCard.findElement(By.xpath(".//label[contains(text(),'Duration')]/following::input[@type='number'][1]"));
        durationInput.clear();
        durationInput.sendKeys(duration);

        WebElement startDateInput = medCard.findElement(By.xpath(".//label[contains(text(),'Start Date')]/following::input[@type='date'][1]"));
        Assert.assertTrue("Start date field is not displayed", startDateInput.isDisplayed());
    }

    // ✅ Click "Send Prescription" button
    public void clickSendPrescription() {
        click(sendPrescriptionButton);
    }

    // ✅ Optional: Retrieve toast message (if required)
    public String getToastMessage() {
        return getText(notificationToast);
    }

    // ✅ Check if diagnosis field is empty (no direct DriverManager)
    public boolean isDiagnosisFieldEmpty() {
        return waitForVisibility(diagnosisField).getAttribute("value").isEmpty();
    }

    // 🔹 Utility: Wait for all message cards to be visible
    private List<WebElement> waitForVisibilityOfAll(By locator) {
        return webWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
