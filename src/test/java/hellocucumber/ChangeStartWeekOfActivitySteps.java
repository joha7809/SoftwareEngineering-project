package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//August
public class ChangeStartWeekOfActivitySteps {
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;
    
    public ChangeStartWeekOfActivitySteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }
     public StatusMessage result;

    @When("the user updates the start week to {string}")
        public void theUserUpdatesTheStartWeekTo(String newStartDate) {
        var project = sharedContext.getCurrentProject();
        var activity = sharedContext.getProjectActivity();

        // Handle null cases explicitly
        String projectName = (project != null && project.getProjectName() != null) ? project.getProjectName() : null;
        String activityName = (activity != null && activity.getName() != null) ? activity.getName() : null;

        result = controller.setActivityStartWeek(projectName, activityName, newStartDate); 
        sharedContext.setResult(result);
    }

    @Then("the start week is updated")
    public void theStartWeekIsUpdated() {
        assertTrue(sharedContext.getResult().success, result.message);
    }

    @Then("the start week is {string}")
    public void the_start_week_is(String s) {
        // Write code here that turns the phrase above into concrete actions
        ProjectActivity act = sharedContext.getProjectActivity();
        Project project = sharedContext.getCurrentProject();

        String startDate = controller.getActivityStartDate(project.getProjectName(), act.getName());
        assertTrue(startDate.equalsIgnoreCase(s));
    }

    @Given("the user is logged out")
    public void the_user_is_logged_out() {
        // Write code here that turns the phrase above into concrete actions
        User user = controller.getActiveUser();
        var msg = controller.logout(user.getUserID());
        assertTrue(msg.success, "User is not logged out");
        assertTrue(controller.getActiveUser() == null, "User is not logged out"); ;
    }


    // @Given("there is no activity with the name {string} for project {string}")
    // public void there_is_no_activity_with_the_name_for_project(String activityName, String projectName) {
    //     Project project = controller.getProject(projectName);
    //     ProjectActivity act = controller.getProjectActivity(projectName, activityName);
    //     sharedContext.setProjectActivity(act);
    //     sharedContext.setCurrentProject(project);
    //     assertTrue(project != null, "Project " + projectName + " does not exist");
    //     assertTrue(act == null, "Activity with the name " + activityName + " already exists in project " + projectName);
    //     // Write code here that turns the phrase above into concrete actions
    // }
        
}
    