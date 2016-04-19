package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.Model.Issue;
import ru.stqa.pft.mantis.Model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by oleg on 19.04.16.
 */
public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app){
        app =  this.app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("Administrator","root");
       return Arrays.asList(projects).stream().map((p)->new Project().whitId(p.getId().intValue()).whitName(p.getName())).collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
    }

    public Issue addIssue(Issue issue) throws RemoteException, MalformedURLException, ServiceException{
        MantisConnectPortType mc = getMantisConnect();
        String categories[] = mc.mc_project_get_categories("Administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData =  new IssueData();
        issueData.setId(BigInteger.valueOf(issue.getId()));
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId =  mc.mc_issue_add("Administrator","root",issueData);
        IssueData createIssueData = mc.mc_issue_get("Administrator","root",issueId);
        return new Issue().whitId(createIssueData.getId().intValue()).whitSummary(createIssueData.getSummary())
                .withDescription(createIssueData.getDescription())
                .withProject(new Project().whitId(createIssueData.getProject().getId().intValue()).whitName(createIssueData.getProject().getName()));
    }

}
