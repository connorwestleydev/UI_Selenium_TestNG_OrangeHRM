package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public class BuzzPage extends BasePage {

    private By buzzNavLink = By.linkText("Buzz");
    private By newsfeedTitle = By.cssSelector(".orangehrm-buzz-newsfeed-title");
    private By postTextArea = By.cssSelector(".oxd-buzz-post-input");
    private By postButton = By.cssSelector(".oxd-buzz-post .oxd-button");
    private By postsText = By.cssSelector(".orangehrm-buzz-post-body-text");
    private By posts = By.cssSelector(".orangehrm-buzz-post");
    private By postConfigButtons = By.cssSelector(".orangehrm-buzz-post-header-config .oxd-icon-button");
    private By postConfigMenuItems = By.cssSelector(".orangehrm-buzz-post-header-config-item");
    private By editTextArea = By.cssSelector(".orangehrm-buzz-post-modal-header-text .oxd-buzz-post-input");
    private By editModalPostButton = By.cssSelector(".orangehrm-dialog-modal .oxd-button");
    private By deleteModalButtons = By.cssSelector(".orangehrm-dialog-popup .oxd-button");

    public BuzzPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewsfeedTitleVisible() {
        return getElement(newsfeedTitle).isDisplayed();
    }

    public void clickBuzzPostNavLink() {
        clickElement(buzzNavLink);
    }

    public void setBuzzPostText(String text) {
        setText(postTextArea, text);
    }

    public void clickPostButton() {
        clickElement(postButton);
    }

    public void createBuzzPost(String postText) {
        setBuzzPostText(postText);
        clickPostButton();
    }

    /**
     * Gets text of specified buzz post
     * @param index starts at 1 (i.e. the first post)
     * @return body text of the buzz post
     */
    public String getBuzzPostText(int index) {
        List<WebElement> buzzPosts = getElements(postsText);
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
            clickElementFromListByText(postConfigMenuItems, "Edit Post");
            setEditPostText(text);
            clickModalPostButton();
        } else {
            throw new NoSuchElementException("No buzz posts are present");
        }
    }

    public void deleteBuzzPost(int index, String modalOption) {
        if (isAtLeastOneBuzzPostPresent()) {
            clickBuzzPostConfigButton(index - 1);
            clickElementFromListByText(postConfigMenuItems, "Delete Post");
            clickElementFromListByText(deleteModalButtons, modalOption);
        } else {
            throw new NoSuchElementException("No buzz posts are present");
        }
    }

    public boolean isAtLeastOneBuzzPostPresent() {
        return getElements(posts).size() > 0;
    }

    public void clickBuzzPostConfigButton(int index) {
        WebElement configButton = getElements(postConfigButtons).get(index);
        configButton.click();
    }

    public void setEditPostText(String text) {
        setText(editTextArea, text);
    }

    public void clickModalPostButton() {
        clickElement(editModalPostButton);
    }
}
