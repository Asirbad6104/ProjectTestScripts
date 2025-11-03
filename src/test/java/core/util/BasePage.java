package core.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {

    // ✅ Centralized wait
    protected WebDriverWait webWait() {
        return new WebDriverWait(DriverManager.get(), Duration.ofSeconds(Config.explicitWaitSec()));
    }

    // ✅ Wait for visibility
    protected WebElement waitForVisibility(By locator) {
        return webWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ✅ Wait for clickable
    protected WebElement waitForElementToBeClickable(By locator) {
        return webWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ✅ Click using locator
    protected void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    // ✅ Type into a field
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // ✅ Get visible text
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    // ✅ Dropdown selection
    protected void selectByVisibleText(By locator, String visibleText) {
        WebElement dropdown = waitForVisibility(locator);
        new Select(dropdown).selectByVisibleText(visibleText);
    }

    // ✅ Accept alert if present
    protected void acceptAlertIfPresent() {
        try {
            WebDriverWait alertWait = new WebDriverWait(DriverManager.get(), Duration.ofSeconds(5));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException ignored) {
            // No alert
        }
    }

    // ✅ Navigate to URL
    protected void navigateTo(String url) {
        DriverManager.get().get(url);
    }

    // ✅ Optional: For backward compatibility
    protected void isVisibleElement(WebElement element) {
        webWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element) {
        webWait().until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}
