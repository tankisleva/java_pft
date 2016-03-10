package ru.stqa.pft.addressbook.model;

public class ContactData {
    private  String firstname;
    private  String lastname;
    private final String username;
    private final String company;
    private final String homeadress;
    private final String mobilenumber;
    private final String groupname;
    private int id;

    public ContactData(String firstname, String lastname, String username, String company, String homeadress, String mobilenumber, String groupname,int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.company = company;
        this.homeadress = homeadress;
        this.mobilenumber = mobilenumber;
        this.groupname = groupname;
        this.id = Integer.MAX_VALUE;
    }


    public ContactData(String firstname, String lastname, String username, String company, String homeadress, String mobilenumber, String groupname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.company = company;
        this.homeadress = homeadress;
        this.mobilenumber = mobilenumber;
        this.groupname = groupname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getCompany() {
        return company;
    }

    public String getHomeadress() {
        return homeadress;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getGroupname() {
        return groupname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }


}
