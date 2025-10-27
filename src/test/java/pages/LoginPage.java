package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import hooks.Hooks;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static hooks.Hooks.driver;
import static hooks.Hooks.wait;

public class LoginPage {

    WebDriver driver;

    private By login_signup_button = By.xpath("//a[@href='/auth']");
    private By usernameField = By.id("login-username");
    private By passwordField = By.id("login-password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By title  = By.xpath("//div[@class='form-title']");
    private By dashboardMsg = By.xpath("//h3[text()='Medical Consultation'] | //strong[text()='Status:']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void click_loginSignUpButton(){
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(login_signup_button));
        driver.findElement(login_signup_button).click();
    }

    public boolean isOnLandingPage(){
        WebElement titlee = wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        return driver.findElement(title).getText().equals("Login");
    }
    public void enterUsername(String username){
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password){
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton(){
        Hooks.wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password){
       Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        enterUsername(username);
        enterPassword(password);
    }

    public boolean verifyDashboard(String expectedMsg){
        if(isAlertPresent()){
            Alert alert = driver.switchTo().alert();
            String actualMsg = alert.getText();
            boolean result = actualMsg.contains(expectedMsg);
            alert.accept();
            return result;
        }

        WebElement msg = driver.findElement(dashboardMsg);
        String actualMessage = msg.getText();
         return actualMessage.contains(expectedMsg);


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
