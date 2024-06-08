package login;

import base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;

import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"Admin",   "admin123", "Successful login"},
                {"",        "",         "Empty credentials fields"},
                {"",        "admin123", "Empty username"},
                {"admin",   "",         "Empty password"},
                {"test",    "test",     "Invalid credentials"}
        };
    }

    @Test (dataProvider = "loginData")
    public void testLoginScenarios(String username, String password, String scenario) {
        loginPage.enterCredentials(username, password);
        DashboardPage dashboardPage = loginPage.clickLoginButton();

        switch (scenario) {
            case "Successful login":
                verifySuccessfulLogin(dashboardPage);
                dashboardPage.logout();
                break;
            case "Empty credentials fields":
            case "Empty username":
            case "Empty password":
                verifyEmptyFields(username, password);
                break;
            case "Invalid credentials":
                verifyInvalidCredentials();
                break;
            default:
                throw new IllegalArgumentException("Unexpected scenario: " + scenario);
        }

        if (!scenario.equals("Successful login")) {loginPage.clearFields();}
    }

    private void verifySuccessfulLogin(DashboardPage dashboardPage) {
        assertTrue(dashboardPage.isSignedIn(), "Not signed in");
        assertEquals(dashboardPage.getBreadcrumbText(), "Dashboard", "Dashboard not displayed");
    }

    private void verifyInvalidCredentials() {
        assertEquals(loginPage.getErrorMessage(), "Invalid credentials", "Error message not correct");
    }

    private void verifyEmptyFields(String username, String password) {
        int expectedEmptyFields = (int) Stream.of(username, password).filter(String::isEmpty).count();
        List<String> fieldValidationMessages = loginPage.getValidationMessages();

        assertEquals(fieldValidationMessages.size(), expectedEmptyFields, "Validation not triggered");
        fieldValidationMessages.forEach(i -> assertEquals(i, "Required", "Required text not present"));
    }
}
