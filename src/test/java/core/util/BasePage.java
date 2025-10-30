package core.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriverWait webWait() {
        return new WebDriverWait(DriverManager.get(), Duration.ofSeconds(Config.explicitWaitSec()));
    }

    protected void isVisibleElement(WebElement element) {
        webWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element) {
        webWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void navigateTo(String url) {
        DriverManager.get().get(url);
    }
}