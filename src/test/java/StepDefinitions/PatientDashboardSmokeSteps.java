package StepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.support.ui.Select;

public class PatientDashboardSmokeSteps {

    WebDriver driver;
    WebDriverWait wait;


    @Given("user is on the landing Pages")
    public void user_is_on_the_landing_page() {
        // ===== CHROME SETUP =====
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", chromePrefs);

        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:4200/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/auth']")));
    }

    @When("user clicks the login_signup buttons")
    public void user_clicks_the_login_signup_button() {
        driver.findElement(By.xpath("//a[@href='/auth']")).click();
    }

    @When("user enter usernam {string} and {string}")
    public void user_enter_username_and(String username, String password) {
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-title']"))
        );
        Assert.assertEquals("Not redirected to the login page", "Login", title.getText());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username"))).sendKeys(username);
        driver.findElement(By.id("login-password")).sendKeys(password);
    }
    @And("clicks the login buttons")
    public void clicks_the_login_buttons() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
    @Then("the user should see the messages {string}")
    public void the_user_should_see_the_message(String expectedMessage) {
        WebElement msg = driver.findElement(By.xpath("//h3[text()='Medical Consultation'] | //strong[text()='Status:']"));
        String actualMessage = msg.getText();
        Assert.assertEquals("Displayed message doesn't match!", expectedMessage, actualMessage);
    }

   @Given("user is on patient Dashboard with title {string}")
    public void user_is_on_patient_dashboard_with_title(String expectedMessage) {
       WebElement msg = driver.findElement(By.xpath("//h3[text()='Medical Consultation'] | //strong[text()='Status:']"));
       String actualMessage = msg.getText();
       Assert.assertEquals("Displayed message doesn't match!", expectedMessage, actualMessage);
    }

    @When("user clicks on feature card and redirects to consultation Form")
    public void user_redirects_to_consultation_Form() {
        driver.findElement(By.xpath("//div[@class='feature-card']")).click();
        String actualMessage = driver.findElement(By.xpath("//h3[text()='Medical Consultation Form']")).getText();
        String expectedMessage = "Medical Consultation Form";
        Assert.assertEquals("Displayed message doesn't match!", expectedMessage, actualMessage);
    }

    @Then("user fills the consultation form with {string}, {string}, {string}, {string}, and {string}" )
    public void user_fills_the_consultation_form(String fname , String age , String specialist , String level , String Description) {
        driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys(fname);
        driver.findElement(By.xpath("//input[@placeholder='Enter your age']")).sendKeys(age);
        WebElement doctype = driver.findElement(By.xpath("//select[@id='disease']"));
        Select select = new Select(doctype);

        select.selectByVisibleText(specialist);

        WebElement urgency = driver.findElement(By.xpath("//select[@id='severity']"));
        Select select2 = new Select(urgency);
        select2.selectByVisibleText(level);


        driver.findElement(By.xpath("//textArea[@id = 'description']")).sendKeys(Description);

    }

    @And("clicks the send button")
    public void clicks_the_send_button() {

        driver.findElement(By.cssSelector("button[type='submit']")).click();

    }

    @Then("successful pop-up should appear")
    public void successful_popup_should_appear() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Medical Consultation']")));
        String actualMessage = driver.findElement(By.xpath("//h3[text()='Medical Consultation']")).getText();
        String expectedMessage = "Medical Consultation";
        Assert.assertEquals("Displayed message doesn't match!", expectedMessage, actualMessage);


    }

}


