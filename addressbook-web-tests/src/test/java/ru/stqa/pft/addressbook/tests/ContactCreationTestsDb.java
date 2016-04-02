package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 02.04.16.
 */
public class ContactCreationTestsDb extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            GroupData newGroup = new GroupData().withName("test").withHeader("test2").withFooter("test3");
            app.group().create(newGroup);
        }
    }

    @Test
    public void testContactCreationDb() {
        Groups groups = app.db().groups();
        app.goTo().home();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
                .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                .withMobilenumber("79177121162").inGroup(groups.iterator().next());

        app.contact().create(contact, true);
        Contacts after = app.db().contacts();
        app.goTo().home();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
        verifyContactListInUi();
    }
}
