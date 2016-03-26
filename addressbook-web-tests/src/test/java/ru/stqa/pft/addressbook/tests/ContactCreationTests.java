package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().home();
//        List<ContactData> before = app.contact().list();
//        Set<ContactData> before = app.contact().all();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                .withMobilenumber("79177121162").withGroupname("test1");

        app.contact().create(contact, true);
        app.goTo().home();
//        Set<ContactData> after = app.contact().all();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();
//        List<ContactData> after = app.contact().list();
//        Assert.assertEquals(after.size(), before.size() + 1);
//        assertThat(after.size(),equalTo(before.size()+1));
//        contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
//        before.add(contact);
//        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(after, before);
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }




    @Test
    public void testContactCreationFile() {
        app.goTo().home();
        File photo = new File("src/test/resources/tc508.jpg");
        app.contact().initContactCreation();
        app.contact().fillContactForm(new ContactData().withFirstname("tester").withLastname("testerov").withGroupname("test1").withPhoto(photo),true);
        app.contact().submitContactCreation();
        app.goTo().home();
    }


    @Test
    public void testPath(){
        File file = new File(".");
        System.out.println(file.getAbsolutePath());
        File photo = new File("src/test/resources/tc508.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }


}
