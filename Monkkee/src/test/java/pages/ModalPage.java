package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2
public class ModalPage extends BasePage {

    public static final By NEW_TAG = By.id("new-tag");
    public static final By NEW_TAG_BUTTON = By.id("assign-new-tag");
    public static final By TAG_VALIDATION = By.cssSelector(".ng-binding.ng-scope");
    public static final By DELETE_TAG = By.xpath("//*[contains(text(),'Manage tags')]/ancestor::div[contains(@class,'sidebar')]//a");
    public static final By DELETE_TAG_BUTTON =By.xpath("//*[contains(@class,'icon-trash')]/ancestor::td[contains(@class,'buttons')]//i");

    public ModalPage(WebDriver driver) {
        super(driver);
    }

    public void addTag(String text) {
        List<WebElement> elems = driver.findElements(ENTRY);
        elems.get(0).click();
        driver.findElement(NEW_TAG).sendKeys(text);
        driver.findElement(NEW_TAG_BUTTON).click();
        driver.findElement(BUTTON_HOME).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TAG_VALIDATION));
        } catch (TimeoutException ex) {
            Assert.fail("Tag has not been added");
            log.error("Maybe there are no records, you need to check their presence");
        }
        Assert.assertEquals(driver.findElement(TAG_VALIDATION).getText(), text,
                "Tag has not been added");
    }

    public void deleteTag() {
        driver.findElement(DELETE_TAG).click();
        List<WebElement> elems = driver.findElements(DELETE_TAG_BUTTON);
        elems.get(1).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.findElement(BUTTON_HOME).click();
        Assert.assertTrue(driver.findElement(TAG_VALIDATION).isDisplayed(),
                "Tag not removed");
    }
}
