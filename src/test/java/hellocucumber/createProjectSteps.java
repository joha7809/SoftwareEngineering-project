package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.User;
//import dtu.example.model.Project;
//import dtu.example.ui.ProjectOrganizer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

public class createProjectSteps {
	public User user = new User("huba");
	public String projectToBeAddedName;
	public StatusMessage result;

	private final SharedContext sharedContext;
    private final AppManager appManager;

    public createProjectSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.appManager = sharedContext.getAppManager();
    }

	@Given("a user is logged in")
	public void a_user_is_logged_in() {
		// Write code here that turns the phrase above into concrete actions
		appManager.setActiveUser(user);
		assertTrue(appManager.getActiveUser() == user);

	}

	@Given("there is a project with the name {string}")
	public void there_is_a_project_with_the_name(String projectName) {
		// Initialize and add project to project_list in appManager.
		System.out.println("Creating project with name: " + projectName);
		result = appManager.createProject(projectName);
		assertTrue(result.success); // Was the project created?
	}

	@Given("there is no project with the name {string}")
	public void there_is_no_project_with_the_name(String name) {
		// Assert that there doesnt exist a project with name={name} in project list of
		// appManager
		Project project = appManager.getProjectByName(name);
		assertTrue(project == null);
	}

	@When("a project with the name {string} is created")
	public void a_project_with_the_name_is_created(String projectName) {
		result = appManager.createProject(projectName); // sets outcome of function to class variable
		projectToBeAddedName = projectName;
	}

	@Then("the project is added to the list of projects")
	public void the_project_is_added_to_the_list_of_projects() {
		// Write code here that turns the phrase above into concrete actions
		assertTrue(result.success);
		assertTrue(appManager.getProjectByName(projectToBeAddedName) != null);
	}

	@Then("an error message is printed")
	public void an_error_message_is_printed() {
		// Write code here that turns the phrase above into concrete actions
		assertFalse(result.success);
		System.out.println(result.message);
	}
}
