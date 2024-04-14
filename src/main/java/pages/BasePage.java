package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private By successNotification = By.cssSelector("#oxd-toaster_1 .oxd-toast--success");

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

    protected void setText(By locator, String text) {
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

    public boolean isSuccessNotificationDisplayed() {
        return getElement(successNotification).isDisplayed();
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

    /**
     * Clicks the first element from a list of elements with the specified text
     * @param locator By variable to locate the matching elements
     * @param text The text to look for within the elements
     */
    protected void clickElementFromListByText(By locator, String text) {
        List<WebElement> elements = getElements(locator);

        for (WebElement element : elements) {
            if (element.getText().contains(text)) {
                element.click();
                return;
            }
        }

        throw new NoSuchElementException("Element with the text " + text + " was not found");
    }

    public void uploadFile(By uploadField, String relativePath) {
        String filePath = new File(relativePath).getAbsolutePath();
        driver.findElement(uploadField).sendKeys(filePath);
    }
}
