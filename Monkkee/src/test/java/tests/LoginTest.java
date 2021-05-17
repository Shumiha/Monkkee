package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Log In")
    public void correctDataFilling() {
        loginPage
                .open()
                .login("testUser@mailinator.com", "Password123");
        loginPage.checkPageOpened();
    }
}
