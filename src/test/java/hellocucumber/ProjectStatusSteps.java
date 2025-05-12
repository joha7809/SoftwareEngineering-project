package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//Adam
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
        String userID = controller.getActiveUser().getUserID();
        controller.createTimeRegistration(project.getProjectName(), activity.getName(), "20", userID);
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

    @When("the user gets the report")
    public void the_user_gets_the_report() {
        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        assertTrue(project.getProjectStatus().length() > 0);
        var result = controller.getProjectStatus(project.getProjectName());
        System.out.println("SSSS S SDA SD " + result.success);
        System.out.println("SSSS S SDA SD " + result.message);
        sharedContext.setResult(result);
    }

    @Then("{string} is returned")
    public void is_returned(String s) {

        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        var result = sharedContext.getResult();
        
        //getData provides a String
        //assertTrue(result.success);

        //It has to be || as there are different scenarios with different requirements
        System.out.println("\n \n \n \n Result message " + result.message);
        System.out.println(s + "Input \n \n \n \n");
        assertTrue(!s.isBlank());
        assertTrue(result.message.contains(s), "Got: " + result.message + " Expected: " + s);
    }

    @Then("the sum of estimatedRemainingHours is returned")
    public void the_sum_of_estimatedRemainingHours_is_returned() {
        // Write code here that turns the phrase above into concrete actions
       /* Project project = sharedContext.getCurrentProject();
        var result = sharedContext.getResult();
        
        //getData provides a String
        assertTrue(result.success);
        assertTrue(result.message.contains(""));*/
    }

    @Then("the sum of timeRegistrations is returned")
    public void the_sum_of_timeRegistrations_is_returned() {
        // Write code here that turns the phrase above into concrete actions
    }

    

}
