package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void editSelectionContacts(int i){
        wd.findElements(By.name("entry")).get(i).findElement(By.cssSelector("img[alt='Edit']")).click();
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
    }


    public void modify(int index, ContactData modifyContact,boolean c) {
       selectContact(index);
        editSelectionContacts(index);
        fillContactForm((modifyContact), c);
        updateContact();
    }


    public void delete(int index) {
        selectContact(index);
        editSelectionContacts(index);
       deleteContacct();
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
}
