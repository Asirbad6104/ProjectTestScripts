package pages;

import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MedicationReminder{
    WebDriver driver;
    WebDriverWait wait;

    private By Medication_reminder_card = By.xpath("//h3[text()='Medication Reminders']");
    private By reminderDashboardTitle = By.xpath("//h3[contains(normalize-space(),'Medication Schedule')]");
    private By takenButton = By.xpath("//button[@class='take-button']");
    private By isTaken = By.xpath("//span[@id='taken']");

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(takenButton));
        driver.findElement(takenButton).click();
    }

//    public boolean isMarked(){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(isTaken));
//        String text = driver.findElement(isTaken).getText();
//        if(text.equals("taken")){
//            return true;
//        }
//        return false;
//    }

}