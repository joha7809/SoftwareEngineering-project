package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppManager;
import dtu.example.model.Project;
import dtu.example.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a;

public class CreateProjectActivitySteps {

    private final SharedContext sharedContext;
    private final AppManager appManager;
    
    
    public CreateProjectActivitySteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.appManager = sharedContext.getAppManager();
    }

    @Given("user {string} is not project leader for {string}")
    public void user_is_not_project_leader_for(String userID, String projectName) {
        // Write code here that turns the phrase above into concrete actions
        Project project = appManager.getProject(projectName);
        User user = appManager.getUser(userID);
        assertTrue(project != null);
        assertTrue(user != null);
        
        assertFalse(project.getProjectLead().equals(user));
    }
   

    @Given("the project has a project leader for {string}")
    public void the_project_has_a_project_leader_for(String projectName) {
        // Write code here that turns the phrase above into concrete actions
        appManager.createUser("ole");
        appManager.getProject(projectName).setProjectLead(appManager.getUser("ole"));
        assertTrue(!appManager.getProject(projectName).getProjectLead().equals(null));
    }

    @Given("project {string} has no project lead")
    public void project_has_no_project_lead(String projectName) {
        // Write code here that turns the phrase above into concrete actions
        Project p = appManager.getProject(projectName);
        assertTrue(p.getProjectLead() == null);
    }

    @When("the project leader creates a project activity with the name {string}")
    public void the_project_leader_creates_a_project_activity_with_the_name(String activityName) {
        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        
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
