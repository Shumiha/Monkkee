package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void wrongPassword() {
        loginPage
                .open()
                .login("testUser@mailinator.com", "password1");
        loginPage.checkPageOpened();
    }
}
