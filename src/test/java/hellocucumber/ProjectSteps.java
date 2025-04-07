package hellocucumber;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import dtu.example.model.Project;
//import dtu.example.ui.ProjectOrganizer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
    
    //ProjectOrganizer projectOrganizer = new ProjectOrganizer();

    @When("a Project is created with a name {string}")
    public void aProjectIsCreatedWithAName(String string) 
    {
       // projectOrganizer.createProject(string);
    }

    @Then("the Projects name {string}")
    public void theProjectsName(String string) 
    {
        //assertEquals(string, projectOrganizer.getProject().getProjectName());
    }

    @Given("a user {string} is logged in")
    public void a_user_is_logged_in(String s) {
        // Write code here that turns the phrase above into concrete actions
    }
}
