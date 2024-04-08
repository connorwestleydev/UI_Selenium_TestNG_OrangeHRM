package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> getElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected String getText(By locator) {
        return getElement(locator).getText();
    }

    protected String getValue(By locator) {
        return getElement(locator).getAttribute("value");
    }

    protected void enterText(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    protected void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void clearField(By locator) {
        while(!getValue(locator).isEmpty()) {
            getElement(locator).sendKeys(Keys.BACK_SPACE);
        }
    }

    public int getTabCount() {
        return driver.getWindowHandles().size();
    }

    /**
     * Switches to the first tab that isn't the current tab
     */
    protected void switchTab() {
        String originalTab = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalTab.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }
}
