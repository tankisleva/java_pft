package ru.stqa.pft.tests;


import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by oleg on 20.04.16.
 */
public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(5);
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId =  create(newIssue);
        Set <Issue> newIssues = getIssues();
        oldIssues.add(newIssue.whitId(issueId));
        assertEquals(newIssues,oldIssues);
    }



}

