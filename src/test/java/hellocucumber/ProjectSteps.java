package hellocucumber;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import dtu.example.model.Project;
//import dtu.example.ui.ProjectOrganizer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
<<<<<<< HEAD
=======
    public AppManager manager = new AppManager();
    public String projectIDCreated;
    public Project projectCreated;
    public String errorMessage;
>>>>>>> c553859 (WIP project steps)
    
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
<<<<<<< HEAD
=======

        assertTrue(manager.getProject(ProjectName) == null); //Ensure that there is no project called ProjectName
    }

    @Given("there is a Project with the name {string}")
    public void there_is_a_Project_with_the_name(String projectID) {
        // Write code here that turns the phrase above into concrete actions
        Project project = manager.createProject(projectID);
        // check that the project is in the manager
        assertTrue(manager.getProject(projectID) != null);
    }



    @When("a Project with name {string} is created")
    public void a_Project_with_name_is_created(String s) {
        try {
            // create a project with the given name
            projectCreated = manager.createProject(s);
            projectIDCreated = s;

        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        
        
>>>>>>> c553859 (WIP project steps)
    }

    @Then("the Project is added to the list of projects")
    public void the_Project_is_added_to_the_list_of_projects() {
<<<<<<< HEAD
        // Write code here that turns the phrase above into concrete actions
=======
        // // add the project to the manager
        // manager.addProject(projectCreated);
        // check that the project is in the manager
        assertTrue(manager.getProject(projectIDCreated).getProjectID().equals(projectIDCreated));
>>>>>>> c553859 (WIP project steps)
    }

    @When("a Project with name {string} is created")
    public void a_Project_with_name_is_created(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

<<<<<<< HEAD
    @Given("there is no Project with the name {string}")
    public void there_is_no_Project_with_the_name(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("a user is logged in")
    public void a_user_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions
=======
    @Then("an error message is printed")
    public void an_error_message_is_printed() {
        System.out.println("Error message: " + errorMessage);
        // check that the error message is not null
        assertTrue(errorMessage != null);
        // check that the error message is not empty
        assertTrue(!errorMessage.isEmpty());
>>>>>>> c553859 (WIP project steps)
    }
}

