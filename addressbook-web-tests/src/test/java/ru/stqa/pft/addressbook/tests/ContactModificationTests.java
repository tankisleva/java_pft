package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by oleg on 28.02.16.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnHome();
        if (!app.getContactHelper().isThereContact()) {
            ContactData contact = new ContactData("oleg", "malyshev", "tanki_sleva", "wamba", "parkway yraeva", "79177121162", "test");
            app.getContactHelper().createContact(contact, true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().editSelectionContacts();
        ContactData modifyContact = new ContactData("denis", "dmidrieev", "dridly", "mvideo", "pervomayskaya street", "+79177121162", null);
        app.getContactHelper().fillContactForm(modifyContact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().returnHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size()-1);
        before.add(modifyContact);
        Comparator<? super ContactData> byId =  (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after,before);
    }
}
