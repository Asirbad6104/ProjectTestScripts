// pages.PatientForm.java (Already fixed in previous response, ensuring correctness)
package pages;

import core.util.BasePage;
import core.util.DriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PatientForm extends BasePage {

    public PatientForm() {
    }

    private By feature_card_1 = By.xpath("//div[@class='feature-card']");
    private By form_element = By.xpath("//h3[text()='Medical Consultation Form']");
    private By fullname = By.xpath("//input[@placeholder='Enter your full name']");
    private By Age = By.xpath("//input[@placeholder='Enter your age']");
    private By doctype = By.xpath("//select[@id='disease']");
    private By severoity = By.xpath("//select[@id='severity']");
    private By description = By.xpath("//textArea[@id = 'description']");
    private By sendButton =  By.cssSelector("button[type='submit']");
    private By title = By.xpath("//h3[text()='Medical Consultation']");

    public boolean verifyFormAppeared(){
        // Wait for the card to be clickable, then click it
        click(webWait().until(ExpectedConditions.elementToBeClickable(feature_card_1)));

        // Wait for the form element to appear on the new screen
        webWait().until(ExpectedConditions.visibilityOfElementLocated(form_element));

        String actualMessage = DriverManager.get().findElement(form_element).getText();
        String expectedMessage = "Medical Consultation For";
        return actualMessage.contains(expectedMessage);
    }

    public void filling_Form(String fname , String age , String specialist , String level , String Description){
        webWait().until(ExpectedConditions.visibilityOfElementLocated(fullname)).sendKeys(fname);

        DriverManager.get().findElement(Age).sendKeys(age);

        WebElement specialist_type = DriverManager.get().findElement(doctype);
        Select s  = new Select(specialist_type);
        s.selectByVisibleText(specialist);

        WebElement urgency_type = DriverManager.get().findElement(severoity);
        Select s2 = new Select(urgency_type);
        s2.selectByVisibleText(level);

        DriverManager.get().findElement(description).sendKeys(Description);
    }

    public void sendPrescription(){
        click(DriverManager.get().findElement(sendButton));
    }

    public boolean verifySendOrNOt(){
        webWait().until(ExpectedConditions.alertIsPresent());
        Alert alert = DriverManager.get().switchTo().alert();
        alert.accept();
        webWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Medical Consultation']")));
        String actualMessage = DriverManager.get().findElement(title).getText();
        String expectedMessage = "Medical Consultation";
        return actualMessage.contains(expectedMessage);
    }


}