package dtu.example.model;

import java.util.HashMap;
import java.util.ArrayList;

public class AppState {
    private final HashMap<String, Project> projects;
    private final HashMap<String, User> users;
    private final ArrayList<FixedActivity> otherActivities;
    private User activeUser;

    public AppState() {
        this.projects = new HashMap<>();
        this.users = new HashMap<>();
        this.otherActivities = new ArrayList<>();
    }

    // Getters
    public User getActiveUser() {
        return activeUser;
    }

    public User getUser(String userName) {
        return users.get(userName.toLowerCase());
    }

    public Project getProjectById(String projectID) {
        return projects.get(projectID);
    }

    public Project getProjectByName(String projectName) {
        for (var entry: this.projects.entrySet()) {
            if (entry.getValue().getProjectName().equals(projectName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public HashMap<String, Project> getProjects() {
        return projects;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public ArrayList<FixedActivity> getOtherActivities() {
        return otherActivities;
    }

    public void setActiveUser(User user) {
        this.activeUser = user;
    }

    public void addUser(User user) {
        this.users.put(user.getUserID().toLowerCase(), user);
    }

    public void addProject(Project project) {
        this.projects.put(project.getProjectID(), project);
    }
} 