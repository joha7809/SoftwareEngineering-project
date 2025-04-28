package hellocucumber;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppManager;
import dtu.example.model.User;
//import dtu.example.model.Project;
//import dtu.example.ui.ProjectOrganizer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

public class createProjectSteps {
    public AppManager appManager = new AppManager();
    public User user = new User("huba");

    
    @Given("a user is logged in")
    public void a_user_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        appManager.setActiveUser(user);
        assertTrue(appManager.getActiveUser()==user);

    }

    @Given("there is a project with the name {string}")
    public void there_is_a_project_with_the_name(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("there is no project with the name {string}")
    public void there_is_no_project_with_the_name(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("a project with the name {string} is created")
    public void a_project_with_the_name_is_created(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the project is added to the list of projects")
    public void the_project_is_added_to_the_list_of_projects() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("an error message is printed")
    public void an_error_message_is_printed() {
        // Write code here that turns the phrase above into concrete actions
    }
}

