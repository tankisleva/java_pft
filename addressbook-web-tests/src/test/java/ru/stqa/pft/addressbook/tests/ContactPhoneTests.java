package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 22.03.16.
 */
public class ContactPhoneTests extends TestBase{

    @Test
    public void testContactPhones() {
        app.goTo().home();
        ContactData contact =  app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getHomeNumber(),equalTo(cleaned(contactInfoFormEditForm.getHomeNumber())));
        assertThat(contact.getMobilenumber(),equalTo(cleaned(contactInfoFormEditForm.getMobilenumber())));
        assertThat(contact.getWorkNumber(),equalTo(cleaned(contactInfoFormEditForm.getWorkNumber())));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
