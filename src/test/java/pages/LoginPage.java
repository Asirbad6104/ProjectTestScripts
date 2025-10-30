package pages;

import core.util.BasePage;
import core.util.DriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private By login_signup_button = By.xpath("//a[@href='/auth']");
    private By usernameField = By.id("login-username");
    private By passwordField = By.id("login-password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By title  = By.xpath("//div[@class='form-title']");
    private By dashboardMsg = By.xpath("//h3[text()='Medical Consultation'] | //strong[text()='Status:']");

    public LoginPage() {
    }

    public void click_loginSignUpButton(){
        // Ensure the button is clickable before attempting to click
        click(webWait().until(ExpectedConditions.elementToBeClickable(login_signup_button)));
    }

    public boolean isOnLandingPage(){
        webWait().until(ExpectedConditions.visibilityOfElementLocated(title));
        return DriverManager.get().findElement(title).getText().equals("Login");
    }
    public void enterUsername(String username){
        webWait().until(ExpectedConditions.elementToBeClickable(usernameField));
        DriverManager.get().findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password){
        webWait().until(ExpectedConditions.elementToBeClickable(passwordField));
        DriverManager.get().findElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton(){
        click(DriverManager.get().findElement(loginButton));
    }

    // FIX APPLIED HERE: Wait explicitly for the username field to be ready before calling enterUsername
    public void login(String username, String password){
        webWait().until(ExpectedConditions.visibilityOfElementLocated(title));
        webWait().until(ExpectedConditions.elementToBeClickable(usernameField)); // Ensures fields are present
        enterUsername(username);
        enterPassword(password);
    }

    public boolean verifyDashboard(String expectedMsg){
        if(isAlertPresent()){
            Alert alert = DriverManager.get().switchTo().alert();
            String actualMsg = alert.getText();
            boolean result = actualMsg.contains(expectedMsg);
            alert.accept();
            return result;
        }

        // Explicitly wait for the dashboard element to be visible
        WebElement msg = webWait().until(ExpectedConditions.visibilityOfElementLocated(dashboardMsg));
        String actualMessage = msg.getText();
        return actualMessage.contains(expectedMsg);
    }


    private boolean isAlertPresent() {
        try {
            webWait().until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}