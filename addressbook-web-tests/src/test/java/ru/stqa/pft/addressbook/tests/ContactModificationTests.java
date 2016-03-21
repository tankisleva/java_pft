package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 28.02.16.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().home();
        if (app.contact().list().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162").withGroupname("test1");
            app.contact().create(contact, true);
        }
    }

    @Test
    public void testContactModification() {
//        List<ContactData> before = app.contact().list();
//        Set<ContactData> before = app.contact().all();
        Contacts before = app.contact().all();
//        int index = before.size()-1;
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId())
                .withFirstname("Denis").withLastname("Dmitriev").withMobilenumber("79135356446");
        app.contact().modify(contact, false);
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()));
//        List<ContactData> after = app.contact().list();
//        Set<ContactData> after = app.contact().all();
        Contacts after = app.contact().all();
//        Assert.assertEquals(after.size(),before.size());
//        before.remove(modifyContact);
//        before.add(contact);
//        Comparator<? super ContactData> byId =  (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(after,before);
//        assertThat(after.size(),equalTo(before.size()));
        assertThat(after,equalTo(before.withOut(modifyContact).withAdded(contact)));
    }


}
