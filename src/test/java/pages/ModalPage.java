package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ModalPage extends BasePage {

    public static final By NEW_TAG = By.id("new-tag");
    public static final By NEW_TAG_BUTTON = By.id("assign-new-tag");
    public static final By DELETE_TAG = By.cssSelector(".pointer.ng-binding");
    public static final By TAG_VALIDATION = By.cssSelector(".none");
    public static final By INPUT_SEARCH = By.id("appendedInputButton");
    public static final By SEARCH_BUTTON = By.cssSelector(".btn-primary.input-group-addon");
    public static final By MONKEY_LOADING = By.id("loading-animation-wrapper");
    public static final By ENTRY_CONTAINER = By.cssSelector(".entry-container");

    public ModalPage(WebDriver driver) {
        super(driver);
    }

    @Step("Adding a tag to a post")
    public ModalPage addTag(String text) {
        try {
            driver.findElement(ENTRY).click();
        } catch (NoSuchElementException ex) {
            Assert.fail("No entries");
        }
        driver.findElement(NEW_TAG).sendKeys(text);
        driver.findElement(NEW_TAG_BUTTON).click();
        Assert.assertEquals(driver.findElement(DELETE_TAG).getText(), text,
                "Tag has not been added");
        return new ModalPage(driver);
    }

    @Step("Remove tag")
    public ModalPage deleteTag() {
        driver.findElement(DELETE_TAG).click();
        Assert.assertEquals(driver.findElement(TAG_VALIDATION).getText(), "None",
                "Tag not removed");
        return new ModalPage(driver);
    }

    @Step("Search record by word")
    public void searchEmpty(String text) {
        driver.findElement(INPUT_SEARCH).sendKeys(text);
        driver.findElement(SEARCH_BUTTON).click();
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MONKEY_LOADING));
            Assert.assertEquals(driver.findElements(ENTRY_CONTAINER).size(), numberEntry,
                    "Incorrect search results");
        } catch (TimeoutException ex) {
            Assert.fail("No records found");
            log.error("No records found");
        }
    }
}
