package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;


import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 05.04.16.
 */
public class ContactsAddGroups extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(generateText(8)).withHeader("test2").withFooter("test3"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            ContactData contact = new ContactData().withFirstname(generateText(6)).withLastname(generateText(8))
                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
                    .withMobilenumber("79177121162");

            app.contact().create(contact, true);
        }

    }


    @Test
    public void testAddContactToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactsGroups beforeContactsGroups = app.db().contactsGroups();
        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();
        Contacts beforeContacts = group.getContacts();
        Groups beforeGroups = contact.getGroups();
        int contactId = contact.getId();
        String groupName = group.getName();
        if (beforeGroups.size() != 0) {
            Set <GroupData> selectGroups = beforeGroups.stream().filter((g) -> !g.getName().equals(groupName))
                    .collect(Collectors.toSet());

            if (selectGroups.size() == 0) {
                GroupData newGroup = new GroupData().withName(generateText(8)).withHeader("test2").withFooter("test3");
                app.goTo().groupPage();
                app.group().create(newGroup);
                app.goTo().home();
                app.contact().selectContactById(contactId);
                app.contact().addToGroup(newGroup.getName());
                ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(newGroup.getId());
                ContactsGroups afterContactsGroups = app.db().contactsGroups();
                Groups afterGroups = app.db().contacts().stream().filter((c)->c.getLastname().equals(contact.getLastname())).
                        iterator().next().getGroups();
                Contacts afterContacts = app.db().groups().stream().filter((g)->g.getName().equals(group.getName())).iterator().next().getContacts();
                assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withAdded(contactGroup.withGroupId(afterContactsGroups.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
                assertThat(afterGroups,equalTo(beforeGroups.withAdded(newGroup.withId(afterGroups.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
                assertThat(afterContacts,equalTo(beforeContacts.withAdded(contact.withId(afterContacts.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
            } else {
                if (app.db().contactsGroups().stream().filter((c)->c.getContactId()!= contactId).collect(Collectors.toSet()).size() !=0) {
                    GroupData newGroup1 = selectGroups.iterator().next();
                    app.contact().selectContactById(contactId);
                    app.contact().addToGroup(newGroup1.getName());
                    ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(newGroup1.getId());
                    ContactsGroups afterContactsGroups = app.db().contactsGroups();
                    Groups afterGroups = app.db().contacts().stream().filter((c) -> c.getLastname().equals(contact.getLastname())).
                            iterator().next().getGroups();
                    Contacts afterContacts = app.db().groups().stream().filter((g) -> g.getName().equals(group.getName())).iterator().next().getContacts();
                    assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withAdded(contactGroup.withGroupId(afterContactsGroups.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
                    assertThat(afterGroups, equalTo(beforeGroups.withAdded(newGroup1.withId(afterGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
                    assertThat(afterContacts, equalTo(beforeContacts.withAdded(contact.withId(afterContacts.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
                }

                else {

                    GroupData newGroup2 = new GroupData().withName(generateText(8)).withHeader("test2").withFooter("test3");
                    app.goTo().groupPage();
                    app.group().create(newGroup2);
                    app.goTo().home();
                    app.contact().selectContactById(contactId);
                    app.contact().addToGroup(newGroup2.getName());
                    ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(newGroup2.getId());
                    ContactsGroups afterContactsGroups = app.db().contactsGroups();
                    Groups afterGroups = app.db().contacts().stream().filter((c) -> c.getLastname().equals(contact.getLastname())).
                            iterator().next().getGroups();
                    Contacts afterContacts = app.db().groups().stream().filter((g) -> g.getName().equals(group.getName())).iterator().next().getContacts();
                    assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withAdded(contactGroup.withGroupId(afterContactsGroups.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
                    assertThat(afterGroups, equalTo(beforeGroups.withAdded(newGroup2.withId(afterGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
                    assertThat(afterContacts, equalTo(beforeContacts.withAdded(contact.withId(afterContacts.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

                }

            }
        } else {

            app.contact().selectContactById(contactId);
            app.contact().addToGroup(group.getName());
            ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(group.getId());
            ContactsGroups afterContactsGroups = app.db().contactsGroups();
            Groups afterGroups = app.db().contacts().stream().filter((c)->c.getLastname().equals(contact.getLastname())).
                    iterator().next().getGroups();
            Contacts afterContacts = app.db().groups().stream().filter((g)->g.getName().equals(group.getName())).iterator().next().getContacts();
            assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withAdded(contactGroup.withGroupId(afterContactsGroups.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
            assertThat(afterGroups,equalTo(beforeGroups.withAdded(group.withId(afterGroups.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
            assertThat(afterContacts,equalTo(beforeContacts.withAdded(contact.withId(afterContacts.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
        }

    }
}