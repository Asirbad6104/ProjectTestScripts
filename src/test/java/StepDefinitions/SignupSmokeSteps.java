package StepDefinitions;

import core.util.BasePage;
import core.util.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.SignupPage;

import java.util.Objects;


public class SignupSmokeSteps extends BasePage {
        LoginPage loginPage;
        SignupPage signupPage;

        @Given("user is on the landing Page1")
        public void user_is_on_the_landing_page1() {
            loginPage = new LoginPage();
            signupPage = new SignupPage();
        }

        @When("user clicks the login_signup button sign_up")
        public void user_clicks_the_login_signup_button1() {
            loginPage.click_loginSignUpButton();
        }

        @Then("user clicks on the Sign_Up button")
        public void user_clicks_on_the_sign_up_button(){signupPage.clicks_signupButton();}

        @Then("user should redirected to the Sign_Up page")
        public void userShouldRedirectedToTheSign_UpPage() {
            // Write code here that turns the phrase above into concrete actions
            Assert.assertTrue(signupPage.isOnLandingPage());
        }


        @When("the user enters a valid {string} in the Username field")
        public void user_enter_username_and(String username) {
            webWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("register-username")));
            signupPage.enterUsername(username);
        }

        @And("enters {string} in the Email field")
        public void enters_in_the_Email_field(String email){
            webWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("register-email")));
            signupPage.enterEmail(email);
        }

        @And("selects a role {string} from the dropdown")
        public void selects_a_role_from_the_dropdown(String role){
            webWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("register-role")));
            signupPage.enterRole(role);
        }

        @And("handles specialization {string}")
        public void handles_specialization(String specialization){
            webWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("register-specialization")));
            if (specialization != null && !specialization.trim().isEmpty()) {
                try {
                    signupPage.enterSpecialization(specialization);
                    System.out.println("Selected specialization: " + specialization);
                } catch (Exception e) {
                    System.out.println("Skipping specialization due to error: " + e.getMessage());
                }
            } else {
                System.out.println("No specialization required for this role. Skipping interaction.");
            }
        }

        @And("enters a secure {string} in the Password field")
        public void enters_a_secure_in_the_Password_field(String password){
            webWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("register-password")));
            signupPage.enterPassword(password);
        }

        @And("enters the same {string} in the Confirm Password field")
        public void enters_the_same_in_the_Confirm_Password_field(String password){
            webWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("register-confirm-password")));
            signupPage.enterConfirmPassword(password);
        }

        @And("clicks the Create Account Button")
        public void clicks_the_Create_Account_Button(){
            signupPage.clicks_createAccountButton();
            System.out.println("Current URL after registration: " + DriverManager.get().getCurrentUrl());
        }


        @Then("the user should redirected to the LoginPage1")
        public void the_user_should_redirected_to_the_loginPage1() {
            // Write code here that turns the phrase above into concrete actions
//            webWait().until(ExpectedConditions.visibilityOfElementLocated());
            Assert.assertTrue(DriverManager.get().getCurrentUrl().contains("auth"));
        }


    }

