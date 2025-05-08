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

public class ChangeEndDateOfActivitySteps {
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;
    
    public ChangeEndDateOfActivitySteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }
     public StatusMessage result;


    @When("the user updates the end date to {string}")
        public void theUserUpdatesTheEndDateTo(String newEndDate) {
        result = controller.setActivityEndDate(sharedContext.getCurrentProject().getProjectName(), sharedContext.getProjectActivity().getName(), newEndDate); 
        sharedContext.setResult(result);
    }

    @Then("the end date is updated")
    public void theEndDateIsUpdated() {
        assertTrue(sharedContext.getResult().success);
    }
        
}