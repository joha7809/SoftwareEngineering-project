package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.User;
//import dtu.example.model.Project;
//import dtu.example.ui.ProjectOrganizer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

//Kerem
public class createProjectSteps {
	public User user;
	public String projectToBeAddedName;
	public StatusMessage result;

	private final SharedContext sharedContext;
    private final AppController controller;

    public createProjectSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

	@Given("a user is logged in")
	public void a_user_is_logged_in() {
		// Write code here that turns the phrase above into concrete actions

		var msg = controller.createUser("huba");
		assertTrue(msg.success);
		user = controller.getUser("huba");

		controller.setActiveUser(user.getUserID());
		assertTrue(controller.getActiveUser() == user);

	}

	@Given("there is a project with the name {string}")
	public void there_is_a_project_with_the_name(String projectName) {
		// Initialize and add project to project_list in appManager.
		System.out.println("Creating project with name: " + projectName);
		result = controller.createProject(projectName);
		sharedContext.setResult(result);
		Project p = controller.getProject(projectName);
		assertTrue(p != null);
		sharedContext.setCurrentProject(p);
		assertTrue(result.success); // Was the project created?
	}

	@Given("there is no project with the name {string}")
	public void there_is_no_project_with_the_name(String name) {
		// Assert that there doesnt exist a project with name={name} in project list of
		// appManager
		Project project = controller.getProject(name);
		assertTrue(project == null);
	}

	@When("a project with the name {string} is created")
	public void a_project_with_the_name_is_created(String projectName) {
		result = controller.createProject(projectName); // sets outcome of function to class variable
		sharedContext.setResult(result);
		projectToBeAddedName = projectName;
	}

	@Then("the project is added to the list of projects")
	public void the_project_is_added_to_the_list_of_projects() {
		// Write code here that turns the phrase above into concrete actions
		assertTrue(result.success);
		assertTrue(controller.getProject(projectToBeAddedName) != null);
	}

	@Then("an error message is printed")
	public void an_error_message_is_printed() {
		// Write code here that turns the phrase above into concrete actions
		result = sharedContext.getResult();
		assertFalse(result.success, result.message);
	}

	@Given("no projects exist")
    public void no_projects_exist() {
        // Write code here that turns the phrase above into concrete actions
		assertTrue(controller.getProject("25001") == null);
    }

    @Then("the project with the name {string} has id {string}")
    public void the_project_with_the_name_has_id(String name, String id) {
		System.out.println("ID:    " + controller.getProject(name).getProjectID());
        assertTrue(controller.getProject(name).getProjectID().equals(id));
    }



	@When("the project with the name {string} is edited to {string} with description {string}")
    public void the_project_with_the_name_is_edited_to_with_description(String oldName, String newName, String description) {
        Project project = controller.getProject(oldName);
		assertTrue(project != null);
		project.setName(newName);
		project.setDescription(description);
		sharedContext.setCurrentProject(project);
    }

	@Then("the project with the name {string} exists")
    public void the_project_with_the_name_exists(String name) {
        Project project = controller.getProject(name);
        assertTrue(project != null);
    }

    @Then("the project with the name {string} does not exist")
    public void the_project_with_the_name_does_not_exist(String name) {
        Project project = controller.getProject(name);
        assertTrue(project == null);
    }

    @Then("the project with the name {string} has description {string}")
    public void the_project_with_the_name_has_description(String name, String description) {
        Project project = controller.getProject(name);
        assertTrue(project.getDescription().equals(description));
    }

    @When("the project with the name {string} is edited to {string}")
    public void the_project_with_the_name_is_edited_to(String s, String s2) {
        // Write code here that turns the phrase above into concrete actions
		Project project = controller.getProject(s);
		assertTrue(project != null);
		project.setName(s2);
		sharedContext.setCurrentProject(project);
	}
    



	// unit testing idk


}
