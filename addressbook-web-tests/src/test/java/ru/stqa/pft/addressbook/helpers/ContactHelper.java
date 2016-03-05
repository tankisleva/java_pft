package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by oleg on 28.02.16.
 */
public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getUsername());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getHomeadress());
        type(By.name("mobile"),contactData.getMobilenumber());
    }


    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }



    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void selectContact(){
        click(By.name("selected[]"));
    }


    public void deleteSelectionContacts(){
        click(By.cssSelector("input[value='Delete']"));
    }

    public void editSelectionContacts(){
        click(By.cssSelector("img[alt='Edit']"));
    }

    public void deleteContacct(){
        click(By.cssSelector("input[value='Delete']"));
    }


    public void updateContact(){
        click(By.cssSelector("input[value='Update']"));
    }

}
