package tests;

import org.testng.annotations.Test;

public class FunctionalTest extends BaseTest {

    @Test
    public void tagAssignment () {
        loginPage
                .open()
                .login(getLogin(),getPassword())
                .addNewEntry("add tag");
        modalPage.addTag("test tag");
    }

    @Test
    public void deleteTag() {
        loginPage
                .open()
                .login(getLogin(),getPassword())
                .addNewEntry("add tag");
        modalPage.addTag("test tag");
        modalPage.deleteTag();
    }
}
