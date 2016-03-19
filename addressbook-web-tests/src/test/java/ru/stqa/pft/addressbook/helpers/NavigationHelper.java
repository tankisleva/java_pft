package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Created by oleg on 28.02.16.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }


    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("New"))) {
            return;
        }

        click(By.linkText("groups"));
    }


    public void home() {
        if (isElementPresent(By.id("maintable")))
        {
            return;
        }

        click(By.linkText("home"));

    }
}
