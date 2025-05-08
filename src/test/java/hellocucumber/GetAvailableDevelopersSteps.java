package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a;
import io.cucumber.java.lu.ugeholl.ugeholls;


public class GetAvailableDevelopersSteps {
    
    private final SharedContext sharedContext;
    private final AppController controller;
    private StatusMessage result;

    private String projectName = "Ikea";
    private String activityName = "Replanish";
    private String userName = "Bob";
    

    public GetAvailableDevelopersSteps(SharedContext sharedContext)
    {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    //lavet forkert NIKOLAJ

    @Given("Users assigned to activities under a total of {int} activities a week")
    public void Users_assigned_to_activities_under_a_total_of_activities_a_week(int activities) {
        controller.createProject(projectName);
        controller.createProjectActivity(projectName, activityName);
        controller.createUser(userName);


        assertTrue(controller.getUser(userName).getJoinedActivities().size() < activities);
    }

    @Given("Users assigned to activities over a total of {int} activities a week")
    public void Users_assigned_to_activities_over_a_total_of_activities_a_week(int activities) {
        
        
        controller.createProject(projectName);
        controller.createUser(userName);
       
        for(int j = 0; j < 25; j++){
            controller.createProjectActivity(projectName, activityName + "" + j);
            controller.addUserToActivity(controller.getProjectActivity(projectName, activityName + "" + j), controller.getUser("1"));
        }

        assertFalse(controller.getUser(userName).getJoinedActivities().size() < activities);
    }

    @When("Duration needed for the activty is entered")
    public void Duration_needed_for_the_activty_is_entered() {
        // Write code here that turns the phrase above into concrete actions
        
    }

    @When("Duration is updated")
    public void Duration_is_updated() {
        // Write code here that turns the phrase above into concrete actions
    }

    
    @Then("Return all available users")
    public void Return_all_available_users() {
        // Write code here that turns the phrase above into concrete actions
    }

    
    @Then("Return a string stating that no user is currently available")
    public void Return_a_string_stating_that_no_user_is_currently_available() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("Return all available user, that wants to work over the given {int} activities a week")
    public void Return_all_available_user_that_want_s_to_work_over_the_given_activities_a_week(int i) {
        // Write code here that turns the phrase above into concrete actions
    }





  

 
}
