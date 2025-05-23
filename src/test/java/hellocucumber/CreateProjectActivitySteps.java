package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a;
//Nikolaj
public class CreateProjectActivitySteps {

    private final SharedContext sharedContext;
    private final AppController controller;
    private StatusMessage result;
    private String activityName;
    
    //Nikolaj
    public CreateProjectActivitySteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }
    //Nikolaj
    @Given("user {string} is not project leader for {string}")
    public void user_is_not_project_leader_for(String userID, String projectName) {
        // Write code here that turns the phrase above into concrete actions
        Project project = controller.getProject(projectName);
        User user = controller.getUser(userID);
        assertTrue(project != null);
        assertTrue(user != null);
        
        assertFalse(project.getProjectLead() == (user));
    }
   
    //Nikolaj
    @Given("the project has a project leader for {string}")
    public void the_project_has_a_project_leader_for(String projectName) {
        // Write code here that turns the phrase above into concrete actions
        controller.createUser("olee");
        controller.getProject(projectName).setProjectLead(controller.getUser("olee"));
        assertTrue(!controller.getProject(projectName).getProjectLead().equals(null));
    }
    //Nikoalj
    @Given("project {string} has no project lead")
    public void project_has_no_project_lead(String projectName) {
        // Write code here that turns the phrase above into concrete actions
        Project p = controller.getProject(projectName);
        assertTrue(p.getProjectLead() == null);
    }
    //Nikolaj
    @Given("user {string} is logged in")
    public void user_is_logged_in(String userID) {
        User user = controller.getUser(userID);
        assertTrue(user != null);
        var result = controller.setActiveUser(userID);
        assertTrue(result.success);
        assertTrue(controller.getActiveUser().equals(user));
    }
    //Nikolaj
    @When("the user creates a project activity with the name {string}")
    public void the_user_creates_a_project_activity_with_the_name(String activityName) {
        // Write code here that turns the phrase above into concrete actions
        this.activityName = activityName;
        String projectName = sharedContext.getCurrentProject().getProjectName();
        result = controller.createProjectActivity(projectName, activityName);
        sharedContext.setResult(result);
    }
    //Nikoalj
    @Then("a new activity is added to the project with the name")
    public void a_new_activity_is_added_to_the_project_with_the_name() {
        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        assertTrue(project.getActivity(this.activityName) != null);
    }
    //Nikolaj
    @Given("no user is logged in")
    public void no_user_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(controller.getActiveUser() == null, "User is logged in");
    }

    

  

  

    
}
