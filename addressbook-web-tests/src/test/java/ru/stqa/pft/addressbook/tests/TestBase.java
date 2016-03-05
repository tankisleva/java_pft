package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.helpers.ApplicationManager;

/**
 * Created by oleg on 26.02.16.
 */
public class TestBase {
    protected  ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);




    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }



    @AfterMethod
    public void tearDown() {
        app.stop();
    }


}
