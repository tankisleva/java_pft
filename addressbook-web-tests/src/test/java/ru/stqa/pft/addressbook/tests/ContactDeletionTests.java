package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


/**
 * Created by oleg on 28.02.16.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returnHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().editSelectionContacts();
        app.getContactHelper().deleteContacct();
        app.getNavigationHelper().returnHome();
    }
}
