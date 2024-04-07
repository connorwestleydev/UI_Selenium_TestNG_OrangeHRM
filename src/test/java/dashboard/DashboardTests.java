package dashboard;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.DashboardPage;

import static org.testng.Assert.assertTrue;

public class DashboardTests extends BaseTests {

    @Test
    public void testEmployeeLeaveConfigChange() {
        loginPage.enterCredentials("admin", "admin123");
        DashboardPage dashboardPage = loginPage.clickLoginButton();

        dashboardPage.clickEmployeeLeavingCogIcon();
        dashboardPage.clickConfigModalSwitchToggle();
        dashboardPage.clickConfigModalSaveButton();

        assertTrue(dashboardPage.isSuccessNotificationDisplayed(), "Success notification not displayed");
    }

    @Test
    public void testHelpButtonOpensLinkInNewTab() {

    }

    @Test
    public void testEmployeeByDeptChartShowsTooltipOnHover() {

    }
}
