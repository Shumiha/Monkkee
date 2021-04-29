package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LookUp {

    WebDriver driver;
    WebDriverWait wait ;
    String label;
    String locator = "//*[contains(text(),'%s')]/ancestor::lightning-lookup//input";
    String optionLocator = "[title=%s]";

    public LookUp(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
        wait = new WebDriverWait(driver,30);
    }

    public void select(String text) {
        driver.findElement(By.xpath(String.format(locator, label))).sendKeys(text);
        isListOpened(text);
        driver.findElement(By.xpath(String.format(optionLocator, text))).click();
    }
    public void isListOpened(String text) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".slds-input-has-icon_group-right")));
        }catch (NoSuchElementException ex){
            Assert.fail(String.format("Cannot find option '%s'", text));
        }
    }
}
