package StepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginSmokeSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("user is on the landing Page")
    public void user_is_on_the_landing_page() {
        // ===== CHROME SETUP =====
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();

        // Disable Chrome "change your password" popup and password manager
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", chromePrefs);

        // Optional: disable Chrome info bars and extensions
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");

        // Initialize ChromeDriver with options ✅
        driver = new ChromeDriver(options);

        // ===== NAVIGATE =====
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:4200/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/auth']")));
    }

    @When("user clicks the login_signup button")
    public void user_clicks_the_login_signup_button() {
        driver.findElement(By.xpath("//a[@href='/auth']")).click();
    }

    @Then("user should redirected to the login page")
    public void user_should_redirected_to_the_login_page() {
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-title']"))
        );
        Assert.assertEquals("Not redirected to the login page", "Login", title.getText());
    }

    @When("user enter username {string} and {string}")
    public void user_enter_username_and(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username"))).sendKeys(username);
        driver.findElement(By.id("login-password")).sendKeys(password);
    }

    @And("clicks the login button")
    public void clicks_the_login_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("the user should see the message {string}")
    public void the_user_should_see_the_message(String expectedMessage) {
        try {
            // STEP 1️⃣: Check if an alert is open first
            if (isAlertPresent()) {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();


                // Verify the alert message
                Assert.assertTrue(
                        "Invalid login alert text mismatch!",
                        alertText.toLowerCase().contains(expectedMessage.toLowerCase())
                );

                alert.accept();

            }
            else {
                // STEP 2️⃣: No alert -> check for dashboard message (valid login)
                WebElement msg = driver.findElement(By.xpath("//h3[text()='Medical Consultation'] | //strong[text()='Status:']"));
                String actualMessage = msg.getText();
                Assert.assertEquals("Displayed message doesn't match!", expectedMessage, actualMessage);

            }
        } catch (UnhandledAlertException e) {
            // Safety fallback: if the alert appears mid-way
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();

                Assert.assertTrue(alertText.contains(expectedMessage));
                alert.accept();
            } catch (NoAlertPresentException ignored) {}
        } finally {
            driver.quit();
        }
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
