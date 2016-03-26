package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {



    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                .withMobilenumber("79177121162")});
        list.add(new Object[]{new ContactData().withFirstname("Petr").withLastname("Petrov")
                .withUsername("tanki").withCompany("mamba").withHomeadress("parkway yraeva")
                .withMobilenumber("79177121162")});
        list.add(new Object[]{new ContactData().withFirstname("Denic").withLastname("Dmitriev")
                .withUsername("sleva").withCompany("jduru").withHomeadress("parkway yraeva")
                .withMobilenumber("79177121162")});
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> validContactsFromFileCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
            String line = reader.readLine();

            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withCompany(split[2])
                        .withEmail(split[3]).withHomeadress(split[4])});
                line = reader.readLine();
            }

            return list.iterator();
        }
    }






    @DataProvider
    public Iterator<Object[]> validContactsFromFileXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }

            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();

        }
    }



    @DataProvider
    public Iterator<Object[]> validContactsFromFileJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }

            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

        }
    }


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


    @Test(dataProvider = "validContacts")
    public void testContactCreationDP(ContactData contact) {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.contact().create(contact, true);
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }


    @Test(dataProvider = "validContactsFromFileCsv")
    public void testContactCreationCsv(ContactData contact) {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.contact().create(contact, true);
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }


    @Test(dataProvider = "validContactsFromFileXml")
    public void testContactCreationXml(ContactData contact) {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.contact().create(contact, true);
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }



    @Test(dataProvider = "validContactsFromFileJson")
    public void testContactCreationJson(ContactData contact) {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.contact().create(contact, true);
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();
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
