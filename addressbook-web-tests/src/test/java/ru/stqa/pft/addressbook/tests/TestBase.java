package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.helpers.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by oleg on 26.02.16.
 */
public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

    Logger loger = LoggerFactory.getLogger(TestBase.class);


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

     @BeforeMethod
    public void logTestStart(Method m,Object[] p){
         loger.info("Start test " + m.getName() + " with parameters "+ Arrays.asList(p));
     }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        loger.info("End test " + m.getName());
    }

    public void verifyGroupListInUi() {

        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }


    public void verifyContactListInUi() {

        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream().map((c) -> new ContactData().withId(c.getId()).withFirstname(c.getFirstname()).
            withLastname(c.getLastname()).withHomeadress(c.getHomeadress()))
                    .collect(Collectors.toSet())));
        }
    }



    public String generateText(int Length) {

        String[] lettersName = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "g", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z" };

        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(Length);

        for (int i = 0; i < Length; i++)
            sb.append(lettersName[rnd.nextInt(lettersName.length)]);

        return sb.toString();


    }
}
