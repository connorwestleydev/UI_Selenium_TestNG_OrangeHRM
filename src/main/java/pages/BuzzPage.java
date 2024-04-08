package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuzzPage extends BasePage {

    By newsfeedTitle = By.cssSelector(".orangehrm-buzz-newsfeed-title");

    public BuzzPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewsfeedTitleVisible() {
        return getElement(newsfeedTitle).isDisplayed();
    }
}
