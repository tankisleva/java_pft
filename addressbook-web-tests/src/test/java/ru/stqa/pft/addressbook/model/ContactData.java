package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String username;
    private final String company;
    private final String homeadress;
    private final String mobilenumber;

    public ContactData(String firstname, String lastname, String username, String company, String homeadress, String mobilenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.company = company;
        this.homeadress = homeadress;
        this.mobilenumber = mobilenumber;
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
}
