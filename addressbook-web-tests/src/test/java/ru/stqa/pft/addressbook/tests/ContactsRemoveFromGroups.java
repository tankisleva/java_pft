package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 06.04.16.
 */
public class ContactsRemoveFromGroups extends TestBase {


    @Test
    public void testRemoveContactToGroup()  {
        Contacts contacts =  app.db().contacts();
        Groups groups =  app.db().groups();
        ContactsGroups before =  app.db().contactsGroups();
        int  contactId = contacts.iterator().next().getId();
        GroupData group = groups.iterator().next();
        int groupId = group.getId();
        String groupName =  group.getName();
        ContactsGroupsData contactsGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(groupId);
        app.contact().removeFromGroup(groupName,contactId);
        ContactsGroups after =  app.db().contactsGroups();
        assertThat(after, equalTo(before.withOut(contactsGroup)));
    }
}
