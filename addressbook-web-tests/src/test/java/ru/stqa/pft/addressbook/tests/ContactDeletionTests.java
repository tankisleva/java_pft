package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;


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
//        List<ContactData> before = app.contact().list();
        Set<ContactData> before = app.contact().all();
        ContactData deleteContact =  before.iterator().next();
//        int index = before.size()-1;
        app.contact().delete(deleteContact);
        app.goTo().home();
//        List<ContactData> after = app.contact().list();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size()-1);
        before.remove(deleteContact);
        Assert.assertEquals(after,before);

    }

}
