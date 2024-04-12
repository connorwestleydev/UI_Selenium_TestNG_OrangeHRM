package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

import static utils.RandomVariables.getRandomString;

public class BuzzPage extends BasePage {

    By buzzPostNavLink = By.linkText("Buzz");
    By newsfeedTitle = By.cssSelector(".orangehrm-buzz-newsfeed-title");
    By buzzPostTextArea = By.cssSelector(".oxd-buzz-post-input");
    By buzzPostButton = By.cssSelector(".oxd-buzz-post .oxd-button");
    By buzzPostsText = By.cssSelector(".orangehrm-buzz-post-body-text");
    By buzzPosts = By.cssSelector(".orangehrm-buzz-post");
    By buzzPostConfigButtons = By.cssSelector(".orangehrm-buzz-post-header-config .oxd-icon-button");
    By buzzPostConfigMenuItems = By.cssSelector(".orangehrm-buzz-post-header-config-item");
    By buzzPostEditTextArea = By.cssSelector(".orangehrm-buzz-post-modal-header-text .oxd-buzz-post-input");
    By buzzPostEditModalPostButton = By.cssSelector(".orangehrm-dialog-modal .oxd-button");
    By deleteModalButtons = By.cssSelector(".orangehrm-dialog-popup .oxd-button");

    public BuzzPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewsfeedTitleVisible() {
        return getElement(newsfeedTitle).isDisplayed();
    }

    public void clickBuzzPostNavLink() {
        clickElement(buzzPostNavLink);
    }

    public void setBuzzPostText(String text) {
        setText(buzzPostTextArea, text);
    }

    public void clickPostButton() {
        clickElement(buzzPostButton);
    }

    public void createBuzzPost(String postText) {
        setBuzzPostText(postText);
        clickPostButton();
    }

    public String createBuzzPostWithRandomText() {
        String randomString = getRandomString();
        createBuzzPost(randomString);
        return randomString;
    }

    /**
     * Gets text of specified buzz post
     * @param index starts at 1 (i.e. the first post)
     * @return body text of the buzz post
     */
    public String getBuzzPostText(int index) {
        List<WebElement> buzzPosts = getElements(buzzPostsText);
        return buzzPosts.get(index - 1).getText();
    }

    /**
     * Checks if given text is present in specified buzz post
     * @param index starts at 1 (i.e. the first post)
     * @param text to check if present in a post's body text
     * @return if text is present in the post
     */
    public boolean isTextPresentInBuzzPost(int index, String text) {
        return getBuzzPostText(1).contains(text);
    }

    /**
     * Edits buzz post with given index and adds text specified
     * @param index starts at 1 (i.e. the first post)
     * @param text the string to be added to the post
     */
    public void editBuzzPost(int index, String text) {
        if (isAtLeastOneBuzzPostPresent()) {
            clickBuzzPostConfigButton(index - 1);
            clickElementFromListByText(buzzPostConfigMenuItems, "Edit Post");
            setEditPostText(text);
            clickModalPostButton();
        } else {
            throw new NoSuchElementException("No buzz posts are present");
        }
    }

    public void deleteBuzzPost(int index, String modalOption) {
        if (isAtLeastOneBuzzPostPresent()) {
            clickBuzzPostConfigButton(index - 1);
            clickElementFromListByText(buzzPostConfigMenuItems, "Delete Post");
            clickElementFromListByText(deleteModalButtons, modalOption);
        } else {
            throw new NoSuchElementException("No buzz posts are present");
        }
    }

    public boolean isAtLeastOneBuzzPostPresent() {
        return getElements(buzzPosts).size() > 0;
    }

    public void clickBuzzPostConfigButton(int index) {
        WebElement configButton = getElements(buzzPostConfigButtons).get(index);
        configButton.click();
    }

    public void setEditPostText(String text) {
        setText(buzzPostEditTextArea, text);
    }

    public void clickModalPostButton() {
        clickElement(buzzPostEditModalPostButton);
    }
}
