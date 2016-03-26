package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;


/**
 * Created by oleg on 28.02.16.
 */
public class BaseHelper {



    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existintext = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existintext)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }

        }
    }



    protected void attach(By locator, File file) {

        if (file != null) {
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }

        }





    protected boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    protected   boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }




    protected void click(By locator) {
        wd.findElement(locator).click();
    }


}

