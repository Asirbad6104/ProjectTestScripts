package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Duration;
import java.util.List;

public class DoctorDashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    private final By allMessageCards = By.cssSelector(".message-card");
    private final By diagnosisField = By.cssSelector(".prescription-form .input-field[name='diagnosis']");
    private final By addMedicationButton = By.xpath("//button[contains(text(), 'Add Medication')]");
    private final By sendPrescriptionButton = By.xpath("//button[contains(text(), 'Send Prescription')]");
    private final By notificationToast = By.cssSelector(".notification");

    public DoctorDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement getFirstMessageCard() {
        List<WebElement> cards = driver.findElements(allMessageCards);
        return cards.isEmpty() ? null : cards.get(0);
    }

    public String getMessageContent(WebElement card) {
        return card.findElement(By.cssSelector(".message-body p")).getText().trim();
    }

    public String getMessageSeverityByContent(String content) {
        List<WebElement> cards = driver.findElements(allMessageCards);
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
        List<WebElement> cards = driver.findElements(allMessageCards);
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
        driver.findElement(diagnosisField).sendKeys(diagnosis);
    }

    public void addMedication() {
        driver.findElement(addMedicationButton).click();
    }

    public void fillMedicationDetails(int index, String name, String timing, String duration) {
        By medCardLocator = By.cssSelector(".medication-card:nth-child(" + (index + 1) + ")");
        WebElement medCard = wait.until(ExpectedConditions.visibilityOfElementLocated(medCardLocator));

        WebElement nameInput = medCard.findElement(By.cssSelector("input[id='medicationName-" + index + "']"));
        nameInput.clear();
        nameInput.sendKeys(name);

        WebElement timingSelectElement = medCard.findElement(By.cssSelector("select[id='dosageTiming-" + index + "']"));
        Select timingSelect = new Select(timingSelectElement);
        timingSelect.selectByVisibleText(timing);

        WebElement durationInput = medCard.findElement(By.cssSelector("input[id='durationDays-" + index + "']"));
        durationInput.clear();
        durationInput.sendKeys(duration);

        WebElement startDateInput = medCard.findElement(By.cssSelector("input[id='startDate-" + index + "']"));
        Assert.assertTrue("Start date field is not displayed", startDateInput.isDisplayed());
    }

    public void clickSendPrescription() {
        driver.findElement(sendPrescriptionButton).click();
    }

    public String getToastMessage() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationToast));
        return toast.getText().trim();
    }

    public boolean isDiagnosisFieldEmpty() {
        return driver.findElement(diagnosisField).getAttribute("value").isEmpty();
    }
}
