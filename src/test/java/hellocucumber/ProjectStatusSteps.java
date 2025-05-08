package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectStatusSteps {
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;

    public ProjectStatusSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    @Given("the project has timeRegistrations")
    public void the_project_has_timeRegistrations() {
        // Write code here that turns the phrase above into concrete actions
        //assertFalse(project.hasTimeRegistrations());
        Project project = sharedContext.getCurrentProject();
        ProjectActivity activity = sharedContext.getProjectActivity();
        controller.createTimeRegistration(project.getProjectName(), activity.getName(), "20");
        assertTrue(project.hasTimeRegistrations());
    }

    @Given("the project has activities")
    public void the_project_has_activities() {
        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        project.addActivity(sharedContext.getProjectActivity());
        assertTrue(project.hasActivities());
    }

    @Given("the project has no timeRegistrations")
    public void the_project_has_no_timeRegistrations() {
        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        
        //ProjectActivity activity = sharedContext.getProjectActivity();
        //controller.createTimeRegistration(project.getProjectName(), activity.getName(), "20");
        assertFalse(project.hasTimeRegistrations());
    }

    @Given("the project has no activities")
    public void the_project_has_no_activities() {
        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        
        //ProjectActivity activity = sharedContext.getProjectActivity();
        //controller.createTimeRegistration(project.getProjectName(), activity.getName(), "20");
        assertFalse(project.hasActivities());
    }

    @When("{string} is typed")
    public void is_typed(String s) {
        // Write code here that turns the phrase above into concrete actions
        
    }

    @Then("returns a string {string}")
    public void returns_a_string(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the sum of estimatedRemainingHours is printed")
    public void the_sum_of_estimatedRemainingHours_is_printed() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("{string} is printed")
    public void is_printed(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the sum of timeRegistrations is printed")
    public void the_sum_of_timeRegistrations_is_printed() {
        // Write code here that turns the phrase above into concrete actions
    }

}
