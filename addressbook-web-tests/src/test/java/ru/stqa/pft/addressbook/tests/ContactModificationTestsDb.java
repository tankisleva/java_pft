package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 02.04.16.
 */
public class ContactModificationTestsDb extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162");
            app.contact().create(contact, true);
        } else { app.goTo().home();}
    }

    @Test
    public void testContactModificationDb() {
        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId())
                .withFirstname("Denis").withLastname("Dmitriev").withMobilenumber("79135356446");
        app.contact().modify(contact, false);
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after,equalTo(before.withOut(modifyContact).withAdded(contact)));
    }


}
