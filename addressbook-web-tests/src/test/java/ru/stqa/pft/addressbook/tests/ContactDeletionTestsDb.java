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
public class ContactDeletionTestsDb extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162");
            app.contact().create(contact, true);
        } else {app.goTo().home();}
    }


    @Test
    public void testContactDeletionDb() {
        Contacts before = app.db().contacts();
        ContactData deleteContact =  before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Contacts after = app.db().contacts();
        assertThat(after,equalTo(before.withOut(deleteContact)));
        verifyContactListInUi();

    }
}
