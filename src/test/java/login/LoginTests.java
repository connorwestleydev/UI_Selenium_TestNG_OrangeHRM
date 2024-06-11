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
    public Object[][] emptyLoginFieldsData() {
        return new Object[][] {
                {"",        "",},
                {"",        "admin123"},
                {"admin",   ""}
        };
    }

    @Test
    public void testSuccessfulLogin() {
        DashboardPage dashboardPage = loginPage.performLogin("admin", "admin123");
        assertTrue(dashboardPage.isSignedIn(), "Not signed in");
        assertEquals(dashboardPage.getBreadcrumbText(), "Dashboard", "Dashboard not displayed");
        dashboardPage.logout();
    }

    @Test (dataProvider = "emptyLoginFieldsData")
    public void testEmptyFieldsValidation(String username, String password) {
        // Determine number of empty fields provided
        int expectedEmptyFields = (int) Stream.of(username, password).filter(String::isEmpty).count();

        loginPage.performLogin(username, password);
        List<String> fieldValidationMessages = loginPage.getValidationMessages();

        assertEquals(fieldValidationMessages.size(), expectedEmptyFields, "Validation not triggered");
        fieldValidationMessages.forEach(i -> assertEquals(i, "Required", "Required text not present"));

        loginPage.clearFields();
    }

    @Test
    public void testInvalidCredentials() {
        loginPage.performLogin("username", "password");
        assertEquals(loginPage.getErrorMessage(), "Invalid credentials", "Error message not correct");
        loginPage.clearFields();
    }
}
