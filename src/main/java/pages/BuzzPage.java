package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import static utils.RandomVariables.getRandomString;

public class BuzzPage extends BasePage {

    private By buzzNavLink = By.linkText("Buzz");
    private By newsfeedTitle = By.cssSelector(".orangehrm-buzz-newsfeed-title");
    private By postTextArea = By.cssSelector(".oxd-buzz-post-input");
    private By postButton = By.cssSelector(".oxd-buzz-post .oxd-button");
    private By postsText = By.cssSelector(".orangehrm-buzz-post-body-text");
    private By postsImage = By.cssSelector(".orangehrm-buzz-photos");
    private By posts = By.cssSelector(".orangehrm-buzz");
    private By postConfigButtons = By.cssSelector(".orangehrm-buzz-post-header-config .oxd-icon-button");
    private By postConfigMenuItems = By.cssSelector(".orangehrm-buzz-post-header-config-item");
    private By editTextArea = By.cssSelector(".orangehrm-buzz-post-modal-header-text .oxd-buzz-post-input");
    private By editModalPostButton = By.cssSelector(".orangehrm-buzz-post-modal-actions .oxd-button");
    private By deleteModalButtons = By.cssSelector(".orangehrm-dialog-popup .oxd-button");
    private By shareButtons = By.cssSelector(".oxd-glass-button");
    private By fileInputField = By.cssSelector(".oxd-file-input");

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

    public String createBuzzPostWithRandomText() {
        String randomString = getRandomString();
        createBuzzPost(randomString);
        return randomString;
    }

    public void createBuzzPostWithPhoto(String relativePath) {
        clickElementFromListByText(shareButtons, "Photos");
        uploadFile(fileInputField, relativePath);
        clickModalPostButton();
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
     * Checks if specified buzz post has an image element
     * @param index starts at 1 (i.e. the first post)
     * @return if post has an image element
     */
    public boolean isImagePresentInBuzzPost(int index) {
        WebElement post = getElements(posts).get(index - 1);
        List<WebElement> images = post.findElements(postsImage);
        return !images.isEmpty();
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

    /**
     * Edits buzz post by adding randomly generated text
     * @param index starts at 1 (i.e. the first post)
     * @return randomly generated text added to post
     */
    public String editBuzzPostAddingRandomText(int index) {
        String randomString = getRandomString();
        editBuzzPost(index, randomString);
        return randomString;
    }

    /**
     * Selects delete option on modal config dropdown and then clicks the
     * specified modal option (either cancel or delete)
     * @param index starts at 1 (i.e. the first post)
     * @param modalOption the option to click (cancel or delete)
     */
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
