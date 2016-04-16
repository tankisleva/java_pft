package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.tests.TestBase;

/**
 * Created by oleg on 16.04.16.
 */
public class NavigationHelper extends BaseHelper {
    public NavigationHelper(ApplicationManager app) throws Exception{
        super(app);
    }

    public void Users(){
        click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    }

    public void clickUser(int id){
       click(By.cssSelector("a[href='manage_user_edit_page.php?user_id="+id+"']"));
    }


    public void resetPassword(){
        click(By.cssSelector("input[value='Reset Password']"));
    }


    public void cheangePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='UpdateUser']"));
    }
}
