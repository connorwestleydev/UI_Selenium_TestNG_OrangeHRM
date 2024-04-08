package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelpPage extends BasePage {

    private By adminGuideLink = By.linkText("Admin User Guide");

    public HelpPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAdminGuideLinkDisplayed() {
        return getElement(adminGuideLink).isDisplayed();
    }

    public DashboardPage switchToDashboardPageTab() {
        switchTab();

        DashboardPage dashboardPage = new DashboardPage(driver);
        if (dashboardPage.isSignedIn()) {
            return dashboardPage;
        }

        return null;
    }
}
