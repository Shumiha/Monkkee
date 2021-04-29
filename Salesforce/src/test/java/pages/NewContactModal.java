package pages;

import elements.DropDown;
import elements.Input;
import elements.LookUp;
import io.qameta.allure.Step;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class NewContactModal extends BasePage {
    public static final By SAVE_BUTTON = By.cssSelector("[title=Save]");

    public NewContactModal(WebDriver driver) {
        super(driver);
    }

    @Step("Creating contact: {contact.lastName}")
    public void create(Contact contact) {
        new Input(driver, "Last Name").write(contact.getLastName());
        new Input(driver, "First Name").write(contact.getFirstName());
        new Input(driver, "Mobile").write(contact.getMobile());
        new Input(driver, "Phone").write(contact.getPhone());
        new LookUp(driver, "Account Name").select(contact.getAccountName());
        new DropDown(driver,"Salutation").choice(contact.getSalutation());
    }

    @Step("Click 'Save'")
    public void save() {
        driver.findElement(SAVE_BUTTON).click();
    }

    public void checkPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".forceActionLink")));
        } catch (TimeoutException ex) {
            Assert.fail("Страница контактов не была загружена");
        }
    }
}
