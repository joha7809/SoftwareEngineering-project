package dtu.example.Controller;

import java.io.ObjectInputFilter.Status;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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

    public StatusMessage createTimeRegistration(String projectName, String activityName, String workHours){
        //Validation
        if (state.getActiveUser() == null) {
            return new StatusMessage(false, "Error: No user is logged in.");
        }
        
        Project project = getProject(projectName);

        if (project == null) {
            return StatusMessage.PROJECT_NOT_FOUND;
        }
        
        ProjectActivity activity = getProjectActivity(projectName, activityName);

        if (activity == null) {
            return StatusMessage.ACTIVITY_NOT_FOUND;
        }

        //Idk: 
        float hours = Float.valueOf(workHours);

        if (hours % 0.5 != 0){
            return new StatusMessage(false, "Error: Hours not in half-hour interval.");
        }

        //Hvordan skal date passses?

        TimeRegistration registration = new TimeRegistration(null, hours, getActiveUser());
        getProjectActivity(projectName, activityName).addRegistration(registration);;
        return new StatusMessage(true, workHours + "Work hours registered on activity.");
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

    //Ved ik hvordan jeg ellers skal gøre. Skal bruge det til at holde styr hvor mange 
    //activities en user er på: Nikolaj
    public void addUserToActivity(ProjectActivity activity , User user){
        if(user.getJoinedActivities().size()<20){
            user.joinActivity(activity);
        }
        else {
            //error message !!!
            //fjern user.joinActivity(activity);
            user.joinActivity(activity);
        }
        
    }

    public ArrayList<User> getAllAvailableUsers()
    {
        //ved ikke om et hashmap ville være bedre
        ArrayList<User> allAvailableUsers = new ArrayList<>();
        for (var entry : state.getUsers().entrySet()) {
            User user = entry.getValue();
            if (user.getJoinedActivities().size() < 20);{
                allAvailableUsers.add(user);
            }
        }
        return allAvailableUsers;
    }

    public boolean isAvailable(String userID) {
        User user = state.getUser(userID);
        if (user != null) {
            return user.getJoinedActivities().size() < 20;
        }
        return false;
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

     //Adam wrote this. It creates a fixed activity, and returns a status message regarding missing arguments
    public StatusMessage createFixedActivity(String startDate, String endDate, String typeCause){

        //TODO: Maybe arraylist of possible fixed activity types


        //String createFixedActivityCommand = "createFixedActivity";
        String createFixedActivityType = typeCause;
        //boolean commandPass = command.equalsIgnoreCase(createFixedActivityCommand);
        boolean startDatePass = isDateFormat(startDate);
        boolean endDatePass = isDateFormat(endDate);
        //boolean typeCausePass = typeCause.equalsIgnoreCase(createFixedActivityType);

        if(!startDatePass && !endDatePass){
            return StatusMessage.error("Error: Neither dates are valid dates!");
        }

        if(!startDatePass){
            return StatusMessage.error("Error: Start date is not a valid date!");
        }
        
        if(!endDatePass){
            return StatusMessage.error("Error: End date is not a valid date!");
        }


        getFixedActivities().add(new FixedActivity(startDate, endDate, typeCause));
        return new StatusMessage(true, "Fixed Activity " + typeCause + " was succesfully created");
        }
    

    //Adam wrote this. Helps determine whether a date is of the date format
    public boolean isDateFormat(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            // TODO: handle exception
            return false;
        }
    }

    //Adam wrote this. Determines whether a fixed activity with given attributes exists
    //Validation for fixedactivity exists:
    public StatusMessage findFixedActivity(String type, String timeStart, String timeEnd){
        for (int i = 0; i < getFixedActivities().size(); i++){
            //if(getFixedActivities().get(i).getStartDate().equalsIgnoreCase(name)){//gets problematic once there are multiple fixed activities named the same e. g. sick
            if(getFixedActivities().get(i).getName().equalsIgnoreCase(type) && getFixedActivities().get(i).getStartDate().equalsIgnoreCase(timeStart) && getFixedActivities().get(i).getEndDate().equalsIgnoreCase(timeEnd)){//gets problematic once there are multiple fixed activities named the same e. g. sick
                    
                return new StatusMessage(true, "Fixed Activity " + getFixedActivities().get(i).getName() + " Exists.");
            }
        }
        return new StatusMessage(false, "Could not find fixed activity");
    }



    public ArrayList<FixedActivity> getFixedActivities() {
        return state.getFixedActivities();
    }


    public StatusMessage setActivityStartDate(String projectName, String activityName, String date){
        if (getProject(projectName).getProjectLead() != null && getActiveUser() != getProject(projectName).getProjectLead()){
            return new StatusMessage(false, "Error: Logged in user not project leader.");
        }

        var project = getProject(projectName);

        if (project == null) {
            return StatusMessage.PROJECT_NOT_FOUND;
        }

        var projectActivity = getProjectActivity(projectName, activityName);

        if (projectActivity == null) {
            return StatusMessage.ACTIVITY_NOT_FOUND;
        }

        projectActivity.setStartDate(date);
        return StatusMessage.success("Activity start date successfully set to: " + date);


    }

    public String getActivityStartDate(String projectName, String activityName){
        ProjectActivity activity = getProjectActivity(projectName, activityName);
        
        return activity.getStartDate();

    }

} 