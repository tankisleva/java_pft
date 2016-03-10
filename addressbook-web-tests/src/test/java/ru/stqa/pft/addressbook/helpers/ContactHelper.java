package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.internal.Streams;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 28.02.16.
 */
public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void fillContactForm(ContactData contactData,boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getUsername());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getHomeadress());
        type(By.name("mobile"),contactData.getMobilenumber());
        if (creation) {
            if (isElementPresent(By.name("new_group"))) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroupname());
            }

        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }



    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void selectContact(int i){
        wd.findElements(By.name("selected[]")).get(i).click();
//        click(By.name("selected[]"));
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

    public boolean isThereContact() {

        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contactData, boolean g) {

      initContactCreation();
       fillContactForm((contactData), g);
       submitContactCreation();
    }


    public List<ContactData> getContactList(){

        WebElement table = wd.findElement(By.id("maintable"));
        List<WebElement> rows = table.findElements(By.name("entry"));
        List<ContactData> contacts =  new ArrayList<>();

        for (WebElement row: rows)
        {
            String firstName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String lastName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(firstName,lastName,null,null,null,null,null,id);
            contact.setFirstname(lastName);
            contact.setLastname(firstName);
            contacts.add(contact);
        }

        return contacts;

    }
}
