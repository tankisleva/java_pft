package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 05.04.16.
 */
public class ContactsAddGroups extends TestBase{

//    @BeforeClass
//    public void ensurePreconditions(){
//        if (app.db().groups().size() == 0)
//        {   app.goTo().groupPage();
//            app.group().create(new GroupData().withName("test").withHeader("test2").withFooter("test3"));
//        }
//
//         if (app.db().contacts().size() == 0){
//            app.goTo().home();
//             Groups groups = app.db().groups();
//            ContactData contact = new ContactData().withFirstname("Oleg").withLastname("Malyshev")
//                    .withUsername("tanki_sleva").withCompany("wamba").withHomeadress("parkway yraeva")
//                    .withMobilenumber("79177121162").inGroup(groups.iterator().next());
//
//            app.contact().create(contact, true);
//        }
//    }



    @Test
    public void testAddContactToGroup()  {
        Contacts contacts =  app.db().contacts();
        Groups groups =  app.db().groups();
        ContactsGroups before =  app.db().contactsGroups();
        int  contactId = contacts.iterator().next().getId();
        GroupData group = groups.iterator().next();
        int groupId = group.getId();
        String groupName =  group.getName();
       ContactsGroupsData contactsGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(groupId);
        app.contact().selectContactById(contactId);
        app.contact().addToGroup(groupName);
        ContactsGroups after =  app.db().contactsGroups();
        assertThat(after, equalTo(before.withAdded(contactsGroup)));



    }
}
