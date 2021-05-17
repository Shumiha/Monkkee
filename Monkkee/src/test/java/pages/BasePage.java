package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {
    public static final By MODAL_CONTENT = By.cssSelector(".modal-content");
    public static final By CANCEL_BUTTON = By.xpath("//*[text()='Cancel']");
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 6);
    }

    public void popupWindow() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_CONTENT));
        } catch (TimeoutException exception) {
            driver.findElement(CANCEL_BUTTON).click();
        }
    }
}
