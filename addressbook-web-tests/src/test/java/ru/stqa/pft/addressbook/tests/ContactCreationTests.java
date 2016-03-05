package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("oleg", "malyshev", "tanki_sleva", "wamba", "parkway yraeva", null,"test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnHome();
    }





}