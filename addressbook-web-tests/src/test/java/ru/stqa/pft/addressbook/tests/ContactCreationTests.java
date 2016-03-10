package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact =  new ContactData("oleg", "malyshev", "tanki_sleva", "wamba", "parkway yraeva", "79177121162", "test");
        app.getContactHelper().createContact(contact,true);
        List<ContactData> after = app.getContactHelper().getContactList();
        app.getNavigationHelper().returnHome();
        Assert.assertEquals(after.size(),before.size()+1);
        before.add(contact);
        Comparator<? super ContactData> byId =  (c1,c2) -> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after,before);

    }


}
