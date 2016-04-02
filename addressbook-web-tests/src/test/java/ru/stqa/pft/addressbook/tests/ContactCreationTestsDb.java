package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 02.04.16.
 */
public class ContactCreationTestsDb extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().home();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                .withMobilenumber("79177121162").withGroupname("test1");

        app.contact().create(contact, true);
        Contacts after = app.db().contacts();
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }
}
