package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.User;
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

    private Project project;
    private ProjectActivity activity;

    @Given("there are multiple users which is available")
    public void there_are_multiple_users_which_is_available() {
        // Write code here that turns the phrase above into concrete actions

        //laver et project, activity og to users
        controller.createProject(projectName);
        controller.createProjectActivity(projectName, activityName);

        activity = controller.getProjectActivity(projectName, activityName);

        controller.createUser(userName);
        controller.createUser(userName + "1");
        controller.addUserToActivity(activity, controller.getUser(userName));
        controller.addUserToActivity(activity, controller.getUser(userName+"1"));

        //laver en bool og en counter
        Boolean multipleAvailableUsers = false;
        int counter = 0;
        //chekker at der er to navne der passer med vores users, som er en del af listen med
        //available users
        for (User user : controller.getAllAvailableUsers()) {
            if(controller.getUser(userName).equals(user.getUserID()) || controller.getUser(userName+"1").equals(user.getUserID())){
                counter++;
            }
        }
        //ser og der var mere end 1 hit
        if(counter>1){
            multipleAvailableUsers = true;
        }
        //GIVER FEJL -_- idk?
        assertTrue(multipleAvailableUsers);


    }

    @When("the user request a list of available users")
    public void the_user_request_a_list_of_available_users() {
        // Write code here that turns the phrase above into concrete actions

        //IDK
        assertTrue(controller.getAllAvailableUsers() == ArrayList<User>)
    }

    @Then("the user gets all a list of all available users")
    public void the_user_gets_all_a_list_of_all_available_users() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("there are no users available")
    public void there_are_no_users_available() {
        // Write code here that turns the phrase above into concrete actions
    }
}

    