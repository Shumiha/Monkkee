package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public static final By LOGIN_INPUT = By.id("login");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.cssSelector(".btn-text-content");
    public static final By ERROR_EMPTY = By.cssSelector(".help-block ");
    public static final By DATA_ERROR = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get("https://my.monkkee.com/");
        return new LoginPage(driver);
    }

    public EntriesPage login(String userName, String password) {
        driver.findElement(LOGIN_INPUT).sendKeys(userName);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        closingThePopup();
        return new EntriesPage(driver);
    }

    public void checkPageOpened() {
        Assert.assertTrue(driver.findElement(CREATE_BUTTON).isDisplayed(), "Incorrect login or password");
    }

    public void invalidDataCheck() {
        Assert.assertEquals(driver.findElement(DATA_ERROR).getText(), "Login failed" ,
                "Incorrect login or password");
    }

    public void checkingAnEmptyField() {
        Assert.assertEquals(driver.findElement(ERROR_EMPTY).getText(),"Mandatory field",
                "Empty login or password");
    }
}
