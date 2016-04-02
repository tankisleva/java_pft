package ru.stqa.pft.addressbook.tests;


import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.helpers.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Method;
import java.util.Arrays;
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
}
