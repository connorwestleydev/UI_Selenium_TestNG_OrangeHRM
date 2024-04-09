package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage extends BasePage {

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("[type=submit]");
    private By validationMessages = By.cssSelector("form .oxd-text--span");
    private By errorMessage = By.cssSelector(".oxd-alert--error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterCredentials(String username, String password) {
        enterUsername(username);
        enterPassword(password);
    }

    private void enterUsername(String username) {
        setText(usernameField, username);
    }

    private void enterPassword(String password) {
        setText(passwordField, password);
    }

    public DashboardPage clickLoginButton() {
        clickElement(loginButton);
        return new DashboardPage(driver);
    }

    public void clearFields() {
        clearField(usernameField);
        clearField(passwordField);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public List<String> getValidationMessages() {
        List<WebElement> validationMessageElements = driver.findElements(validationMessages);
        return validationMessageElements.stream().map(e -> e.getText()).collect(Collectors.toList());
    }
}