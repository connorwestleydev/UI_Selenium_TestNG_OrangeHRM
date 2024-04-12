package buzz;

import base.BaseTests_LoginLogout;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BuzzPage;

import static org.testng.Assert.*;
import static utils.RandomVariables.getRandomString;


public class BuzzTests extends BaseTests_LoginLogout {

    private BuzzPage buzzPage;

    @BeforeMethod
    public void setUpBuzzPage() {
        buzzPage = dashboardPage.clickBuzzPost(1);
    }

    @Test
    public void testBuzzPostWithTextOnlyIsSuccessful() {
        buzzPage.createBuzzPostWithRandomText();
        assertTrue(buzzPage.isSuccessNotificationDisplayed(), "Post not successful");
    }

    @Test
    public void testNewBuzzPostAppearsInFeed() {
        String postText = buzzPage.createBuzzPostWithRandomText();
        buzzPage.clickBuzzPostNavLink();
        assertEquals(buzzPage.getBuzzPostText(1), postText, "First post text is not correct");
    }

    @Test
    public void testEditTextOfNewBuzzPostIsSuccessful() {
        buzzPage.createBuzzPostWithRandomText();
        buzzPage.clickBuzzPostNavLink();

        String newText = getRandomString();
        buzzPage.editBuzzPost(1, newText);

        assertTrue(buzzPage.isSuccessNotificationDisplayed(), "Edit post not successful");
    }

    @Test
    public void testEditTextOfNewBuzzPostAppearsInFeed() {
        buzzPage.createBuzzPostWithRandomText();
        buzzPage.clickBuzzPostNavLink();

        int firstPost = 1;
        String newText = getRandomString();
        buzzPage.editBuzzPost(firstPost, newText);

        assertTrue(buzzPage.isTextPresentInBuzzPost(firstPost, newText),
                "Edited post does not contain new text");
    }

    @Test
    public void testDeletionOfNewBuzzPostIsSuccessful() {
        buzzPage.createBuzzPostWithRandomText();
        buzzPage.clickBuzzPostNavLink();

        buzzPage.deleteBuzzPost(1, "Delete");
        assertTrue(buzzPage.isSuccessNotificationDisplayed(), "Delete post not successful");
    }

    @Test
    public void testDeletedNewPostIsNotPresentInFeed() {
        String text = buzzPage.createBuzzPostWithRandomText();
        buzzPage.clickBuzzPostNavLink();

        int firstPost = 1;
        buzzPage.deleteBuzzPost(firstPost, "Delete");
        buzzPage.clickBuzzPostNavLink();
        assertFalse(buzzPage.isTextPresentInBuzzPost(firstPost, text) , "Deleted post is still present");
    }

    @Test
    public void testCancelDeletionKeepsPostInFeed() {
        String text = buzzPage.createBuzzPostWithRandomText();
        buzzPage.clickBuzzPostNavLink();

        int firstPost = 1;
        buzzPage.deleteBuzzPost(1, "Cancel");
        buzzPage.clickBuzzPostNavLink();
        assertTrue(buzzPage.isTextPresentInBuzzPost(firstPost, text), "Post is not present");
    }
}
