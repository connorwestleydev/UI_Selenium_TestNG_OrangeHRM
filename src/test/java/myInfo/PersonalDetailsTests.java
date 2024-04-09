package myInfo;

import base.BaseTests_LoginLogout;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PersonalDetailsTests extends BaseTests_LoginLogout {

    @Test
    public void testNationalitySelection() {
        var personalDetailsPage = dashboardPage.navigateToPersonalDetailsPage();
        personalDetailsPage.clickNationalityDropDown();
        String nationality = "British";
        personalDetailsPage.selectNationality(nationality);
        assertEquals(personalDetailsPage.getSelectedNationality(), nationality, "Selected nationality is incorrect");
    }
}
