package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

import dtu.example.model.Project;

public class ProjectOrganizer {

    private List<Project> projects = new ArrayList<>();

    public void createProject(String name)
    {
        projects.add(new Project(name));
    }

    public Project getProject()
    {
        return projects.get(0);
    }
    
}
