package tests;

import org.testng.annotations.Test;

public class ContactTest extends BaseTest {

    @Test(description = "Creation of contact")
    public void createContact() {
        loginSteps.login(LOGIN,PASSWORD);
        contactSteps.create(contact);
    }
}