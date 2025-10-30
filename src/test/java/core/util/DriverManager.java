package core.util;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<RemoteWebDriver> TL = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void set(RemoteWebDriver driver) {
        TL.set(driver);
    }

    public static RemoteWebDriver get() {
        return TL.get();
    }

    public static void unload() {
        WebDriver driver = TL.get();
        if (driver != null) {
            driver.quit();
        }
        TL.remove();
    }
}