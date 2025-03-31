package hellocucumber;


import dtu.example.ui.Project;
import dtu.example.ui.ProjectOrganizer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
    

    @When("a Project is created with a name {string}")
    public void aProjectIsCreatedWithAName(String string) 
    {
       Project project = new Project("newProject");
    }

    @Then("the Projects name {string}")
    public void theProjectsName(String string) 
    {
        
    }
}
