package dtu.example.Controller;

import java.util.ArrayList;

import dtu.example.model.OtherActivity;
import dtu.example.model.Project;
import dtu.example.model.User;

public class AppManager {
    private ArrayList<Project> projects;
    private ArrayList<User> users;
    private ArrayList<OtherActivity> otherActivities;
    private User activeUser; // The user that is currently logged in

    public User getActiveUser() {
        return activeUser;
    }

    public AppManager() {
        //Initialize arrayLists
        this.projects = new ArrayList<>();
        this.users = new ArrayList<>();
        this.otherActivities = new ArrayList<>();
    }

    public void setActiveUser(User user){
        this.activeUser = user;
    }

    public Project getProject(String projectID){
        for (Project project : projects){
            if (project.getProjectID().equals(projectID)){
                return project;
            }
        }
        return null;
    }

    public void addProject(Project project){
        // Check if the project already exists
        for (Project p : projects) {
            if (p.getProjectID().equals(project.getProjectID())) {
                return; // Project already exists, do not add
            }
        }
        // If it doesn't exist, add it
        projects.add(project);
    }

}

