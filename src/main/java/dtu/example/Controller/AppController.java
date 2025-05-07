package dtu.example.Controller;

import java.io.ObjectInputFilter.Status;
import java.time.Year;
import dtu.example.model.*;
import dtu.example.Controller.command_returns.StatusMessage;

public class AppController {
    private final AppState state;

    public AppController() {
        this.state = new AppState();
    }

    public AppController(AppState state) {
        this.state = state;
    }

    public StatusMessage createUser(String userID) {
        if (userID.isBlank()){
            return new StatusMessage(false, "Error creating user. User ID cannot be empty.");
        }

        if (state.getUser(userID) != null) {
            return new StatusMessage(false, "Error creating user. User with ID: " + userID + " already exists.");
        }
        User newUser = new User(userID);
        state.addUser(newUser);
        return new StatusMessage(true, "User created with ID: " + userID);
    }

    public StatusMessage createProject(String projectName) {
        if (projectName.equals("")) {
            return new StatusMessage(false, "Project name cannot be empty!");
        }

        if (state.getProjectByName(projectName) != null) {
            return new StatusMessage(false, "Project already exists!");
        }
        Integer projectCount = state.getProjects().size()+1;
        // Make the id
        int currentYear = Year.now().getValue() % 100; // Returns the current year in the format 25 for 2025
        String ID = String.format("%02d%03d", currentYear, projectCount); // Ensures current year is with 2 decimals and count is with 3.


        Project project = new Project(projectName, ID);
        state.addProject(project);
        return new StatusMessage(true, "Project with name " + projectName + " and id: " + ID + " was successfully created!");
    }

    public StatusMessage createProjectActivity(String projectName, String activityName) {
        // Validation
        if (state.getActiveUser() == null) {
            return new StatusMessage(false, "Error: No user is logged in.");
        }
        
        if (activityName.equals("")) {
            return new StatusMessage(false, "Error: Activity name cannot be empty!");
        }

        Project project = getProject(projectName);
        
        if (project == null) {
            return StatusMessage.PROJECT_NOT_FOUND;
        }
        
        if (project.getProjectLead() != null && state.getActiveUser() != project.getProjectLead()) {
            return new StatusMessage(false, "Error: Logged in user not project leader.");
        }

        if (project.getActivity(activityName) != null) {
            return new StatusMessage(false, "Activity " + activityName + " already exists in project.");
        }

        // Business Logic
        ProjectActivity activity = new ProjectActivity(activityName);
        project.addActivity(activity);
        return new StatusMessage(true, "Activity " + activityName + " been successfully created.");
    }

    public Project getProject(String projectInput) {
        if (projectInput.matches("\\d{5}")) {
            return state.getProjectById(projectInput);
        } else {
            return state.getProjectByName(projectInput);
        }
    }

    public void setActiveUser(User user) {
        state.setActiveUser(user);
    }

    public User getActiveUser() {
        return state.getActiveUser();
    }

    public User getUser(String userName) {
        return state.getUser(userName);
    }

    public StatusMessage setActivityDescription(String project, String activity, String newDescription){
        Project p = this.getProject(project);
        if (p == null){
            return StatusMessage.PROJECT_NOT_FOUND;
        }
        
        ProjectActivity act = p.getActivity(activity);

        if (act == null) {
            return StatusMessage.error("Error: Activity not found.");
        }

        if (p.getProjectLead() != null && p.getProjectLead() != state.getActiveUser()){
            return StatusMessage.error("Error: You are not project lead.");
        }

        act.setActivtyDescription(newDescription);
        return StatusMessage.success("Activity description succesfully set to: " + newDescription);
    }

    public ProjectActivity getProjectActivity(String projectName, String activityName){
        Project project = state.getProjectByName(projectName);
        if(project != null) {
        ProjectActivity act = project.getActivity(activityName);
        return act;
        }
       
        else {
            return null;
        }
        
    }

} 