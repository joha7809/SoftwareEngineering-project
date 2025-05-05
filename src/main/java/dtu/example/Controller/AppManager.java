package dtu.example.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.*;

public class AppManager {
    private ArrayList<Project> projects;
    private HashMap<String, User> users; // Change to HashMap instead?
    private ArrayList<OtherActivity> otherActivities;
    private User activeUser; // The user that is currently logged in

    public User getActiveUser() {
        return activeUser;
    }

    public User getUser(String userName) {
        // for (User user : users) {
        //     if (user.getUserID().equalsIgnoreCase(userName)) { // ignore case if you want
        //         return user;
        //     }
        // }
        // return null; // not found
        return users.get(userName.toLowerCase());
    }

    public StatusMessage createUser(String userID) {
        if (this.getUser(userID) != null) {
            return new StatusMessage(false, "Error creating user. User with ID: " + userID + " already exists.");
        }
        users.put(userID.toLowerCase(), new User(userID));
        return new StatusMessage(true, "User created with ID: " + userID);
    }

    public AppManager() {
        // Initialize arrayLists
        this.projects = new ArrayList<>();
        this.users = new HashMap<>();
        this.otherActivities = new ArrayList<>();
    }

    public AppManager(ArrayList<Project> projects, ArrayList<User> users) {
        // Initialize arrayLists for testing or predifined data.
        this.projects = projects;
        this.users = new HashMap<>();
        for (User user : users) {
            this.users.put(user.getUserID().toLowerCase(), user); // Populate HashMap
        }
        this.otherActivities = new ArrayList<>();
    }

    public void setActiveUser(User user) {
        this.activeUser = user;
    }

    public Project getProject(String projectID) {
        for (Project project : projects) {
            if (project.getProjectID().equals(projectID)) {
                return project;
            }
        }
        return null;
    }

    public StatusMessage createProject(String projectName) {
        if (projectName.equals("")) { // guard clause
            return new StatusMessage(false, "Project name cannot be empty!");
        }

        if (this.getProject(projectName) == null) {
            Project project = new Project(projectName);
            this.projects.add(project);
            return new StatusMessage(true, "Project with name " + projectName + " was successfuly created!");
        }

        // TODO: Implement error messages
        return new StatusMessage(false, "Project already exists!");
    }

}
