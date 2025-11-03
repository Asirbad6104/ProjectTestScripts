package pages;

import core.util.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import core.util.DriverManager;

public class LoginPage extends BasePage {

    private By loginSignUpButton = By.xpath("//a[@href='/auth']");
    private By usernameField = By.id("login-username");
    private By passwordField = By.id("login-password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By title = By.xpath("//div[@class='form-title']");
    private By dashboardMsg = By.xpath("//h3[text()='Medical Consultation'] | //strong[text()='Status:']");

    public LoginPage() {}

    public void click_loginSignUpButton() {
        click(loginSignUpButton);
    }

    public boolean isOnLandingPage() {
        return getText(title).equals("Login");
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        waitForVisibility(title);
        enterUsername(username);
        enterPassword(password);
    }

    public boolean verifyDashboard(String expectedMsg) {
        try {
            webWait().until(ExpectedConditions.alertIsPresent());
            String alertText = DriverManager.get().switchTo().alert().getText();
            boolean matches = alertText.contains(expectedMsg);
            DriverManager.get().switchTo().alert().accept();
            return matches;
        } catch (Exception e) {
            String actualMsg = getText(dashboardMsg);
            return actualMsg.contains(expectedMsg);
        }
    }
}
