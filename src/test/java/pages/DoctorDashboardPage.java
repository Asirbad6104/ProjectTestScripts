package pages;

import core.util.BasePage;
import core.util.DriverManager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;

public class DoctorDashboardPage extends BasePage {

    private final By diagnosisField = By.xpath("//input[@placeholder='Enter patient diagnosis']");
    private final By addMedicationButton = By.xpath("//button[contains(text(),'Add Medication')]");
    private final By sendPrescriptionButton = By.xpath("//button[contains(text(),'Send Prescription')]");
    private final By notificationToast = By.cssSelector(".toast-message");
    private final By allMessageCards = By.cssSelector(".message-card");
    public DoctorDashboardPage() {
    }

    public WebElement getFirstMessageCard() {
        List<WebElement> cards = DriverManager.get().findElements(allMessageCards);
        return cards.isEmpty() ? null : cards.get(0);
    }

    public String getMessageContent(WebElement card) {
        return card.findElement(By.cssSelector(".message-body p")).getText().trim();
    }

    public String getMessageSeverityByContent(String content) {
        List<WebElement> cards = DriverManager.get().findElements(allMessageCards);
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

    public boolean isMessageCardVisibleByContent(String content) {
        List<WebElement> cards = DriverManager.get().findElements(allMessageCards);
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

    public void enterDiagnosis(String diagnosis) {
        webWait().until(ExpectedConditions.visibilityOfElementLocated(diagnosisField)).sendKeys(diagnosis);
    }

    public void addMedication() {
        click(DriverManager.get().findElement(addMedicationButton));
    }
    // 🔹 Fill details for a specific medication card
    public void fillMedicationDetails(int index, String name, String timing, String duration) {
        // ✅ Fixed XPath (removed extra parenthesis)
        By medCardLocator = By.xpath("(//div[contains(@class,'medication-card')])[" + (index + 1) + "]");
        WebElement medCard = webWait().until(ExpectedConditions.visibilityOfElementLocated(medCardLocator));

        // ✅ Relative XPath (.// instead of //)
        WebElement nameInput = medCard.findElement(By.xpath(".//label[contains(text(),'Medication Name')]/following::input[1]"));
        nameInput.clear();
        nameInput.sendKeys(name);

        WebElement timingSelectElement = medCard.findElement(By.xpath(".//label[contains(text(),'Dosage Timing')]/following::select[1]"));
        Select timingSelect = new Select(timingSelectElement);
        timingSelect.selectByVisibleText(timing);

        WebElement durationInput = medCard.findElement(By.xpath(".//label[contains(text(),'Duration')]/following::input[@type='number'][1]"));
        durationInput.clear();
        durationInput.sendKeys(duration);

        WebElement startDateInput = medCard.findElement(By.xpath(".//label[contains(text(),'Start Date')]/following::input[@type='date'][1]"));
        Assert.assertTrue("Start date field is not displayed", startDateInput.isDisplayed());
    }

    public void clickSendPrescription() {
        click(DriverManager.get().findElement(sendPrescriptionButton));
    }

//    public String getToastMessage() {
//        WebElement toast = webWait().until(ExpectedConditions.visibilityOfElementLocated(notificationToast));
//        return toast.getText().trim();
//    }

    public boolean isDiagnosisFieldEmpty() {
        return DriverManager.get().findElement(diagnosisField).getAttribute("value").isEmpty();
    }
}