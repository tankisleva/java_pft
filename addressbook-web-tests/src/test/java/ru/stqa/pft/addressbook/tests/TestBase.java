package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.helpers.ApplicationManager;

/**
 * Created by oleg on 26.02.16.
 */
public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);




    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }



    @AfterSuite
    public void tearDown() {
        app.stop();
    }


}
