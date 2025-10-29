// pages.MedicationReminder.java (Already fixed in previous response, ensuring correctness)
package pages;

import core.util.BasePage;
import core.util.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MedicationReminder extends BasePage {

    private By Medication_reminder_card = By.xpath("//h3[text()='Medication Reminders']");
    private By reminderDashboardTitle = By.xpath("//h3[contains(normalize-space(),'Medication Schedule')]");
    private By takenButton = By.xpath("//button[@class='take-button']");
    private By isTaken = By.xpath("//span[@id='taken']");

    public MedicationReminder() {
    }

    public void clickOnReminder(){
        click(DriverManager.get().findElement(Medication_reminder_card));
    }

    public boolean verifyReminderPage(){
        // Explicit wait for the title element to ensure page transition is complete
        WebElement titleElement = webWait().until(ExpectedConditions.visibilityOfElementLocated(reminderDashboardTitle));
        String actualText = titleElement.getText();
        return actualText.equals("Medication Schedule");
    }

    public void clickTakenButton(){
        click(webWait().until(ExpectedConditions.visibilityOfElementLocated(takenButton)));
    }
}