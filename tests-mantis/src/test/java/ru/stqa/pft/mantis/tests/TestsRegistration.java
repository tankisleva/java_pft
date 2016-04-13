package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.Model.MailMessage;

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
        String email = "user3@localhost.localdomain";
        String password = "password";
        String user = "user3";
        app.registration().start(user, email);
        List<MailMessage> mailMessages =  app.mail().waitForMail(2,240000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink,password);
        app.newSession().login(user,password);
        app.newSession().isLoggedInAs(user);


    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
       MailMessage mailMessage = mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
       return  regex.getText(mailMessage.text);
    }


    @BeforeMethod(alwaysRun = true)
    public void stopMailServer() throws Exception {
        app.mail().stop();
    }
}
