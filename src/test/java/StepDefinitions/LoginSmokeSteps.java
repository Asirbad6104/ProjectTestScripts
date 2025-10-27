package StepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;

import static hooks.Hooks.driver;
import static hooks.Hooks.wait;

public class LoginSmokeSteps {
    LoginPage loginPage;

    @Given("user is on the landing Page")
    public void user_is_on_the_landing_page() {

        loginPage = new LoginPage(driver);

       }

    @When("user clicks the login_signup button")
    public void user_clicks_the_login_signup_button() {

        loginPage.click_loginSignUpButton();

    }
    @Then("user should redirected to the login page")
    public void user_should_redirected_to_the_login_page() {

        Assert.assertTrue(loginPage.isOnLandingPage());

        }

    @When("user enter username {string} and {string}")
    public void user_enter_username_and(String username, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username")));
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("clicks the login button")
    public void clicks_the_login_button() {

       loginPage.clickLoginButton();
    }

    @Then("the user should see the message {string}")
    public void the_user_should_see_the_message(String expectedMessage) {

        Assert.assertTrue(loginPage.verifyDashboard(expectedMessage));
    }



}
