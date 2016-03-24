package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 24.03.16.
 */
public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva fsfsfs sfsfsfs   sfsfsfs   sfsfsfsfssf")
                    .withMobilenumber("79177121162").withHomeNumber("46984968468 555 555").withWorkNumber("(-) gddgdgdgdgdg");
            app.contact().create(contact, true);
        }
    }

    @Test
    public void testContactDetails() {
        app.goTo().home();
        ContactData contact =  app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFromEditForm(contact);
        assertThat(app.contact().infoDetailsContact(contact),equalTo(mergeAll(contactInfoFormEditForm)));

    }


    private String mergeFirstNameLastName(ContactData contact) {
        return Arrays.asList(contact.getFirstname(),contact.getLastname())
                .stream()
                .filter((s)->!s.equals(""))
                .map(ContactDetailsTests::cleaned)
                .collect(Collectors.joining(" "));
    }

    private String mergePersonalData(ContactData contact) {
        return Arrays.asList(mergeFirstNameLastName(contact),contact.getUsername(),contact.getCompany(),contact.getHomeadress())
                .stream()
                .filter((s)->!s.equals(""))
                .map(ContactDetailsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList("H: "+ contact.getHomeNumber(),"M: " + contact.getMobilenumber(),"W: " + contact.getWorkNumber())
                .stream()
                .filter((s)->!s.equals(""))
                .map(ContactDetailsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


    private String mergeEmails(ContactData contact) {
             String[] getEmail = contact.getEmail().split("@");
             String[] getEmail1 = contact.getEmail1().split("@");
             String[] getEmail2 = contact.getEmail2().split("@");
        return Arrays.asList(contact.getEmail() + " (www."+getEmail[1]+ ")",
                             contact.getEmail1() + " (www."+getEmail1[1]+ ")",
                             contact.getEmail2() + " (www."+getEmail2[1]+ ")")
                .stream()
                .filter((s)->!s.equals(""))
                .map(ContactDetailsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }



    private String mergeAll(ContactData contact) {
        return Arrays.asList(mergePersonalData(contact),mergePhones(contact),mergeEmails(contact))
                .stream()
                .filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n\n"));
    }



    public static String cleaned(String phone){
        return phone.replaceAll("\\s{2,}"," ");
    }




}
