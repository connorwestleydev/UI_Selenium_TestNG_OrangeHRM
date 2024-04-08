package dashboard;

import base.BaseTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BuzzPage;
import pages.DashboardPage;
import pages.HelpPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DashboardTests extends BaseTests {

    private DashboardPage dashboardPage;

    @BeforeMethod
    public void login() {
        this.loginPage.enterCredentials("admin", "admin123");
        this.dashboardPage = loginPage.clickLoginButton();
    }

    @AfterMethod
    public void logout() {
        dashboardPage.logout();
    }

    @Test
    public void testEmployeeLeaveConfigChange() {
        dashboardPage.clickEmployeeLeavingCogIcon();
        dashboardPage.clickConfigModalSwitchToggle();
        dashboardPage.clickConfigModalSaveButton();
        assertTrue(dashboardPage.isSuccessNotificationDisplayed(), "Success notification not displayed");
    }

    @Test
    public void testHelpButtonOpensLinkInNewTab() {
        dashboardPage.clickHelpButton();
        assertEquals(dashboardPage.getTabCount(), 2,"Two tabs are not open");
        HelpPage helpPage = dashboardPage.switchToHelpPageTab();
        assertTrue(helpPage.isAdminGuideLinkDisplayed(), "Admin Guide link is not visible");
        dashboardPage = helpPage.switchToDashboardPageTab();
    }

    @Test
    public void testClickingBuzzPostNavigatesToBuzzPage() {
        BuzzPage buzzPage = dashboardPage.clickBuzzPost(1);
        assertTrue(buzzPage.isNewsfeedTitleVisible(), "Newsfeed is not displayed");
    }
}
