package ru.stqa.pft.addressbook.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oleg on 05.04.16.
 */

@Entity
@Table(name = "address_in_groups")
public class ContactsGroupsData {


    @Column(name = "id")
    @Id
    private int contactId;


    @Column(name = "group_id")
    private int groupId;

    public int getContactId() {
        return contactId;
    }

    public int getGroupId() {
        return groupId;
    }

    public ContactsGroupsData withContactId(int contactId) {
        this.contactId = contactId;
        return this;
    }

    public ContactsGroupsData withGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public String toString() {
        return "ContactsGroupsData{" +
                "contactId=" + contactId +
                ", groupId=" + groupId +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsGroupsData that = (ContactsGroupsData) o;

        if (contactId != that.contactId) return false;
        return groupId == that.groupId;

    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + groupId;
        return result;
    }
}
