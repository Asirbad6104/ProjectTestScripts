package pages;

import hooks.Hooks;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

import static hooks.Hooks.driver;
import static hooks.Hooks.wait;

public class PatientForm {
    WebDriver driver;
    WebDriverWait wait;

    public PatientForm(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(feature_card_1));
        driver.findElement(feature_card_1).click();
        String actualMessage = driver.findElement(form_element).getText();
        String expectedMessage = "Medical Consultation For";
        return actualMessage.contains(expectedMessage);
    }
    public void filling_Form(String fname , String age , String specialist , String level , String Description){
        driver.findElement(fullname).sendKeys(fname);

        driver.findElement(Age).sendKeys(age);

        WebElement specialist_type = driver.findElement(doctype);
        Select s  = new Select(specialist_type);
        s.selectByVisibleText(specialist);

        WebElement urgency_type = driver.findElement(severoity);
        Select s2 = new Select(urgency_type);
        s2.selectByVisibleText(level);

        driver.findElement(description).sendKeys(Description);

    }

    public void sendPrescription(){
        driver.findElement(sendButton).click();
    }

    public boolean verifySendOrNOt(){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Medical Consultation']")));
        String actualMessage = driver.findElement(title).getText();
        String expectedMessage = "Medical Consultation";
        return actualMessage.contains(expectedMessage);
    }


    private boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
