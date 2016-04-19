package ru.stqa.pft.mantis.Model;

/**
 * Created by oleg on 19.04.16.
 */
public class Project {
    private int id;
    private  String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project whitId(int id) {
        this.id = id;
        return this;
    }

    public Project whitName(String name) {
        this.name = name;
        return this;
    }
}
