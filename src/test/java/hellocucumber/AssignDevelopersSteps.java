package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.User;
import dtu.example.ui.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a;

public class AssignDevelopersSteps {

    private final SharedContext sharedContext;
    private final AppController controller;
    private StatusMessage result;

    public AssignDevelopersSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    private String activityName = "Metting";
    private Project project;
    private User user;
    private ProjectActivity activity;

    @Given("user {string} is unavailable")
    public void isUnavailable(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
        project = sharedContext.getCurrentProject();

        for(int j = 0; j < 25; j++){
            // Manually create activities for the project
            // This is to skip login validation etc.
            ProjectActivity activity = new ProjectActivity(activityName + "" + j);
            project.addActivity(activity);


            controller.addUserToActivity(controller.getProjectActivity(project.getProjectName(),
            project.getActivity(activityName + "" + j).getName() + "" + j), controller.getUser(userInitials));
        }
        Boolean isAvailable = false;
        for (User user : controller.getAllAvailableUsers()) {
            if(user.getUserID().equals(userInitials)){
                isAvailable = true;
            }
        }
        assertFalse(isAvailable);

    }

    @Given("user {string} is available")
    public void is_available(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
        Boolean userAvailable = false;
        for (User user : controller.getAllAvailableUsers()) {
            if(user.getUserID().equals(userInitials)){
                userAvailable = true;
            }
            
        }
        assertTrue(controller.isAvailable(userInitials));
        assertTrue(userAvailable);
        //assertTrue(controller.getUser(userInitials).isAvailable());
    }
    
    @When("project leader assigns {string} to an activity")
    public void projectLeaderAssignsToAnActivity(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
        Boolean isAssign = false;
        User us = controller.getUser(userInitials);
        activity = sharedContext.getProjectActivity();
        controller.addUserToActivity(activity, us);
        for(ProjectActivity activity : controller.getUser(userInitials).getJoinedActivities()) {
            if(activity.getName().equals(activityName)){
                isAssign = true;
            }
        }
        assertTrue(isAssign);
    }

    @Then("{string} is assigned to the activity")
    public void is_assigned_to_the_activity(String userInitals) {
        // Write code here that turns the phrase above into concrete actions
        for (ProjectActivity activity : controller.getUser(user.getUserID()).getJoinedActivities()) {
            assertTrue(activity.getName().equals(activityName));
        }
    }



    @Then("{string} is not assigned to the activity")
    public void is_not_assigned_to_the_activity(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
        for (ProjectActivity activity : controller.getUser(user.getUserID()).getJoinedActivities()) {
            assertFalse(activity.getName().equals(activityName));
        }
    }
}

