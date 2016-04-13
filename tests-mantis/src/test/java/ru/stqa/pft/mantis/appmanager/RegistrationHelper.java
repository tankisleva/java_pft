package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by oleg on 12.04.16.
 */
public class RegistrationHelper extends BaseHelper{

    public RegistrationHelper(ApplicationManager app) throws Exception{
        super(app);
    }


    public void start(String username, String email){
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='UpdateUser']"));
    }
}
