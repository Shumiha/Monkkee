package tests;

import org.testng.annotations.Test;

public class FunctionalTest extends BaseTest {

    @Test(description = "Tag assignment")
    public void tagAssignment() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword());
        modalPage.addTag("test tag");
    }

    @Test(description = "Delete tag")
    public void deleteTag() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword());
        modalPage.addTag("test tag")
                .deleteTag();
    }

    @Test(description = "Search entry")
    public void searchByWord() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword());
        modalPage.searchEmpty("hello");
    }
}
