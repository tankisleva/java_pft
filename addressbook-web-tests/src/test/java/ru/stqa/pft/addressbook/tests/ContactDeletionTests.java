package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


/**
 * Created by oleg on 28.02.16.
 */
public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().delete(index);
        app.goTo().home();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(after,before);

    }

}
