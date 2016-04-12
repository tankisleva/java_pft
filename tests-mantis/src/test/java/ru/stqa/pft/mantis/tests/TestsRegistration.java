package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.Model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by oleg on 12.04.16.
 */
public class TestsRegistration extends TestBase {

    @BeforeMethod
    public void startMailServer() throws Exception{
        app.mail().start();
    }

    @Test
    public void testRegistration() throws Exception {
       app.registration().start("user2","user2@localhost.localdomain");
       List<MailMessage> mailMessages =  app.mail().waitForMail(2,10000);

    }


    @BeforeMethod(alwaysRun = true)
    public void stopMailServer() throws Exception {
        app.mail().stop();
    }
}
