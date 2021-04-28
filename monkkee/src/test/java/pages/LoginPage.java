package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public static final By LOGIN_INPUT = By.id("login");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.cssSelector(".btn-text-content");
    public static final By CREATE_BUTTON = By.id("create-entry");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get("https://my.monkkee.com/");
        return new LoginPage(driver);
    }

    public void login(String userName, String password) {
        driver.findElement(LOGIN_INPUT).sendKeys(userName);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void checkPageOpened() {
        try {
            Assert.assertTrue(driver.findElement(CREATE_BUTTON).isDisplayed(), "Incorrect login or password");
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CREATE_BUTTON));
        } catch (TimeoutException ex) {
            Assert.fail("Failed to load page");
        }
    }
}
