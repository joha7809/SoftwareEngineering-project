package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class RegisterUsedTimeSteps {
    

    
    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;

    public RegisterUsedTimeSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    public StatusMessage result;

    @When("the user logs their time on the activity")
        public void theUserLogsTheirTimeOnTheActivity(){
        User user = sharedContext.getCurrentUser();

    }


}



