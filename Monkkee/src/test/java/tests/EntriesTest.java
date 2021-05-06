package tests;

import org.testng.annotations.Test;

public class EntriesTest extends BaseTest {

    @Test(description = "Adding a new entry")
    public void addEntries() {
        loginPage
                .open()
                .login("testUser@mailinator.com", "Password123")
                .addNewEntry("Hello World!");
    }

    //@Test(description = "Delete entry")
    //public void deleteEntries() {
    //   addEntries();
    //    entriesPage.deleteEntry();
    //}

    @Test(description = "Updating an existing record by adding a new proposal")
    public void updateEntry() {
        addEntries();
        entriesPage.addToExistingEntry(" Hi Misha");
    }
}
