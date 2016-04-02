package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 22.03.16.
 */
public class ContactPhoneTests extends TestBase{


    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162").withAllEmails("gsgssfsf@mail.ru").withtEmail2("gdgdgdgdW@yandex.ru")
                    .withMobilenumber("8593859385").withHomeNumber("gdgdgdgdgdg");
            app.contact().create(contact, true);
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().home();
        ContactData contact =  app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFormEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomeNumber(),contact.getMobilenumber(),contact.getWorkNumber())
                .stream()
                .filter((s)->!s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
