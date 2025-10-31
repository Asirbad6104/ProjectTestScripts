package pages;

import core.util.BasePage;
import core.util.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {
    private By login_signup_button = By.xpath("//a[@href='/auth']");
    private By usernameField = By.id("register-username");
    private By emailField = By.xpath("//input[@id='register-email']");
    private By roleField = By.id("register-role");
    private By specializationField = By.xpath("//select[@id='register-specialization']");
    private By passwordField = By.id("register-password");
    private By confirmPasswordField = By.id("register-confirm-password");
    private By signup_button = By.className("signup-btn");
    private By create_account_button = By.xpath("//button[text()=' Create Account ']");
    private By title  = By.xpath("//div[@class='form-title']");

    public SignupPage(){
    }

    public boolean isOnLandingPage(){
        webWait().until(ExpectedConditions.visibilityOfElementLocated(title));
        return DriverManager.get().findElement(title).getText().equals("Register");
    }

    public void enterUsername(String username){
        webWait().until(ExpectedConditions.elementToBeClickable(usernameField));
        DriverManager.get().findElement(usernameField).sendKeys(username);
    }

    public void enterEmail(String email){
        webWait().until(ExpectedConditions.elementToBeClickable(emailField));
        DriverManager.get().findElement(emailField).sendKeys(email);
    }

    public void enterRole(String role){
        webWait().until(ExpectedConditions.elementToBeClickable(roleField));
        Select select = new Select(DriverManager.get().findElement(roleField));
        select.selectByVisibleText(role);
    }

    public void enterSpecialization(String specialization){
        try {
            if (!DriverManager.get().findElements(specializationField).isEmpty()) {
                webWait().until(ExpectedConditions.elementToBeClickable(specializationField));
                Select select = new Select(DriverManager.get().findElement(specializationField));
                select.selectByVisibleText(specialization);
            } else {
                System.out.println("Specialization field not present. Skipping.");
            }
        } catch (Exception e) {
            System.out.println("Error selecting specialization: " + e.getMessage());
        }
    }



    public void enterPassword(String password){
        webWait().until(ExpectedConditions.elementToBeClickable(passwordField));
        DriverManager.get().findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword){
        webWait().until(ExpectedConditions.elementToBeClickable(confirmPasswordField));
        DriverManager.get().findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clicks_signupButton() {
        click(DriverManager.get().findElement(signup_button));
    }

    public void clicks_createAccountButton() {
        webWait().until(ExpectedConditions.elementToBeClickable(create_account_button));
        click(DriverManager.get().findElement(create_account_button));
    }

}
