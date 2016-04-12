package ru.stqa.pft.mantis.Model;

import ru.stqa.pft.mantis.appmanager.MailHelper;

/**
 * Created by oleg on 12.04.16.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text =  text;
    }
}
