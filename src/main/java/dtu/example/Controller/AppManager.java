package dtu.example.Controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.*;

public class AppManager {
    //private ArrayList<Project> projects;
    private HashMap<String, Project> projects;
    private HashMap<String, User> users; // Change to HashMap instead?
    private ArrayList<FixedActivity> otherActivities;
    private User activeUser; // The user that is currently logged in
    private int projectCount;

    public User getActiveUser() {
        return activeUser;
    }

    public User getUser(String userName) {
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
        this.projects = new HashMap<>();
        this.users = new HashMap<>();
        this.otherActivities = new ArrayList<>();
    }

    public AppManager(ArrayList<Project> projects, ArrayList<User> users) {
        // Initialize arrayLists for testing or predifined data.
        this.projects = new HashMap<>();
        this.users = new HashMap<>();
        this.otherActivities = new ArrayList<>();

        for (User user : users) {
            this.users.put(user.getUserID().toLowerCase(), user); // Populate HashMap
        }

        for (Project project : projects) {
            this.projects.put(project.getProjectID(), project);
        }

        
    }

    public void setActiveUser(User user) {
        this.activeUser = user;
    }

    private Project getProjectById(String projectID) {
        return projects.get(projectID);
    }

    private Project getProjectByName(String projectName) {
        for (var entry: this.projects.entrySet()){
            // in this case the keu is project id and value is our projects.
            if (entry.getValue().getProjectName().equals(projectName)){
                return entry.getValue();
            }
        }
        return null;
        
    }

    public StatusMessage createProject(String projectName) {
        // project id should be of form: {yr}{number} -> "22001"
        if (projectName.equals("")) { // guard clause
            return new StatusMessage(false, "Project name cannot be empty!");
        }

        if (this.getProjectByName(projectName) == null) {
            this.projectCount++;
            int currentYear = Year.now().getValue() % 100; // Get last two digits of the year
            String ID = String.format("%02d%03d", currentYear, this.projectCount);
            Project project = new Project(ID, projectName);
            this.projects.put(ID, project);
            return new StatusMessage(true, "Project with name " + projectName + " and id: " + ID + " was successfully created!");
        }

        // TODO: Implement error messages
        return new StatusMessage(false, "Project already exists!");
    }

    public StatusMessage createProjectActivity(String projectName, String activityName){
        if (getActiveUser() == null) {
            return new StatusMessage(false, "Error: No user is logged in.");
        }

        if(projectName.equals("")){
            return new StatusMessage(false, "Error: Project name cannot be empty!");
        } 
        
        if (activityName.equals("")){
            return new StatusMessage(false, "Error: Activity name cannot be empty!");
        }

        if (getProject(projectName) == null) {
            return new StatusMessage(false, "Error: Project does not exist!");
        }
        
        if (getProject(projectName).getProjectLead() != null && getActiveUser() != getProject(projectName).getProjectLead()) {
            return new StatusMessage(false, "Error: Logged in user not projectleader.");
        }

        ProjectActivity activity = new ProjectActivity(activityName);
        getProjectByName(projectName).addActivity(activity);
        return new StatusMessage(true, "Activity has been created.");
    }

    public Project getProject(String projectInput){
        Project project;
        // Accepts both id-format and project name

        // Determines if the projectInput is string of 5 integers
        if (projectInput.matches("\\d{5}")) {
            project = this.getProjectById(projectInput);
        } else {
            project = this.getProjectByName(projectInput);
        }

        return project;
    }


}
