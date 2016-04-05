package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private  String firstname;

    @Expose
    @Column(name = "lastname")
    private  String lastname;

    @Expose
    @Column(name = "nickname")
    private  String username;

    @Expose
    @Column(name = "company")
    private  String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private  String homeadress;

    @Type(type = "text")
    @Column(name = "mobile")
    private  String mobilenumber;

    @Type(type = "text")
    @Column(name = "home")
    private  String homenumber;

    @Type(type = "text")
    @Column(name = "work")
    private  String worknumber;


    @Type(type = "text")
    @Expose
    @Column(name = "email")
    private  String email;

    @Type(type = "text")
    @Column(name = "email2")
    private  String email1;

    @Type(type = "text")
    @Column(name = "email3")
    private  String email2;

    @Transient
    private  String allPhones;

    @Transient
    private String allEmails;

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Type(type = "text")
    @Column(name = "photo")
    private String photo;

    @ManyToMany
    @JoinTable(name = "address_in_groups",joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups =  new HashSet<GroupData>();

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
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

    public String getHomeNumber() {
        return homenumber;
    }

    public String getWorkNumber() {
        return worknumber;
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

    public File getPhoto() {
        if(photo != null) {
            return new File(photo);
        }
        return null;
    }


    public int getId() {
        return id;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
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


    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
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
