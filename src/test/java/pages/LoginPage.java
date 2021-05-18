package pages;

import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Data
public class LoginPage extends BasePage {

    public static final By LOGIN_INPUT = By.id("login");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.cssSelector(".btn-text-content");
    public static final By ERROR_EMPTY = By.cssSelector(".help-block ");
    public static final By DATA_ERROR = By.cssSelector(".alert-danger");
    private String login = "testUser@mailinator.com";
    private String password = "Password123";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Page loading 'Mymonkkee'")
    public LoginPage open() {
        driver.get("https://my.monkkee.com/");
        return new LoginPage(driver);
    }

    @Step("Login page")
    public EntriesPage login(String userName, String password) {
        driver.findElement(LOGIN_INPUT).sendKeys(userName);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        closingThePopup();
        return new EntriesPage(driver);
    }

    @Step("Checking that the page is loaded")
    public void checkPageOpened() {
        Assert.assertTrue(driver.findElement(CREATE_BUTTON).isDisplayed(), "Incorrect login or password");
    }

    @Step("Login check with invalid data")
    public void invalidDataCheck() {
        Assert.assertEquals(driver.findElement(DATA_ERROR).getText(), "Login failed",
                "Incorrect login or password");
    }

    @Step("Validating an input with blank fields")
    public void checkingAnEmptyField() {
        Assert.assertEquals(driver.findElement(ERROR_EMPTY).getText(), "Mandatory field",
                "Empty login or password");
    }
}
