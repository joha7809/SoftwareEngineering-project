package dtu.example.Controller;

import java.util.ArrayList;
import dtu.example.model.*;

public class AppManager {
    private ArrayList<Project> projects;
    private ArrayList<User> users; //Change to HashMap instead?
    private ArrayList<OtherActivity> otherActivities;
    private User activeUser; // The user that is currently logged in

    public User getActiveUser() {
        return activeUser;
    }

    public User getUser(String userName){
        for (User user : users) {
            if (user.getUserID().equalsIgnoreCase(userName)) { // ignore case if you want
                return user;
            }
        }
        return null; // not found
    }

    public StatusMessage createUser(String userID){
        if (this.getUser(userID) != null){
            return new StatusMessage(false, "Error creating user. User with ID: " + userID + " already exists.");
        }
        users.add(new User(userID));
        return new StatusMessage(true, "User created with ID: " + userID);
    }

    public AppManager() {
        //Initialize arrayLists
        this.projects = new ArrayList<>();
        this.users = new ArrayList<>();
        this.otherActivities = new ArrayList<>();
    }

    public AppManager(ArrayList<Project> projects, ArrayList<User> users) {
        //Initialize arrayLists
        this.projects = projects;
        this.users = users;
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


    public StatusMessage createProject(String projectName){
        if (projectName.equals("")){ //guard clause
            return new StatusMessage(false, "Project name cannot be empty!");
        }

        if (this.getProject(projectName) == null){
            Project project = new Project(projectName);
            this.projects.add(project);
            return new StatusMessage(true, "Project with name " + projectName + " was successfuly created!");
        }
        
        // TODO: Implement error messages
        return new StatusMessage(false, "Project already exists!");
    }

}

