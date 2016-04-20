package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * Created by oleg on 20.04.16.
 */
public class GitHubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("08adec86090677cef3ccda21e33ab77943d12432");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("tankisleva", "java_pft")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(commit);
            System.out.println(new RepoCommit.Smart(commit).message());
    }
}


}