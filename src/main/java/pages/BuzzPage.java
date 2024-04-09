package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BuzzPage extends BasePage {

    By buzzPostNavLink = By.linkText("Buzz");
    By newsfeedTitle = By.cssSelector(".orangehrm-buzz-newsfeed-title");
    By buzzPostTextArea = By.cssSelector(".oxd-buzz-post-input");
    By buzzPostButton = By.cssSelector(".oxd-buzz-post .oxd-button");
    By buzzPostText = By.cssSelector(".orangehrm-buzz-post-body-text");

    public BuzzPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewsfeedTitleVisible() {
        return getElement(newsfeedTitle).isDisplayed();
    }

    public void clickBuzzPostNavLink() {
        getElement(buzzPostNavLink).click();
    }

    public void setBuzzPostText(String text) {
        getElement(buzzPostTextArea).sendKeys(text);
    }

    public void clickPostButton() {
        getElement(buzzPostButton).click();
    }

    public void createBuzzPost(String postText) {
        setBuzzPostText(postText);
        clickPostButton();
    }

    public String getFirstBuzzPostText() {
        List<WebElement> buzzPostsText = getElements(buzzPostText);
        return buzzPostsText.get(0).getText();
    }
}
