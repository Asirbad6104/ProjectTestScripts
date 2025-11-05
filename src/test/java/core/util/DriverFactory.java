package core.util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
    private DriverFactory(){}

    public static RemoteWebDriver newDriver(String browser, boolean headless, ChromeOptions options) {
        if (browser.equalsIgnoreCase("chrome")) {
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080"); // Helps with rendering
                options.addArguments("--disable-gpu"); // Optional, useful on Linux
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            return new ChromeDriver(options);
        }

        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }


    private static RemoteWebDriver createChrome(boolean headless){
        ChromeOptions opts = new ChromeOptions();
        if (System.getenv("HEADFUL")==null) {
            opts.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage");
        }
        return new ChromeDriver(opts);
    }

    private static RemoteWebDriver createFirefox(boolean headless){
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("-headless");
        return new RemoteWebDriver(options);
    }

    private static RemoteWebDriver createEdge(boolean headless){
        EdgeOptions options = new EdgeOptions();
        if (headless) options.addArguments("--headless=new");
        return new RemoteWebDriver(options);
    }
}