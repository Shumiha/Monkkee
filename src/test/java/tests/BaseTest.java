package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.EntriesPage;
import pages.LoginPage;
import pages.ModalPage;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

//@Data
@Listeners(TestListener.class)
public abstract class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    EntriesPage entriesPage;
    ModalPage modalPage;
    //private String login = "testUser@mailinator.com";
    //private String password = "Password123";

    @BeforeMethod(description = "Opening browser")
    public void setup(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        entriesPage = new EntriesPage(driver);
        modalPage = new ModalPage(driver);
        context.setAttribute("driver", driver);
    }


    @AfterMethod(alwaysRun = true, description = "Closing browser")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
