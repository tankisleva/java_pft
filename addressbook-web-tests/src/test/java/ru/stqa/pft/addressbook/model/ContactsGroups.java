package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oleg on 05.04.16.
 */
public class ContactsGroups extends ForwardingSet<ContactsGroupsData> {

    private Set<ContactsGroupsData> delegate;

    public ContactsGroups(ContactsGroups contactsGroups) {
        this.delegate =  new HashSet<ContactsGroupsData>(contactsGroups.delegate());
    }

    public ContactsGroups(Collection<ContactsGroupsData> contactsGroups) {
        this.delegate =  new HashSet<ContactsGroupsData>(contactsGroups);
    }

    public ContactsGroups() {
        this.delegate =  new HashSet<ContactsGroupsData>();
    }

    @Override
    protected Set<ContactsGroupsData> delegate() {
        return delegate;
    }

    public ContactsGroups withAdded(ContactsGroupsData contactsGroup){
        ContactsGroups contactsGroups = new ContactsGroups(this);
        contactsGroups.add(contactsGroup);
        return contactsGroups;
    }


    public ContactsGroups withOut(ContactsGroupsData contactsGroup){
        ContactsGroups contactsGroups = new ContactsGroups(this);
        contactsGroups.remove(contactsGroup);
        return contactsGroups;
    }
}
