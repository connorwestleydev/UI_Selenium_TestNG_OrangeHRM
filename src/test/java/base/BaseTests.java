package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;

public class BaseTests {

    private static WebDriver driver;
    protected LoginPage loginPage;

    @BeforeSuite
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @BeforeClass
    public void initialiseLogin() {
        loginPage = new LoginPage(driver);
    }

    @AfterSuite
    public static void tearDown() {
        driver.quit();
    }
}
