package dtu.example.Controller;

import java.util.ArrayList;
import dtu.example.model.*;
import dtu.example.Controller.command_returns.StatusMessage;

public class AppManager {
    private final AppController controller;
    private final AppState state;

    public AppManager() {
        this.state = new AppState();
        this.controller = new AppController(state);
    }

    public AppManager(ArrayList<Project> projects, ArrayList<User> users) {
        this.state = new AppState();
        this.controller = new AppController(state);

        // Initialize with predefined data
        for (User user : users) {
            state.addUser(user);
        }

        for (Project project : projects) {
            state.addProject(project);
        }
    }

    public User getActiveUser() {
        return controller.getActiveUser();
    }

    public User getUser(String userName) {
        return controller.getUser(userName);
    }

    public StatusMessage createUser(String userID) {
        return controller.createUser(userID);
    }

    public void setActiveUser(User user) {
        controller.setActiveUser(user);
    }

    public Project getProject(String projectInput) {
        return controller.getProject(projectInput);
    }

    public StatusMessage createProject(String projectName) {
        return controller.createProject(projectName);
    }

    public StatusMessage createProjectActivity(String projectName, String activityName) {
        return controller.createProjectActivity(projectName, activityName);
    }
}
