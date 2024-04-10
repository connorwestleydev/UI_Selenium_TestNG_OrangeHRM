package buzz;

import base.BaseTests_LoginLogout;
import org.testng.annotations.Test;
import pages.BuzzPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.RandomVariables.getRandomString;


public class BuzzTests extends BaseTests_LoginLogout {

    @Test
    public void testBuzzPostWithTextOnlyIsSuccessful() {
        BuzzPage buzzPage = dashboardPage.clickBuzzPost(1);
        String postText = getRandomString();
        buzzPage.createBuzzPost(postText);
        assertTrue(buzzPage.isSuccessNotificationDisplayed(), "Post not successful");
    }

    @Test
    public void testNewBuzzPostAppearsInFeed() {
        BuzzPage buzzPage = dashboardPage.clickBuzzPost(1);
        String postText = getRandomString();
        buzzPage.createBuzzPost(postText);
        buzzPage.clickBuzzPostNavLink();
        assertEquals(buzzPage.getBuzzPostText(1), postText, "First post text is not correct");
    }

    @Test
    public void testEditTextOfNewBuzzPostIsSuccessful() {
        BuzzPage buzzPage = dashboardPage.clickBuzzPost(1);
        buzzPage.createBuzzPost(getRandomString());
        buzzPage.clickBuzzPostNavLink();

        String newText = getRandomString();
        buzzPage.editBuzzPost(1, newText);

        assertTrue(buzzPage.isSuccessNotificationDisplayed(), "Post not successful");
    }

    @Test
    public void testEditTextOfNewBuzzPostAppearsInFeed() {
        BuzzPage buzzPage = dashboardPage.clickBuzzPost(1);
        buzzPage.createBuzzPost(getRandomString());
        buzzPage.clickBuzzPostNavLink();

        int editedPost = 1;
        String newText = getRandomString();
        buzzPage.editBuzzPost(editedPost, newText);

        assertTrue(buzzPage.isTextPresentInBuzzPost(editedPost, newText),
                "Edited buzz post does not contain new text");
    }
}
