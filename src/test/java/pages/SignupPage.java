package pages;

import core.util.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignupPage extends BasePage {

    private final By loginSignupButton = By.xpath("//a[@href='/auth']");
    private final By usernameField = By.id("register-username");
    private final By emailField = By.id("register-email");
    private final By roleField = By.id("register-role");
    private final By specializationField = By.id("register-specialization");
    private final By passwordField = By.id("register-password");
    private final By confirmPasswordField = By.id("register-confirm-password");
    private final By signupButton = By.className("signup-btn");
    private final By createAccountButton = By.xpath("//button[normalize-space()='Create Account']");
    private final By title = By.xpath("//div[@class='form-title']");

    public SignupPage() {}

    // ✅ Verify if we're on the registration page
    public boolean isOnLandingPage() {
        String text = getText(title);
        return text.equalsIgnoreCase("Register");
    }

    // ✅ Enter username
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    // ✅ Enter email
    public void enterEmail(String email) {
        type(emailField, email);
    }

    // ✅ Select role from dropdown
    public void enterRole(String role) {
        selectByVisibleText(roleField, role);
    }

    // ✅ Handle specialization (optional dropdown)
    public void enterSpecialization(String specialization) {
        try {
            if (!webWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(specializationField)).isEmpty()) {
                selectByVisibleText(specializationField, specialization);
            } else {
                System.out.println("⚠️ Specialization field not present, skipping...");
            }
        } catch (Exception e) {
            System.out.println("❌ Error selecting specialization: " + e.getMessage());
        }
    }

    // ✅ Enter password
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    // ✅ Enter confirm password
    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    // ✅ Click Sign Up button
    public void clickSignupButton() {
        click(signupButton);
    }

    // ✅ Click Create Account button
    public void clickCreateAccountButton() {
        waitForElementToBeClickable(createAccountButton);
        click(createAccountButton);
    }
}
