package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by oleg on 28.02.16.
 */
public class ApplicationManager {

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    WebDriver wd;
    private String browser;
    private  final Properties properties;

    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }




    public void stop() {
        wd.quit();
    }


    public void init() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
        if (properties.getProperty("browser").equals("FIREFOX")) {
            wd = new FirefoxDriver();
//        } else if (browser.equals(BrowserType.CHROME)) {
//            wd = new ChromeDriver();
        }
        else if (properties.getProperty("browser").equals("CHROME")){
            wd = new ChromeDriver();
        } else {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.url"));
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }


    public GroupHelper group() {
        return groupHelper;
    }


    public NavigationHelper goTo() {
        return navigationHelper;
    }


    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
}
