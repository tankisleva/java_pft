package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 23.03.16.
 */
public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva kgsgsgj ksglksglkg lksglklsgk    ;sg;sg;ssg")
                    .withMobilenumber("79177121162").withGroupname("test1").withAllEmails("gsgssfsf@mail.ru").withtEmail2("gdgdgdgdW@yandex.ru");
            app.contact().create(contact, true);
        }
    }


    @Test
    public void testContactAddress() {
        app.goTo().home();
        ContactData contact =  app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getHomeadress(),equalTo(cleaned(contactInfoFormEditForm.getHomeadress())));

    }



    public static String cleaned(String address){
        return address.replaceAll("\\s{2,}"," ");
    }
}
