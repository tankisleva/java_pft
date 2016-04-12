package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * Created by oleg on 12.04.16.
 */
public class TestChangePassword extends TestBase {

    @BeforeMethod
    public void startMailServer() throws Exception{
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws Exception {

    }


    @BeforeMethod(alwaysRun = true)
    public void stopMailServer() throws Exception {
        app.mail().stop();
    }
}
