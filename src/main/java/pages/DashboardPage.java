package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private By headerBreadcrumb = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By userDropdown = By.cssSelector(".oxd-userdropdown");
    private By logoutLink = By.linkText("Logout");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignedIn() {
        return getElement(headerBreadcrumb).isDisplayed();
    }

    public String getBreadcrumbText() {
        return getElement(headerBreadcrumb).getText();
    }

    public void logout() {
        getElement(userDropdown).click();
        getElement(logoutLink).click();
    }
}