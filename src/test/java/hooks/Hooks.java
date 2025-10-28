package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Hooks {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void setup(){
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

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:4200/");
        driver.manage().window().maximize();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/auth']")));

    }

//    @After
//    public void tearDown(){
//
//        driver.quit();
//    }
}
