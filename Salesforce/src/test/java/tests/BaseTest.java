package tests;

import models.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.ContactSteps;
import steps.LoginSteps;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {

    public static final String LOGIN = "timoshkevichmisha-vhrf@force.com";
    public static final String PASSWORD = "Misha9753954";

    WebDriver driver;
    Contact contact;
    LoginSteps loginSteps;
    ContactSteps contactSteps;

    @BeforeMethod(description = "Opening browser")
    public void setup(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        contact = new Contact("Misha","Timoshkevich",  "Africa",
                "+375293333333","26752","Mr.");
        loginSteps = new LoginSteps(driver);
        contactSteps = new ContactSteps(driver);

        context.setAttribute("driver",driver);
    }

    @AfterMethod(alwaysRun = true, description = "Closing browser")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
