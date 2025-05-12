package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a;

//Kerem
public class ChangeExpectedTimeForActivitySteps {

    private final AppController controller;
    private final SharedContext sharedContext;
    private Project project;

    public ChangeExpectedTimeForActivitySteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    private StatusMessage result;


    
    @Given("the activity with the name {string} has no estimated time")
    public void the_Activity_With_The_Name_Has_No_Estimated_Time(String activityName) {
        //checks if the activity has no estimated time
        assertTrue(sharedContext.getCurrentProject().getActivity(activityName).getAllottedTime() == 0.0f);
    }

    @Given("the activity with the name {string} has an estimated time")
    public void the_Activity_With_The_Name_Has_An_Estimated_Time(String activityName) {
        //sets an estimated time for the activity and checks it
        sharedContext.getCurrentProject().getActivity(activityName).setAllottedTime(1.0f);
        assertTrue(sharedContext.getCurrentProject().getActivity(activityName).getAllottedTime() != 0.0f);
    }

    

    @When("The project lead inputs the expected time, of {string} hours, for the activity {string}")
    public void The_project_lead_inputs_the_expected_time_of_5_hours_for_the_activity(String time, String activityName) {
        //checks if the time is a number and greater than 0
        //if so, sets the estimated time for the activity
        if(Float.valueOf(time) > 0.0){
            sharedContext.getCurrentProject().getActivity(activityName).
            setAllottedTime(Float.valueOf(time));  
            //set result to true
            result = new StatusMessage(true, "Estimated time updated");
        }        
        else{
            //sets result to false
            result = new StatusMessage(false, "Estimated time must be greater than 0");
        }

        sharedContext.setResult(result);
      
    }

    


    @Then("the estimated time for the activity {string} is updated")
    public void the_estimated_time_for_the_activity_is_updated(String activityName) {
        // Write code here that turns the phrase above into concrete actions
        sharedContext.getResult();
        assertTrue(sharedContext.getCurrentProject().getActivity(activityName).getAllottedTime() == 5f);
    }

   

    
   
}
