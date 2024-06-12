package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public class PersonalDetailsPage extends BasePage {

    private By nationalityDropDown = By.xpath("//div[child::label[text()='Nationality']]//following-sibling::div");
    private By selectedNationality = By.xpath("//div[child::label[text()='Nationality']]//following-sibling::div//div[@class='oxd-select-text-input']");
    private By dropdown = By.cssSelector(".oxd-select-dropdown");
    private By dropdownItems = By.cssSelector(".oxd-select-dropdown .oxd-select-option");
    private By contactDetailsLink = By.linkText("Contact Details");


    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void clickNationalityDropDown() {
        clickElement(nationalityDropDown);
    }

    public void waitUntilDropdownDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(dropdown));
    }

    public void selectNationality(String desiredNationality) {
        waitUntilDropdownDisplayed();
        clickElementFromListByText(dropdownItems, desiredNationality);
    }

    public String getSelectedNationality() {
        return getElement(selectedNationality).getText();
    }
}
