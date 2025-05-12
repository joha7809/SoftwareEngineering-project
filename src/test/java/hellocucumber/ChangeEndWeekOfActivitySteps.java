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

public class ChangeEndWeekOfActivitySteps {
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;
    
    public ChangeEndWeekOfActivitySteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }
     public StatusMessage result;


    @When("the user updates the end week to {string}")
    public void theUserUpdatesTheEndWeekTo(String newEndWeek) {
        var project = sharedContext.getCurrentProject();
        var activity = sharedContext.getProjectActivity();

        // Handle null cases explicitly
        String projectName = (project != null && project.getProjectName() != null) ? project.getProjectName() : null;
        String activityName = (activity != null && activity.getName() != null) ? activity.getName() : null;

        result = controller.setActivityEndWeek(projectName, activityName, newEndWeek); 
        sharedContext.setResult(result);
    }

    @Then("the end week is updated")
    public void theEndDateIsUpdated() {
        assertTrue(sharedContext.getResult().success);
    }

    @Then("the end week is {string}")
    public void the_end_week_is(String s) {
        // Write code here that turns the phrase above into concrete actions
        ProjectActivity act = sharedContext.getProjectActivity();
        Project project = sharedContext.getCurrentProject();

        String endDate = controller.getActivityEndDate(project.getProjectName(), act.getName());
        assertTrue(endDate.equalsIgnoreCase(s));
    }

    @Given("there is no activity with the name {string} for project {string}")
    public void there_is_no_activity_with_the_name_for_project(String activityName, String projectName) {
        Project project = controller.getProject(projectName);
        ProjectActivity act = controller.getProjectActivity(projectName, activityName);
        sharedContext.setProjectActivity(act);
        sharedContext.setCurrentProject(project);
        sharedContext.setActivityName(activityName);
        sharedContext.setProjectName(projectName);
        assertTrue(project != null, "Project " + projectName + " does not exist");
        assertTrue(act == null, "Activity with the name " + activityName + " already exists in project " + projectName);
    }

        
}