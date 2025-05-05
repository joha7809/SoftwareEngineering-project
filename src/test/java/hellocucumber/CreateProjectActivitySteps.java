package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateProjectActivitySteps {
    private final AppManager appManager = new AppManager();

    @Given("a project called {string} exists")
    public void a_project_called_exists(String s) {
        // Write code here that turns the phrase above into concrete actions
        appManager.createProject(s);
        assertTrue(appManager.getProjectByName(s) != null);
    }

    @Given("user {string} is not project leader for {string}")
    public void user_is_not_project_leader_for(String s, String s2) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("the user {string} is project leader for {string}")
    public void the_user_is_project_leader_for(String s, String s2) {
        // Write code here that turns the phrase above into concrete actions
    }

   

    @Given("the project has a project leader for {string}")
    public void the_project_has_a_project_leader_for(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("project {string} has no project lead")
    public void project_has_no_project_lead(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("gives the activity a name")
    public void gives_the_activity_a_name() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the project leader creates a project activity")
    public void the_project_leader_creates_a_project_activity() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("does not give the activity a name")
    public void does_not_give_the_activity_a_name() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the user creates a project activity")
    public void the_user_creates_a_project_activity() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("a error message is printed")
    public void a_error_message_is_printed() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("a new activity is added to the project with the name")
    public void a_new_activity_is_added_to_the_project_with_the_name() {
        // Write code here that turns the phrase above into concrete actions
    }

  

  

    
}
