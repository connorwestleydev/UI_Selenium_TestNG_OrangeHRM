package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void enterText(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return getElement(locator).getText();
    }

    public void clickElement(By locator) {
        getElement(locator).click();
    }
}
