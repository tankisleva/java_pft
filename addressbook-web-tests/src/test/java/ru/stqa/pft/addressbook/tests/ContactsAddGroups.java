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

/*  Если есть группы, в которые контакт не добавлен, я добавляю контакт в эти группы.
Если таких групп больше нет, то я добавляю новый контакт и добавляю его в группу. */

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
        // Получаем список контактов из базы данных
        Contacts contacts = app.db().contacts();

        // Получаем список групп из базы данных
        Groups groups = app.db().groups();

        //Получаем список групп и контактов из таблицы address_in_groups
        ContactsGroups beforeContactsGroups = app.db().contactsGroups();

        //Выбираем контакт, с которым будем работать
        ContactData contact = contacts.iterator().next();

        //Выбираем группу, в которую будем добавлять контакт
        GroupData group = groups.iterator().next();

        //Проверяем сколько у этой группы контактов, до добавления туда контакта
        Contacts beforeContacts = group.getContacts();

        //Проверяем в сколько групп входит контакт до добавления  контакта
        Groups beforeGroups = contact.getGroups();

        // Получаем  id выбранного контакта
        int contactId = contact.getId();

        // Получаем имя группы (все имена групп уникальные)
        String groupName = group.getName();

        // Условие, если контакт состоит в группе/группах
        if (beforeGroups.size() != 0) {

            // Формируем новое множество из групп, где имя нашый вабранной группы, в которую мы
            // хотим добавить контакт, не совпадае с именем групп, в которые
            // входит контакт

            Set <GroupData> selectGroups = beforeGroups.stream().filter((g) -> !g.getName().equals(groupName))
                    .collect(Collectors.toSet());

            //Если такое множество равно нулю, то значит контакт уже есть в этой группе и мы создаем другую группу и добавляем
            // контакт туда

            if (selectGroups.size() == 0) {
                GroupData newGroup = new GroupData().withName(generateText(8)).withHeader("test2").withFooter("test3");
                app.goTo().groupPage();
                app.group().create(newGroup);
                app.goTo().home();
                app.contact().selectContactById(contactId);
                app.contact().addToGroup(newGroup.getName());

                //Формируем модель для добавления связи в address_in_groups

                ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(newGroup.getId());

                // Получаем список связей из address_in_groups после добавления контакта в новую группу
                ContactsGroups afterContactsGroups = app.db().contactsGroups();

                // Получаем из базы данных список групп именно нашего выбранного контакта
                Groups afterGroups = app.db().contacts().stream().filter((c)->c.getLastname().equals(contact.getLastname())).
                        iterator().next().getGroups();

                // Получаем список контактов, которые вошли именно в нашу новую добавленную группу
                Contacts afterContacts = app.db().groups().stream().filter((g)->g.getName().equals(group.getName())).iterator().next().getContacts();

                // Проверяем, что в таблицу address_in_groups добавлиась новая связь
                assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withAdded(contactGroup.withGroupId(afterContactsGroups.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));

                // Проверяем,  что у контакта появилась новая группа, в которую он входит
                assertThat(afterGroups,equalTo(beforeGroups.withAdded(newGroup.withId(afterGroups.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

                // Проверяем, что у группы появился новый контакт.
                assertThat(afterContacts,equalTo(beforeContacts.withAdded(contact.withId(afterContacts.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
            } else {

                // Если множество выше не равно нулю, значит выбираем любую группу из множества и добавляем контакт в эту группу
                    GroupData newGroup1 = selectGroups.iterator().next();
                    app.contact().selectContactById(contactId);
                    app.contact().addToGroup(newGroup1.getName());
                    ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(newGroup1.getId());
                    ContactsGroups afterContactsGroups = app.db().contactsGroups();
                    Groups afterGroups = app.db().contacts().stream().filter((c) -> c.getLastname().equals(contact.getLastname())).
                            iterator().next().getGroups();
                    Contacts afterContacts = app.db().groups().stream().filter((g) -> g.getName().equals(newGroup1.getName())).iterator().next().getContacts();
                    assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withAdded(contactGroup.withGroupId(afterContactsGroups.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
                    assertThat(afterGroups, equalTo(beforeGroups.withAdded(newGroup1.withId(afterGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
                    assertThat(afterContacts, equalTo(beforeContacts.withAdded(contact.withId(afterContacts.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
                }

//                else {
//
//                    GroupData newGroup2 = new GroupData().withName(generateText(8)).withHeader("test2").withFooter("test3");
//                    app.goTo().groupPage();
//                    app.group().create(newGroup2);
//                    app.goTo().home();
//                    app.contact().selectContactById(contactId);
//                    app.contact().addToGroup(newGroup2.getName());
//                    ContactsGroupsData contactGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(newGroup2.getId());
//                    ContactsGroups afterContactsGroups = app.db().contactsGroups();
//                    Groups afterGroups = app.db().contacts().stream().filter((c) -> c.getLastname().equals(contact.getLastname())).
//                            iterator().next().getGroups();
//                    Contacts afterContacts = app.db().groups().stream().filter((g) -> g.getName().equals(group.getName())).iterator().next().getContacts();
//                    assertThat(afterContactsGroups, equalTo(beforeContactsGroups.withAdded(contactGroup.withGroupId(afterContactsGroups.stream().mapToInt((g) -> g.getGroupId()).max().getAsInt()))));
//                    assertThat(afterGroups, equalTo(beforeGroups.withAdded(newGroup2.withId(afterGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
//                    assertThat(afterContacts, equalTo(beforeContacts.withAdded(contact.withId(afterContacts.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
//
//                }


        } else {

            // Если в выбранном контакте нет ни одной группы, то добавляем выбранную группу в выбранный контакт
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