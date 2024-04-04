package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private By activeMenuItem = By.cssSelector("ul.oxd-main-menu .active");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getActiveMenuText() {
        return getText(activeMenuItem);
    }
}