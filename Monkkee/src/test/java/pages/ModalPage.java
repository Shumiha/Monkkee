package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

@Log4j2
public class ModalPage extends BasePage {

    public static final By NEW_TAG = By.id("new-tag");
    public static final By NEW_TAG_BUTTON = By.id("assign-new-tag");
    public static final By DELETE_TAG = By.cssSelector(".pointer.ng-binding");
    public static final By TAG_VALIDATION = By.cssSelector(".none");

    public ModalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Adding a tag to a post")
    public void addTag(String text) {
        try {
            driver.findElement(ENTRY).click();
        } catch (NoSuchElementException ex) {
            Assert.fail("No entries");
        }
        driver.findElement(NEW_TAG).sendKeys(text);
        driver.findElement(NEW_TAG_BUTTON).click();
        Assert.assertEquals(driver.findElement(DELETE_TAG).getText(), text,
                "Tag has not been added");
    }

    @Step("Remove tag")
    public void deleteTag() {
        driver.findElement(DELETE_TAG).click();
        try {
            Assert.assertEquals(driver.findElement(TAG_VALIDATION).getText(), "None",
                    "Tag not removed");
        } catch (NoSuchElementException ex) {
            log.error("Tag has been removed");
        }
    }
}
