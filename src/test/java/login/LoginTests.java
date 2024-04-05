package login;

import base.BaseTests;
import jdk.jfr.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"Admin",   "admin123", 0, "Successful login"},
                {"",        "",         2, "Empty credentials fields"},
                {"",        "admin123", 1, "Empty username"},
                {"admin",   "",         1, "Empty password"},
                {"test",    "test",     0, "Invalid credentials"}
        };
    }

    @Test (dataProvider = "loginData")
    public void testLoginScenarios(String username, String password, int emptyFields, String scenario) {
        loginPage.enterCredentials(username, password);
        DashboardPage dashboardPage = loginPage.clickLoginButton();

        switch (scenario) {
            case "Successful login":
                assertTrue(dashboardPage.isSignedIn(), "Not signed in");
                assertEquals(dashboardPage.getBreadcrumbText(), "Dashboard", "Dashboard not displayed");
                dashboardPage.logout();
                break;
            case "Empty credentials fields":
            case "Empty username":
            case "Empty password":
                List<String> fieldValidationMessages = loginPage.getValidationMessages();
                assertTrue(fieldValidationMessages.size() == emptyFields, "Validation not triggered");
                fieldValidationMessages.forEach(i -> assertEquals(i, "Required", "Required text not present"));
                break;
            case "Invalid credentials":
                assertEquals(loginPage.getErrorMessage(), "Invalid credentials", "Error message not correct");
        }

        if (!scenario.equals("Successful login")) {loginPage.clearFields();}
    }
}
