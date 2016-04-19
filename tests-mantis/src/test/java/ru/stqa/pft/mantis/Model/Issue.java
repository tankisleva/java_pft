package ru.stqa.pft.mantis.Model;

/**
 * Created by oleg on 19.04.16.
 */
public class Issue {
    private int id;
    private String summary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public String getSummary() {
        return summary;
    }


    public Project getProject() {
        return project;
    }

    public Issue whitId(int id) {
        this.id = id;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;

    }

    public Issue whitSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }



}
