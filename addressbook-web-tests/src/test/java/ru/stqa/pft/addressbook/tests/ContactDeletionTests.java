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
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162").withGroupname("test1");
            app.contact().create(contact, true);
        }
    }


    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deleteContact =  before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().home();
        Contacts after = app.contact().all();
        assertThat(after.size(),equalTo(before.size()-1));
        assertThat(after,equalTo(before.withOut(deleteContact)));

    }

}
