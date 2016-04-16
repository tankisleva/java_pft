package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by oleg on 12.04.16.
 */
public class AuthorizationHelper extends BaseHelper {

    public AuthorizationHelper(ApplicationManager app) throws Exception {
        super(app);
    }


    public void login(String username, String password){
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"),username);
        type(By.name("password"),password);
        click(By.cssSelector("input[value='Login']"));
    }


}
