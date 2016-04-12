package ru.stqa.pft.mantis.appmanager;


import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by oleg on 12.04.16.
 */
public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app){
        this.app = app;
        ftp = new FTPClient();
    }
}
