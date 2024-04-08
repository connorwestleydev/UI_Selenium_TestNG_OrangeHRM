package myInfo;

import base.BaseTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.PersonalDetailsPage;

import static org.testng.Assert.assertEquals;

public class PersonalDetailsTests extends BaseTests {

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
    public void testNationalitySelection() {
        PersonalDetailsPage personalDetailsPage = dashboardPage.navigateToPersonalDetailsPage();
        personalDetailsPage.clickNationalityDropDown();
        String nationality = "British";
        personalDetailsPage.selectNationality(nationality);
        assertEquals(personalDetailsPage.getSelectedNationality(), nationality, "Selected nationality is incorrect");
    }
}
