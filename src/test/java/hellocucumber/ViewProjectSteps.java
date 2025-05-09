package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;

public class ViewProjectSteps {

    private final SharedContext sharedContext;
    private final AppController controller;
    private String result;

    public ViewProjectSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    @Given("there are multiple projects in the system")
    public void thereAreMultipleProjectsInTheSystem() {
        controller.createProject("Project Alpha");
        controller.createProject("Project Beta");
    }

    @When("the user views all projects")
    public void theUserViewsAllProjects() {
        result = controller.getAllProjectsSummary();
    }

    @Then("a list of all projects with their names and IDs is displayed")
    public void aListOfAllProjectsWithTheirNamesAndIDsIsDisplayed() {
        assertTrue(result.contains("Project Alpha"));
        assertTrue(result.contains("Project Beta"));
    }




    @When("the user views the project with the name {string}")
    public void theUserViewsTheProjectWithTheName(String projectName) {
        result = controller.getProjectDetails(projectName);

    }

    @Then("the project details are displayed")
    public void theProjectDetailsAreDisplayed() {
        assertTrue(result.contains("Project Details"));
    }

    @Then("the project lead {string} is displayed")
    public void theProjectLeadIsDisplayed(String projectLead) {
        System.out.println("RESULT: SSSS " + result);
        assertTrue(result.contains(projectLead));
    }

    @Then("the activities related to the project are displayed")
    public void theActivitiesRelatedToTheProjectAreDisplayed() {
        assertTrue(result.contains("Design Phase"));
        assertTrue(result.contains("Development Phase"));
    }

    @Then("the time registrations related to the project are displayed")
    public void theTimeRegistrationsRelatedToTheProjectAreDisplayed() {
        // Implement logic to verify time registrations are displayed
    }


    @Then("the \"Error: Project not found.\" error message is displayed")
    public void theErrorMessageIsDisplayed() {
        assertEquals("Error: Project not found.", result);
    }
}
