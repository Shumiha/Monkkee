package tests;

import org.testng.annotations.Test;

public class SettingsTest extends BaseTest {

    @Test(description = "Choice language")
    public void switchLanguage() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword());
        settingsPage.languageSelectionFrench();
    }

    @Test(description = "Change password")
    public void changePassword() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword());
        settingsPage.passwordChange(loginPage.getPassword(), "Password2");
    }

    @Test(description = "Creating alias")
    public void creatingAlias() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword());
        settingsPage.creatingAnAlias("testAlias");
    }

}
