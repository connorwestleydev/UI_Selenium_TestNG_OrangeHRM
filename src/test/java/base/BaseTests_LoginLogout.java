package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;

public class BaseTests_LoginLogout extends BaseTests {

    protected DashboardPage dashboardPage;

    @BeforeMethod
    public void login() {
        this.loginPage.enterCredentials("admin", "admin123");
        this.dashboardPage = loginPage.clickLoginButton();
    }

    @AfterMethod
    public void logout() {
        dashboardPage.logout();
    }
}