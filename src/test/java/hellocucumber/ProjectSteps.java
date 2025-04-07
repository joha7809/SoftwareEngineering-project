package hellocucumber;


import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppManager;
import dtu.example.Model.Project;
import dtu.example.Model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
    public AppManager manager = new AppManager();
    public String projectIDCreated;
    public Project projectCreated;
    
    @Given("a user is logged in")
    public void a_user_is_logged_in() {
        // create a user and set it as the active user
        User user = new User("testUser");
        manager.setActiveUser(user);
        // check that the active user is not null        
        assertTrue(manager.activeUser != null);
    }

    @Given("there is no Project with the name {string}")
    public void there_is_no_Project_with_the_name(String ProjectName) {
        // Write code here that turns the phrase above into concrete actions

        assertTrue(manager.getProject(ProjectName) == null); //Ensure that there is no project called ProjectName
    }

    @Given("there is a Project with the name {string}")
    public void there_is_a_Project_with_the_name(String projectID) {
        // Write code here that turns the phrase above into concrete actions
        Project project = new Project(projectID);
        // add the project to the manager
        manager.addProject(project);
        // check that the project is in the manager
        assertTrue(manager.getProject(projectID) != null);
    }



    @When("a Project with name {string} is created")
    public void a_Project_with_name_is_created(String s) {
        projectIDCreated = s;
        projectCreated = new Project(s);
    }

    @Then("the Project is added to the list of projects")
    public void the_Project_is_added_to_the_list_of_projects() {
        // add the project to the manager
        manager.addProject(projectCreated);
        // check that the project is in the manager
        assertTrue(manager.getProject(projectIDCreated).getProjectID().equals(projectIDCreated));
    }


    @Then("an error message is printed")
    public void an_error_message_is_printed() {
        // When adding project to manager list, an error should occur.
        manager.addProject(projectCreated); //Should fail


    }
}
