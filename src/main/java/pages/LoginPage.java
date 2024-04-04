package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private By usernameField = By.cssSelector("[name=username]");
    private By passwordField = By.cssSelector("[name=password]");
    private By loginButton = By.cssSelector("[type=submit]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        waitForPageLoad();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public DashboardPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new DashboardPage(driver);
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
    }
}
