package dtu.example.Controller;

import java.io.ObjectInputFilter.Status;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import dtu.example.model.*;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Service.ActivityService;
import dtu.example.Service.FixedActivityService;
import dtu.example.Service.ProjectService;
import dtu.example.Service.TimeRegistrationService;
import dtu.example.Service.UserService;

public class AppController {
    private final AppState state;
    private final ActivityService activityService;
    private final FixedActivityService fixedActivityService;
    private final ProjectService projectService;
    private final UserService userService;
    private final TimeRegistrationService registrationService;

    public AppController() {
        this.state = new AppState();
        this.activityService = new ActivityService(state);
        this.fixedActivityService = new FixedActivityService(state);
        this.projectService = new ProjectService(state);
        this.userService = new UserService(state);
        this.registrationService = new TimeRegistrationService(state);
    }

    public AppController(AppState state) {
        this.state = state;
        this.activityService = new ActivityService(state);
        this.fixedActivityService = new FixedActivityService(state);
        this.projectService = new ProjectService(state);
        this.userService = new UserService(state);
        this.registrationService = new TimeRegistrationService(state);
    }

    public StatusMessage createUser(String userID) {
        StatusMessage result = userService.createUser(userID);
        return result;
    }

    public StatusMessage createProject(String projectName) {
        StatusMessage result = projectService.createProject(projectName);
        return result;
    }

    public Project getProject(String projectInput) {
        return projectService.getProject(projectInput);
    }
    

    public StatusMessage createTimeRegistration(String projectName, String activityName, String workHours){
        Project project = projectService.getProject(projectName);
        ProjectActivity activity = activityService.getProjectActivity(project, activityName);
        StatusMessage result = registrationService.createTimeRegistration(project, activity, getActiveUser(), workHours);
        return result;
    }

    

    public StatusMessage setActiveUser(String userName) {
        // TODO: TJEK OM ANDdre kalder på USER object direkte
        var user = userService.getUser(userName);
        return userService.setActiveUser(user);
    }

    public User getActiveUser() {
        return userService.getActiveUser();
    }

    public User getUser(String userName) {
        return userService.getUser(userName); 
    }

    //Ved ik hvordan jeg ellers skal gøre. Skal bruge det til at holde styr hvor mange 
    //activities en user er på: Nikolaj
    public StatusMessage addUserToActivity(String projectName, String activityName, String userID){
        Project project = projectService.getProject(projectName);
        ProjectActivity activity = activityService.getProjectActivity(project, activityName);
        User user = userService.getUser(userID);
        return activityService.addUserToActivity(activity, user);
    }

    // public ArrayList<User> getAllAvailableUsers()
    // {
    //     return userService.getAllAvailableUsers();
    // }

    public boolean isUserAvailable(String userID) {
        return userService.isAvailable(userID);
    }

    public StatusMessage createProjectActivity(String projectName, String activityName) {
        Project project = projectService.getProject(projectName);
        return activityService.createProjectActivity(project, activityName);
    }

    public StatusMessage setActivityDescription(String projectName, String activityName, String newDescription){
        Project project = projectService.getProject(projectName);
        ProjectActivity activity = activityService.getProjectActivity(project, activityName);

        return activityService.setActivityDescription(project, activity, newDescription);
    }

    public ProjectActivity getProjectActivity(String projectName, String activityName){
        Project project = projectService.getProject(projectName);
        return activityService.getProjectActivity(project, activityName);
          
    }

     //Adam wrote this. It creates a fixed activity, and returns a status message regarding missing arguments
    public StatusMessage createFixedActivity(String startDate, String endDate, String typeCause){
        StatusMessage result = fixedActivityService.createFixedActivity(startDate, endDate, typeCause);
        return result;
        }
    
    //Adam wrote this. Determines whether a fixed activity with given attributes exists
    //Validation for fixedactivity exists:
    public StatusMessage findFixedActivity(String type, String timeStart, String timeEnd){
        return fixedActivityService.findFixedActivity(type, timeStart, timeEnd);
    }



    public ArrayList<FixedActivity> getFixedActivities() {
        return fixedActivityService.getFixedActivities();
    }


    public StatusMessage setActivityStartDate(String projectName, String activityName, String date){
        Project project = projectService.getProject(projectName);
        ProjectActivity activity = activityService.getProjectActivity(project, activityName);
        StatusMessage result = activityService.setActivityStartDate(project, activity, date);
        return result;
            


    }

    public StatusMessage setActivityEndDate(String projectName, String activityName, String date){
        Project project = projectService.getProject(projectName);
        ProjectActivity activity = activityService.getProjectActivity(project, activityName);
        StatusMessage result = activityService.setActivityEndDate(project, activity, date);
        return result;
            


    }

    public String getActivityStartDate(String projectName, String activityName){
        Project project = projectService.getProject(projectName);
        return activityService.getActivityStartDate(project, activityName);
    }

    public String getActivityEndDate(String projectName, String activityName){
        Project project = projectService.getProject(projectName);
        return activityService.getActivityEndDate(project, activityName);
    }

    public HashMap<String, User> getAllUsers(){
        return userService.getAllUsers();
    }

    // Function for getting all availble users for given week
    public ArrayList<User> getAvailableUsers(String week) {
        return userService.getAllAvailableUsers(week);
    }
} 