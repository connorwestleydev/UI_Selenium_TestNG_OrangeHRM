package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private By headerBreadcrumb = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private By userDropdown = By.cssSelector(".oxd-userdropdown");
    private By logoutLink = By.linkText("Logout");
    private By helpButton = By.cssSelector(".oxd-topbar-body-nav-slot .oxd-icon-button");
    private By employeeLeaveCogIcon = By.cssSelector(".orangehrm-leave-card-icon");
    private By configModalSwitchToggle = By.cssSelector(".orangehrm-dialog-modal .oxd-switch-input");
    private By configModalSaveButton = By.cssSelector(".orangehrm-dialog-modal button[type=submit]");
    private By successNotification = By.cssSelector("#oxd-toaster_1 .oxd-toast--success");

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

    public void clickEmployeeLeavingCogIcon() {
        clickElement(employeeLeaveCogIcon);
    }

    public void clickConfigModalSwitchToggle() {
        clickElement(configModalSwitchToggle);
    }

    public void clickConfigModalSaveButton() {
        clickElement(configModalSaveButton);
    }

    public boolean isSuccessNotificationDisplayed() {
        return getElement(successNotification).isDisplayed();
    }

    public void clickHelpButton() {
        clickElement(helpButton);
    }

    public HelpPage switchToHelpPageTab() {
        switchTab();

        HelpPage helpPage = new HelpPage(driver);
        if (helpPage.isAdminGuideLinkDisplayed()) {
            return helpPage;
        }

        return null;
    }
}