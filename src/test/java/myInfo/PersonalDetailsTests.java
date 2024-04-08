package myInfo;

import base.BaseTests;
import base.BaseTests_LoginLogout;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.PersonalDetailsPage;

import static org.testng.Assert.assertEquals;

public class PersonalDetailsTests extends BaseTests_LoginLogout {

    @Test
    public void testNationalitySelection() {
        PersonalDetailsPage personalDetailsPage = dashboardPage.navigateToPersonalDetailsPage();
        personalDetailsPage.clickNationalityDropDown();
        String nationality = "British";
        personalDetailsPage.selectNationality(nationality);
        assertEquals(personalDetailsPage.getSelectedNationality(), nationality, "Selected nationality is incorrect");
    }
}
