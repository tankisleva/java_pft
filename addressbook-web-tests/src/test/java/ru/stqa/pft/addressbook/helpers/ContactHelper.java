package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContactById(int id){
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
//        click(By.name("selected[]"));
    }


    public void deleteSelectionContacts(){
        click(By.cssSelector("input[value='Delete']"));
    }

    public void editSelectionContacts(int i){
        wd.findElements(By.name("entry")).get(i).findElement(By.cssSelector("img[alt='Edit']")).click();
    }

    public void editSelectionContactById(int id){
        wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
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

    public void create(ContactData contactData, boolean c) {
      initContactCreation();
       fillContactForm((contactData), c);
       submitContactCreation();
        contactCache =  null;
    }


    public void modify(ContactData contact,boolean c) {
        editSelectionContactById(contact.getId());
        fillContactForm((contact), c);
        updateContact();
        contactCache =  null;
    }


    public void delete(int index) {
        selectContact(index);
        editSelectionContacts(index);
       deleteContacct();
    }


    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContacct();
        isAlertPresent();
        wd.switchTo().alert().accept();
        contactCache =  null;
    }


    public List<ContactData> list(){

        WebElement table = wd.findElement(By.id("maintable"));
        List<WebElement> rows = table.findElements(By.name("entry"));
        List<ContactData> contacts =  new ArrayList<>();

        for (WebElement row: rows)
        {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String firstName = cells.get(1).getText();
            String lastName = cells.get(2).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withFirstname(lastName).withLastname(firstName).withId(id));
        }

        return contacts;

    }


//    public Set<ContactData> all(){
//
//        WebElement table = wd.findElement(By.id("maintable"));
//        List<WebElement> rows = table.findElements(By.name("entry"));
//        Set<ContactData> contacts =  new HashSet<>();
//
//        for (WebElement row: rows)
//        {
//            List<WebElement> cells = row.findElements(By.tagName("td"));
//            String firstName = cells.get(1).getText();
//            String lastName = cells.get(2).getText();
//            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
//            contacts.add(new ContactData().withFirstname(lastName).withLastname(firstName).withId(id));
//        }
//
//        return contacts;
//
//    }

   private Contacts contactCache =  null;

    public Contacts all(){
        if (contactCache != null){
            contactCache =  new Contacts(contactCache);
        }

        WebElement table = wd.findElement(By.id("maintable"));
        List<WebElement> rows = table.findElements(By.name("entry"));
        contactCache =  new Contacts();

        for (WebElement row: rows)
        {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String firstName = cells.get(1).getText();
            String lastName = cells.get(2).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withFirstname(lastName).withLastname(firstName).withId(id));
        }

        return new Contacts(contactCache);

    }
}
