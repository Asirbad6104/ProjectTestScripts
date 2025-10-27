package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MedicationReminder{
    WebDriver driver;
    WebDriverWait wait;

    private By Medication_reminder_card = By.xpath("//h3[text()='Medication Reminders']");
    private By reminderDashboardTitle = By.xpath("//h3[contains(normalize-space(),'Medication Schedule')]");
    private By takenButton = By.cssSelector("button.take-button");


    public MedicationReminder(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickOnReminder(){
        driver.findElement(Medication_reminder_card).click();

    }

    public boolean verifyReminderPage(){
        String actualText = driver.findElement(reminderDashboardTitle).getText();
        return actualText.equals("Medication Schedule");
    }

    public void clickTakenButton(){
        driver.findElement(takenButton).click();
    }

}