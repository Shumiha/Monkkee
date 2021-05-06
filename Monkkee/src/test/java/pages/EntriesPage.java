package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class EntriesPage extends BasePage {

    public static final By CREATE_AN_ENTRY = By.id("create-entry");
    public static final By DELETE_BUTTON = By.id("delete-entries");
    public static final By EDITABLE = By.id("editable");
    public static final By EDITOR_TOOLBARS = By.cssSelector(".cke_top");
    public static final By SAVE_BUTTON = By.cssSelector(".cke_button__savetoggle");
    public static final By NUMBER_OF_RECORDS = By.cssSelector(".checkbox-wrapper");
    public static final By BUTTON_HOME = By.cssSelector(".icon-home");
    public static final By ENTRY = By.cssSelector("div[ng-bind-html='entry.body']");
    public static final By DATE_ENTRY = By.cssSelector(".calendar-date");

    public EntriesPage(WebDriver driver) {
        super(driver);
    }

    public EntriesPage addNewEntry(String text) {
        driver.findElement(CREATE_AN_ENTRY).click();
        popupWindow();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(EDITOR_TOOLBARS));
        } catch (TimeoutException ex) {
            Assert.fail("Failed to load page");
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

    //TODO не могу разобраться почему не кликает чекбокс!!! поэтому метод не работает!
    public EntriesPage deleteEntry() {
        //WebElement element = driver.findElement(NUMBER_OF_RECORDS);
        //element.click();
        Actions clickCheckbox = new Actions(driver);
        List<WebElement> elems = driver.findElements(NUMBER_OF_RECORDS);
        clickCheckbox.click(elems.get(0));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_BUTTON));
        } catch (TimeoutException ex) {
            //Assert.fail("Failed to load page");
        }
        driver.findElement(DELETE_BUTTON).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return new EntriesPage(driver);
    }

    public EntriesPage addToExistingEntry(String text) {
        driver.findElement(ENTRY).click();
        popupWindow();
        String textEntry = driver.findElement(EDITABLE).getText() + text;
        driver.findElement(EDITABLE).clear();
        driver.findElement(EDITABLE).sendKeys(textEntry);
        driver.findElement(SAVE_BUTTON).click();
        driver.findElement(BUTTON_HOME).click();
        try {
            Assert.assertEquals(driver.findElement(ENTRY).getText(), textEntry, "Record not updated");
        } catch (TimeoutException ex) {
            Assert.fail("Record not updated");
        }
        return new EntriesPage(driver);
    }
}
