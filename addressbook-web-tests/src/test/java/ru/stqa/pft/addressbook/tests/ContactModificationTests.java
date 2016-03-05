package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by oleg on 28.02.16.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returnHome();
        app.getContactHelper().selectContact();
        app.getContactHelper().editSelectionContacts();
        app.getContactHelper().fillContactForm(new ContactData("denis", "dmidrieev", "dridly", "mvideo", "pervomayskaya street", "+79177121162",null), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().returnHome();
    }
}