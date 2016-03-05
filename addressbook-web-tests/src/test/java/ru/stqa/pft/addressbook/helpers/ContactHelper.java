package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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

    public boolean isThereContact() {

        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contactData, boolean g) {

      initContactCreation();
       fillContactForm((contactData), g);
       submitContactCreation();
    }
}
