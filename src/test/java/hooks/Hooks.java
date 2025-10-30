package hooks;

import core.util.DriverFactory;
import core.util.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import core.util.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * PicoContainer will instantiate this class once per scenario,
 * making the WebDriver available to all Step Definition classes.
 */
public class Hooks {

    @Before
    public void setup() {
        RemoteWebDriver driver = DriverFactory.newDriver(Config.browser(), Config.headless());
        DriverManager.set(driver);
        DriverManager.get().manage().window().maximize();

        // Navigate to the base URL using Config
        DriverManager.get().get(Config.baseUrl());

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

    }

    @After
    public void teardown() {
        DriverManager.unload();
    }
}