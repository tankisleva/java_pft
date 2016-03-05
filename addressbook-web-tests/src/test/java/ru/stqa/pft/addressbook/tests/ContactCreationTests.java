package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("oleg", "malyshev", "tanki_sleva", "wamba", "parkway yraeva", "79177121162", "test1"), true);
        app.getNavigationHelper().returnHome();
    }


}
