package login;

import base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {

    @DataProvider
    public Object[][] loginData() {
        Object[][] loginData = new Object[1][2];

        loginData[0][0] = "Admin";  loginData[0][1] = "admin123";

        return loginData;
    }

    @Test (dataProvider = "loginData")
    public void testSuccessfulLogin(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        DashboardPage dashboardPage = loginPage.clickLoginButton();
        assertTrue(dashboardPage.getPageURL().contains("dashboard"), "Not redirected to dashboard page");
    }
}
