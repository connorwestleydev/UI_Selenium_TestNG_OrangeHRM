package buzz;

import base.BaseTests_LoginLogout;
import org.testng.annotations.Test;
import pages.BuzzPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.RandomVariables.getRandomString;


public class BuzzTests extends BaseTests_LoginLogout {

    @Test
    public void testBuzzPostWithTextOnly() {
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
        assertEquals(buzzPage.getFirstBuzzPostText(), postText, "First post text is not correct");
    }
}
