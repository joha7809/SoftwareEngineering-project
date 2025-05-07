package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AssignProjectLeadSteps {
    
    private final AppManager appManager;
    private final SharedContext sharedContext;
    private Project project;

    public AssignProjectLeadSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.appManager = sharedContext.getAppManager();
    }

    public StatusMessage result;

    @Given("there is no user with the name {string}")
    public void thereIsNoUserWithTheName(String userID) {
        // Check for if there is a user with the userID
        assertTrue(appManager.getUser(userID) == null);
    }

    @Given("there is a user with the name {string}")
    public void thereIsAUserWithTheName(String userID) {
        //Create a user and check for if there isn't a user with the userID
        System.out.println("CREATING USER WITH NAME " + userID);
        var result = appManager.createUser(userID);
        System.out.println(result);
        assertTrue(result.success);
        assertTrue(appManager.getUser(userID) != null);
    }
    
    @Given("Project with the name {string} has no project lead")
    public void projectWithTheNameHasNoProjectLead(String projectName) {
        // Check that the project does not have a project lead
        assertTrue(appManager.getProject(projectName).getProjectLead() == null);
    }


    @Given("user {string} is project lead on project {string}")
    public void userIsProjectLeadOnProject(String userID, String projectName) {
        // Set user to project lead and check that the user is the project lead
        User user = appManager.getUser(userID);
        System.out.println("TRYING TO GET USER" + user);
        assertTrue(user != null);
        Project project = appManager.getProject(projectName);
        assertTrue(project != null);

        project.setProjectLead(user);
        assertTrue(project.getProjectLead() == user);
        assertTrue(appManager.getProject(projectName).getProjectLead().getUserID().equals(userID));
    }

    @When("The user {string} is assigned as project lead on project {string}")
    public void theUserIsAssignedAsProjectLeadOnProject(String userID, String projectName) {
        project = appManager.getProject(projectName);

        if (project == null) {
            result = StatusMessage.PROJECT_NOT_FOUND;
            return;
        }

        User user = appManager.getUser(userID);
        if (user == null) {
            result = StatusMessage.USER_NOT_FOUND;
            return;
        }
        project.setProjectLead(user);
        result = new StatusMessage(true, "Project lead assigned successfully");
        //result = appManager.getProjectByName(projectName).setProjectLead(appManager.getUser(userID));
    }

    
    @Then("the {string} error message is given")
    public void theErrorMessageIsGiven(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        result = (result != null) ? result : sharedContext.getResult();
        System.out.println("RESULT: " + result);
        if (!result.success) {
            assertTrue(result.message.equals(string));
        } else {
            assertTrue(result.success);
        }
    }

    
    @Then("the project lead {string} is replaced with new project lead {string}")
    public void theProjectLeadIsReplacedWithNewProjectLead(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(project.getProjectLead().getUserID().equals(string2));
        assertFalse(project.getProjectLead().getUserID().equals(string));
    }


    @Then("the project {string} has project lead {string}")
    public void theProjectHasProjectLead(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(appManager.getProject(string).getProjectLead().getUserID().equals(string2));
    }  
}