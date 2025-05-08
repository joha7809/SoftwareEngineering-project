package dtu.example.Service;

import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.AppState;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.User;

public class ActivityService {
    private final AppState state;

    public ActivityService(AppState state) {
        this.state = state;
    }

    public StatusMessage createProjectActivity(Project project, String activityName) {
        // Validation
        if (state.getActiveUser() == null) {
            return new StatusMessage(false, "Error: No user is logged in.");
        }
        
        if (activityName.equals("")) {
            return new StatusMessage(false, "Error: Activity name cannot be empty!");
        }
        
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

    public ProjectActivity getProjectActivity(Project project, String activityName){
        if(project != null) {
            ProjectActivity act = project.getActivity(activityName);
            return act;
        }
       
        else {
            return null;
        }
        
    }

    public StatusMessage setActivityStartDate(Project project, ProjectActivity activity, String date){
        if (project == null) {
            return StatusMessage.PROJECT_NOT_FOUND;
        }

        if (project.getProjectLead() != null && state.getActiveUser() != project.getProjectLead()){
            return new StatusMessage(false, "Error: You are not project lead.");
        }


        if (activity== null) {
            return StatusMessage.ACTIVITY_NOT_FOUND;
        }

        activity.setStartWeek(date);

        return StatusMessage.success("Activity start date successfully set to: " + date);
    }

    public StatusMessage setActivityEndDate(Project project, ProjectActivity activity, String date){
        if (project == null) {
            return StatusMessage.PROJECT_NOT_FOUND;
        }

        if (project.getProjectLead() != null && state.getActiveUser() != project.getProjectLead()){
            return new StatusMessage(false, "Error: You are not project lead.");
        }


        if (activity== null) {
            return StatusMessage.ACTIVITY_NOT_FOUND;
        }

        activity.setEndWeek(date);

        return StatusMessage.success("Activity start date successfully set to: " + date);
    }


    public String getActivityStartDate(Project project, String activityName){
        ProjectActivity activity = getProjectActivity(project, activityName);

        if (activity == null){
            return null;
        }
        
        return activity.getStartWeek();

    }

    public String getActivityEndDate(Project project, String activityName){
        ProjectActivity activity = getProjectActivity(project, activityName);

        if (activity == null){
            return null;
        }
        
        return activity.getEndWeek();

    }


    public StatusMessage setActivityDescription(Project project, ProjectActivity activity, String newDescription){
        if (project == null){
            return StatusMessage.PROJECT_NOT_FOUND;
        }
        

        if (activity == null) {
            return StatusMessage.error("Error: Activity not found.");
        }

        if (project.getProjectLead() != null && project.getProjectLead() != state.getActiveUser()){
            return StatusMessage.error("Error: You are not project lead.");
        }

        activity.setActivtyDescription(newDescription);
        return StatusMessage.success("Activity description succesfully set to: " + newDescription);
    }

    public StatusMessage addUserToActivity(ProjectActivity activity , User user){
        if (activity == null) {
            return StatusMessage.ACTIVITY_NOT_FOUND;
        }
        if (user == null) {
            return StatusMessage.USER_NOT_FOUND;
        }

        activity.addUser(user);
        user.joinActivity(activity);
        return StatusMessage.success("User " + user.getUserID() + " added to activity " + activity.getName());
    }


}
