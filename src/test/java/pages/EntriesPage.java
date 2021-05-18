package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Log4j2
public class EntriesPage extends BasePage {

    public static final By DELETE_BUTTON = By.id("delete-entries");
    public static final By EDITABLE = By.id("editable");
    public static final By EDITOR_TOOLBARS = By.cssSelector(".cke_top");
    public static final By SAVE_BUTTON = By.cssSelector(".cke_button__savetoggle");
    public static final By NUMBER_OF_RECORDS = By.cssSelector("input[ng-model='model.checked[entry.id]']");
    public static final By DATE_ENTRY = By.cssSelector(".calendar-date");

    public EntriesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Adding a new entry")
    public EntriesPage addNewEntry(String text) {
        driver.findElement(CREATE_BUTTON).click();
        closingThePopup();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(EDITOR_TOOLBARS));
        } catch (TimeoutException ex) {
            Assert.fail("Failed to load page");
            log.error("Editor toolbars not loaded on page");
        }
        driver.findElement(EDITABLE).sendKeys(text);
        driver.findElement(SAVE_BUTTON).click();
        driver.findElement(BUTTON_HOME).click();
        try {
            Assert.assertTrue(driver.findElement(DATE_ENTRY).isDisplayed(), "Entry not added");
        } catch (TimeoutException ex) {
            Assert.fail("Entry has not been added");
        }
        return new EntriesPage(driver);
    }

    @Step("Delete entry")
    public EntriesPage deleteEntry() {
        List<WebElement> elems = driver.findElements(NUMBER_OF_RECORDS);
        elems.get(0).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_BUTTON));
        } catch (TimeoutException ex) {
            Assert.fail("No entry for deletion selected");
            log.error("Maybe there are no records, you need to check their presence");
        }
        driver.findElement(DELETE_BUTTON).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return new EntriesPage(driver);
    }

    @Step("Adding text to an existing entry")
    public EntriesPage addToExistingEntry(String text) {
        try {
            driver.findElement(ENTRY).click();
        } catch (NoSuchElementException ex) {
            Assert.fail("No entries");
        }
        closingThePopup();
        String textEntry = driver.findElement(EDITABLE).getText() + text;
        driver.findElement(EDITABLE).clear();
        driver.findElement(EDITABLE).sendKeys(textEntry);
        driver.findElement(SAVE_BUTTON).click();
        driver.findElement(BUTTON_HOME).click();
        try {
            Assert.assertEquals(driver.findElement(ENTRY).getText(), textEntry, "Record not updated");
        } catch (TimeoutException ex) {
            Assert.fail("Record not updated");
            log.error("Maybe there are no records, you need to check their presence");
        }
        return new EntriesPage(driver);
    }
}
