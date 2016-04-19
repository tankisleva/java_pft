package ru.stqa.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.Model.Issue;
import ru.stqa.pft.mantis.Model.Project;


import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by oleg on 19.04.16.
 */
public class SoapTests extends TestBase{

    @Test
    public void getProjects() throws  Exception {
        Set<Project> projects =  app.soap().getProjects();
        System.out.println(projects.size());
        for(Project project: projects)
            System.out.println(project.getName());
    }


    @Test
    public void testCreateIssue() throws  Exception {
        Set<Project> projects =  app.soap().getProjects();
        Issue issue = new Issue().whitSummary("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created =  app.soap().addIssue(issue);
        assertEquals(issue.getSummary(),created.getSummary());

    }
}
