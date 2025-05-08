// package hellocucumber;

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

public class ChangeStartDateOfActivitySteps {
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;
    
    public ChangeStartDateOfActivitySteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }
     public StatusMessage result;

    @When("user updates the start date")
        public void userUpdatesTheStartDate() {
        project = sharedContext.getCurrentProject();
        ProjectActivity activity = sharedContext.getProjectActivity();
    }

    @When("the user updates the start date to {string}")
        public void theUserUpdatesTheStartDateTo(String newStartDate) {
        result = controller.setActivityStartDate(sharedContext.getCurrentProject().getProjectName(), sharedContext.getProjectActivity().getName(), newStartDate); 
        sharedContext.setResult(result);
    }

    @Then("the start date is updated")
    public void theStartDateIsUpdated() {
        assertTrue(sharedContext.getResult().success);
    }
        
}
    