package login;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.DashboardPage;

import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTests {

    @Test
    public void testSuccessfulLogin() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        DashboardPage dashboardPage = loginPage.clickLoginButton();
        assertEquals(dashboardPage.getActiveMenuText(), "Dashboard", "Dashboard page not selected");
    }
}
