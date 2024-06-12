package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.LoginPage;

import java.util.Arrays;

public class BaseTests {

    private static WebDriver driver;
    protected LoginPage loginPage;

    @BeforeSuite
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Arrays.asList(
                "--headless",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080"
        ));
        driver = new ChromeDriver(options);
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
