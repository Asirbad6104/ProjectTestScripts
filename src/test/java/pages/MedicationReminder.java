package pages;

import core.util.BasePage;
import org.openqa.selenium.By;

public class MedicationReminder extends BasePage {

    private By medicationReminderCard = By.xpath("//h3[text()='Medication Reminders']");
    private By reminderDashboardTitle = By.xpath("//h3[contains(normalize-space(),'Medication Schedule')]");
    private By takenButton = By.xpath("//button[@class='take-button']");
    private By isTaken = By.xpath("//span[@id='taken']");

    public MedicationReminder() {}

    public void clickOnReminder() {
        click(medicationReminderCard);
    }

    public boolean verifyReminderPage() {
        String actualText = getText(reminderDashboardTitle);
        return actualText.equals("Medication Schedule");
    }

    public void clickTakenButton() {
        click(takenButton);
    }
}
