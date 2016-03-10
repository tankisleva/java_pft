package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


/**
 * Created by oleg on 28.02.16.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returnHome();
        if (!app.getContactHelper().isThereContact()) {
            ContactData contact =  new ContactData("oleg", "malyshev", "tanki_sleva", "wamba", "parkway yraeva", "79177121162", "test");
            app.getContactHelper().createContact(contact, true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().editSelectionContacts();
        app.getContactHelper().deleteContacct();
        app.getNavigationHelper().returnHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(after,before);

    }
}
