package steps;

import io.qameta.allure.Step;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ContactListPage;
import pages.NewContactModal;

public class ContactSteps {
    WebDriver driver;

    public ContactSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Creating contact:'{contact.lastName}'")
    public void create(Contact contact) {
        ContactListPage contactListPage = new ContactListPage(driver);
        NewContactModal newContactModal = new NewContactModal(driver);
        contactListPage.open();
        newContactModal.checkPageOpened();
        contactListPage.clickNew();
        newContactModal.create(contact);
        newContactModal.save();
        Assert.assertTrue(driver.findElement(By.cssSelector(".slds-card__header-title ")).isDisplayed());
    }
}
