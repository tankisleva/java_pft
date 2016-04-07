package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by oleg on 28.02.16.
 */
public class ApplicationManager {



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

    }



}
