package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public abstract class BasePage {
    public static final By MODAL_CONTENT = By.cssSelector(".modal-content");
    public static final By CANCEL_BUTTON = By.xpath("//*[text()='Cancel']");
    public static final By CREATE_BUTTON = By.id("create-entry");
    public static final By ENTRY = By.cssSelector(".body ");//("div[ng-bind-html='entry.body']");
    public static final By BUTTON_HOME = By.cssSelector(".icon-home");
    int numberEntry = 1;
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 6);
    }

    public void closingThePopup() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_CONTENT));
        } catch (TimeoutException exception) {
            driver.findElement(CANCEL_BUTTON).click();
            log.info("Popup appeared");
        }
    }
}
