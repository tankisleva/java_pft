package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 06.04.16.
 */
public class ContactsRemoveFromGroups extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(generateText(8)).withHeader("test2").withFooter("test3"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().home();
           GroupData group = app.db().groups().iterator().next();
            ContactData contact = new ContactData().withFirstname("Oleg").withLastname(generateText(8))
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162").inGroup(group);

            app.contact().create(contact, true);
        }

    }


    @Test
    public void testRemoveContactToGroup() throws Exception {
        Groups groups = app.db().groups();
        ContactData contact = app.db().contacts().iterator().next();
        ContactsGroups beforeContactsGroups = app.db().contactsGroups();
        GroupData SelectGroup = groups.iterator().next();
        Contacts beforeContacts = SelectGroup.getContacts();
        Groups beforeGroups = contact.getGroups();

        if (beforeContacts.size() != 0)
        {
            ContactData removeContact = beforeContacts.iterator().next();
            app.goTo().home();
            String title = "Select " + "("+removeContact.getFirstname()+ " " +removeContact.getLastname()+")";
            app.contact().removeFromGroup(SelectGroup.getName(), title);
            ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(removeContact.getId()).withGroupId(SelectGroup.getId());
            ContactsGroups afterContactsGroups = app.db().contactsGroups();
            Groups afterGroups = app.db().contacts().stream().filter((c) -> c.getLastname().equals(removeContact.getLastname())).
                    iterator().next().getGroups();
            Contacts afterContacts = app.db().groups().stream().filter((g) -> g.getName().equals(SelectGroup.getName())).iterator().next().getContacts();    ;
            assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withOut(contactGroup)));
            assertThat(afterGroups,equalTo(beforeGroups.withOut(SelectGroup)));
            assertThat(afterContacts, equalTo(beforeContacts.withOut(removeContact)));
        }

        else {
            app.goTo().home();
            ContactData contactForRemove = new ContactData().withFirstname(generateText(4)).withLastname(generateText(8))
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162").inGroup(SelectGroup);
            app.contact().create(contactForRemove, true);
            app.goTo().home();
            String title = "Select " + "("+contactForRemove.getFirstname()+ " " +contactForRemove.getLastname()+")";
            app.contact().removeFromGroup(SelectGroup.getName(), title);
            ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactForRemove.getId()).withGroupId(SelectGroup.getId());
            ContactsGroups afterContactsGroups = app.db().contactsGroups();
            Groups afterGroups = app.db().contacts().stream().filter((c) -> c.getLastname().equals(contact.getLastname())).
                    iterator().next().getGroups();
            Contacts afterContacts = app.db().groups().stream().filter((g) -> g.getName().equals(SelectGroup.getName())).iterator().next().getContacts();
            assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withOut(contactGroup)));
            assertThat(afterGroups,equalTo(beforeGroups.withOut(SelectGroup)));
            assertThat(afterContacts, equalTo(beforeContacts.withOut(contactForRemove)));

        }

}

}