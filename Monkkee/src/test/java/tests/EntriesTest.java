package tests;

import org.testng.annotations.Test;

public class EntriesTest extends BaseTest {

    @Test(description = "Adding a new entry")
    public void addNewEntries() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword())
                .addNewEntry("Hello World!");
    }

    @Test(description = "Delete entry")
    public void deleteEntries() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword())
                .addNewEntry("Hello World!")
                .deleteEntry();
    }

    @Test(description = "Updating an existing record by adding a new proposal")
    public void updateEntry() {
        loginPage
                .open()
                .login(loginPage.getLogin(), loginPage.getPassword())
                .addToExistingEntry(" Hi Misha");
    }
}
