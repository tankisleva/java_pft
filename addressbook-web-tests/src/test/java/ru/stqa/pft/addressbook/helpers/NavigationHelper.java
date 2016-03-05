package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by oleg on 28.02.16.
 */
public class NavigationHelper extends BaseHelper{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }


    public void gotoGroupPage() {
       click(By.linkText("groups"));
    }


    public void returnHome() {
        click(By.linkText("home"));
    }
}
