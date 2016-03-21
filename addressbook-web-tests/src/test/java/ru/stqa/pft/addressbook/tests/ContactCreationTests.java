package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().home();
//        List<ContactData> before = app.contact().list();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                .withMobilenumber("79177121162").withGroupname("test1");

        app.contact().create(contact, true);
        app.goTo().home();
        Set<ContactData> after = app.contact().all();
//        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
        before.add(contact);
//        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//        before.sort(byId);
//        after.sort(byId);
        Assert.assertEquals(after, before);

    }


}
