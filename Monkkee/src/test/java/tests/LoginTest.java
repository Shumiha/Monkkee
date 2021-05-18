package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Log In")
    public void correctDataFilling() {
        loginPage
                .open()
                .login(getLogin(), getPassword());
        loginPage.checkPageOpened();
    }

    @Test(description = "Empty login and password")
    public void emptyData() {
        loginPage
                .open()
                .login("", "");
        loginPage.checkingAnEmptyField();
    }

    @Test(description = "Incorrect data")
    public void incorrectData() {
        loginPage
                .open()
                .login("usertest@mailinator.com", "Password");
        loginPage.invalidDataCheck();
    }
}
