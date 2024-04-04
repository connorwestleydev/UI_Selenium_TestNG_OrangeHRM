package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameField = By.cssSelector("[name=username]");
    private By passwordField = By.cssSelector("[name=password]");
    private By loginButton = By.cssSelector("[type=submit]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public DashboardPage clickLoginButton() {
        clickElement(loginButton);
        return new DashboardPage(driver);
    }
}