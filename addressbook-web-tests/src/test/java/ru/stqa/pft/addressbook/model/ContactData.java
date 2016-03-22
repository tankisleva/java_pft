package ru.stqa.pft.addressbook.model;

public class ContactData {
    private  String firstname;
    private  String lastname;
    private  String username;
    private  String company;
    private  String homeadress;
    private  String mobilenumber;
    private  String homenumber;
    private  String worknumber;
    private  String groupname;
    private  String email;
    private  String email1;
    private  String email2;
    private int id = Integer.MAX_VALUE;


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

    public String getHomeNumber() {
        return homenumber;
    }

    public String getWorkNumber() {
        return worknumber;
    }

    public String getGroupname() {
        return groupname;
    }


    public String getEmail() {
        return email;
    }

    public String getEmail1() {
        return email1;
    }



    public String getEmail2() {
        return email2;
    }

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withGroupname(String groupname) {
        this.groupname = groupname;
        return this;
    }

    public ContactData withMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
        return this;
    }

    public ContactData withHomeNumber(String homenumber) {
        this.homenumber = homenumber;
        return this;
    }

    public ContactData withWorkNumber(String worknumber) {
        this.worknumber = worknumber;
        return this;
    }

    public ContactData withHomeadress(String homeadress) {
        this.homeadress = homeadress;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withUsername(String username) {
        this.username = username;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }


    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withtEmail2(String email2) {
        this.email2 = email2;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + id;
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
