package pages;

import core.util.BasePage;
import core.util.DriverManager;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class EndToEnd extends BasePage {

    private By login_signup_button = By.xpath("//a[@href='/auth']");
    private By usernameField = By.id("login-username");
    private By passwordField = By.id("login-password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By title  = By.xpath("//div[@class='form-title']");
    private By dashboardMsg = By.xpath("//h3[text()='Medical Consultation'] | //strong[text()='Status:']");
//    private By prescriptionMsg = By.xpath("//h3[text()='My Prescriptions']");
    private By logoutButtonPatient = By.xpath("//button[@class='logout-btn']");
    private By logoutButtonDoctor = By.xpath("//button[@class='logout-button']");
    private By feature_card_1 = By.xpath("//div[@class='feature-card']");
    private By feature_card_2 = By.xpath("//div[@class='feature-card'][2]");
    private By form_element = By.xpath("//h3[text()='Medical Consultation Form']");
    private By prescriptionElement = By.xpath("//h3[@class='section-title']");
    private By fullname = By.xpath("//input[@placeholder='Enter your full name']");
    private By Age = By.xpath("//input[@placeholder='Enter your age']");
    private By doctype = By.xpath("//select[@id='disease']");
    private By severoity = By.xpath("//select[@id='severity']");
    private By description = By.xpath("//textArea[@id = 'description']");
    private By sendButton =  By.cssSelector("button[type='submit']");
    private final By diagnosisField = By.xpath("//input[@placeholder='Enter patient diagnosis']");
    private final By addMedicationButton = By.xpath("//button[contains(text(),'Add Medication')]");
    private final By sendPrescriptionButton = By.xpath("//button[contains(text(),'Send Prescription')]");
    private final By allMessageCards = By.cssSelector(".message-card");
    private final By lastPrescription = By.cssSelector("div[class='prescription-list']>:last-child");
    private final By lastPrescriptionDate = By.cssSelector("div[class='prescription-list']>:last-child>div[class='prescription-header']>div[class='prescription-date']");
    private final By backToDashboardButton = By.xpath("//span[text()='Back to Dashboard']");

    public EndToEnd() {
    }


    public void click_logoutButtonPatient(){
        // Ensure the button is clickable before attempting to click
        click(webWait().until(ExpectedConditions.elementToBeClickable(logoutButtonPatient)));
    }

    public void click_logoutButtonDoctor(){
        // Ensure the button is clickable before attempting to click
        click(webWait().until(ExpectedConditions.elementToBeClickable(logoutButtonDoctor)));
    }

    public boolean verifyMyPrescriptions(){
        // Wait for the card to be clickable, then click it
        click(webWait().until(ExpectedConditions.elementToBeClickable(feature_card_2)));

        // Wait for the form element to appear on the new screen
        webWait().until(ExpectedConditions.visibilityOfElementLocated(prescriptionElement));

        String actualMessage = DriverManager.get().findElement(prescriptionElement).getText();
        String expectedMessage = "Medical Prescriptions";
        return actualMessage.contains(expectedMessage);
    }

    public boolean verifyLastPrescriptionDate(){
        // Wait for the card to be clickable, then click it
//        click(webWait().until(ExpectedConditions.elementToBeClickable(lastPrescription)));

        // Wait for the form element to appear on the new screen
        webWait().until(ExpectedConditions.visibilityOfElementLocated(lastPrescription));

        String actualMessage = DriverManager.get().findElement(lastPrescriptionDate).getText().trim();
        String expectedMessage = "Nov 3, 2025";
        return actualMessage.equals(expectedMessage);
    }

    public void click_backToDashboardButton(){
        // Ensure the button is clickable before attempting to click
        click(webWait().until(ExpectedConditions.elementToBeClickable(backToDashboardButton)));
    }

}
