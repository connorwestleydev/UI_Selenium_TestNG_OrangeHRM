package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;
    private By activeMenuItem = By.cssSelector("ul.oxd-main-menu .active");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        waitForPageLoad();
    }

    public String getActiveMenuText() {
        return driver.findElement(activeMenuItem).getText();
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(activeMenuItem));
    }
}
