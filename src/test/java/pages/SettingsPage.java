package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SettingsPage extends BasePage {

    public static final By SETTINGS_BUTTON = By.cssSelector(".icon-cog.icon-light");
    public static final By LANGUAGE_DROPDOWN = By.name("selectLocale");
    public static final By SAVE_BUTTON = By.cssSelector(".btn-text-content");
    public static final By LABEL_VALIDATION = By.cssSelector(".alert");
    public static final By SELECT_PASSWORD_MENU = By.cssSelector("[ng-class=\"cssClass('password')\"]");
    public static final By SELECT_ALIAS_MENU = By.cssSelector("[ng-class=\"cssClass('login')\"]");
    public static final By OLD_PASSWORD = By.id("old-password");
    public static final By PASSWORD = By.id("password");
    public static final By CONFIRMATION_PASSWORD = By.id("password-confirmation");
    public static final By ALIAS_CHECKBOX = By.id("use-alias");
    public static final By INPUT_ALIAS = By.id("alias");

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Choice of another language, French")
    public void languageSelectionFrench() {
        driver.findElement(SETTINGS_BUTTON).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LANGUAGE_DROPDOWN));
        } catch (NoSuchElementException ex) {
            Assert.fail("Settings page not loaded");
        }
        Select languageFrench = new Select(driver.findElement(LANGUAGE_DROPDOWN));
        languageFrench.selectByIndex(2);
        driver.findElement(SAVE_BUTTON).click();
        Assert.assertEquals(driver.findElement(LABEL_VALIDATION).getText(), "Modifications enregistrées",
                "Error when changing language");
        Select languageEnglish = new Select(driver.findElement(LANGUAGE_DROPDOWN));
        languageEnglish.selectByIndex(1);
        driver.findElement(SAVE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_VALIDATION));
    }

    @Step("Replacing the old password with a new one")
    public void passwordChange(String oldPassword, String newPassword) {
        driver.findElement(SETTINGS_BUTTON).click();
        driver.findElement(SELECT_PASSWORD_MENU).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(OLD_PASSWORD));
        } catch (NoSuchElementException ex) {
            Assert.fail("The password change menu is not open");
        }
        driver.findElement(OLD_PASSWORD).sendKeys(oldPassword);
        driver.findElement(PASSWORD).sendKeys(newPassword);
        driver.findElement(CONFIRMATION_PASSWORD).sendKeys(newPassword);
        driver.findElement(SAVE_BUTTON).click();
        Assert.assertEquals(driver.findElement(LABEL_VALIDATION).getText(),
                "Your password has been changed successfully", "Password has not been changed");
        driver.findElement(OLD_PASSWORD).sendKeys(newPassword);
        driver.findElement(PASSWORD).sendKeys(oldPassword);
        driver.findElement(CONFIRMATION_PASSWORD).sendKeys(oldPassword);
        driver.findElement(SAVE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_VALIDATION));
    }

    @Step("Creating a login alias")
    public void creatingAnAlias(String loginAlias) {
        driver.findElement(SETTINGS_BUTTON).click();
        driver.findElement(SELECT_ALIAS_MENU).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ALIAS_CHECKBOX));
        } catch (NoSuchElementException ex) {
            Assert.fail("The password change menu is not open");
        }
        driver.findElement(ALIAS_CHECKBOX).click();
        driver.findElement(INPUT_ALIAS).clear();
        driver.findElement(INPUT_ALIAS).sendKeys(loginAlias);
        driver.findElement(SAVE_BUTTON).click();
        Assert.assertEquals(driver.findElement(LABEL_VALIDATION).getText(),
                "Your settings have been saved successfully", "Login alias not set");
        driver.findElement(ALIAS_CHECKBOX).click();
        driver.findElement(SAVE_BUTTON).click();

    }

}
