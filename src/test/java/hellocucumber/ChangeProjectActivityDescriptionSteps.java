package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ChangeProjectActivityDescriptionSteps {
    
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;

    public ChangeProjectActivityDescriptionSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    public StatusMessage result;

    @Given("there is an activity with the name {string} for project {string}")
        public void thereIsAnActivityWithTheName(String activityName, String projectName) {
            Project project = controller.getProject(projectName);
            project.addActivity(new ProjectActivity(activityName));
            ProjectActivity act = controller.getProjectActivity(projectName, activityName);

            assertTrue(project != null);
            assertTrue(act != null); 

            // Share the created project with context
            sharedContext.setProjectActivity(act);
            sharedContext.setCurrentProject(project);
    }

    @Given("the activity description is {string}")
        public void theActivityDescriptionIs(String activityDescription) {
            ProjectActivity act = sharedContext.getProjectActivity();
            act.setActivtyDescription(activityDescription);
            assertTrue(act.getActivityDescription() != null);
    }

    @When("the user {string} changes the description of the activity to {string}")
        public void theUserChangesTheDescriptionOfTheActivity(String userID, String activityDescription){
            // User changes, means the user is the logged in user
            User user = controller.getUser(userID);
            controller.setActiveUser(user);
            Project project = sharedContext.getCurrentProject();
            ProjectActivity act = sharedContext.getProjectActivity();
            var result = controller.setActivityDescription(project.getProjectName(), act.getName(), activityDescription);
            sharedContext.setResult(result);
        }


    @Then("the activity description is updated to {string}")
        public void theActivityDescriptionIsUpdated(String activityDescription){
            assertTrue(sharedContext.getProjectActivity().getActivityDescription().equals(activityDescription));
        }
    


}

