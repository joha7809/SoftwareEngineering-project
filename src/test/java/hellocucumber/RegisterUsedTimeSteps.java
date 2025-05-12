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
public class RegisterUsedTimeSteps {
    

    
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;

    public RegisterUsedTimeSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    public StatusMessage result;

    @When("the user logs {string} hours on the activity")
        public void theUserLogsTheirTimeOnTheActivity(String hours) {
        User user = sharedContext.getCurrentUser();
        String projectName = sharedContext.getProjectName();
        String activityName = sharedContext.getActivityName();
        String userName = user != null ? user.getUserID() : "null";
        // handle null case
    

        result = controller.createTimeRegistration(projectName, activityName, hours, userName);
        sharedContext.setResult(result);
    }

    //TODO: Test om det er den rigtige mængde tid? (Lavede et TimeRegistration felt i sharedcontext men indså det var kinda svært at sætte den til noget uden af fokke med statusmessage )

    @Then("the time used is registered on the activity")
        public void theTimeUsedIsRegisteredOnTheActivity() {
            result = sharedContext.getResult();
            assertTrue(result.success);
        }

}



