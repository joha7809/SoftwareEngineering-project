package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.User;
import dtu.example.ui.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a;

public class AssignDevelopersSteps {

    private final SharedContext sharedContext;
    private final AppController controller;
    private StatusMessage result;

    public AssignDevelopersSteps(SharedContext sharedContext)
    {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();

    }

    private String activityName = "Metting";
    private Project project;
    private User user;
    private ProjectActivity activity;

    @Given("user {string} is project lead on project {string}")
    public void isTheProjectLeader(String userInitials, String projectName) {
        controller.createUser(userInitials);
        controller.createProject(projectName);
        controller.createProjectActivity(projectName, activityName);
        controller.setActiveUser(controller.getUser(userInitials));

        user = controller.getUser(userInitials);
        project = controller.getProject(projectName);
        activity = controller.getProjectActivity(projectName, activityName);

        assertTrue(controller.getActiveUser().equals(userInitials));
    }

    @Given("user {string} is unavailable")
    public void isUnavailable(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
       
        for(int j = 0; j < 25; j++){
            controller.createProjectActivity(project.getProjectName(), 
            project.getActivity(activityName) + "" + j);

            controller.addUserToActivity(controller.getProjectActivity(project.getProjectName(),
            project.getActivity(activityName) + "" + j), controller.getUser(userInitials));
        }

        for (User user : controller.getAllAvailableUsers()) {
            assertFalse(user.getUserID().equals(userInitials));
        }

    }

    @Given("user {string} is available")
    public void is_available(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
        for (User user : controller.getAllAvailableUsers()) {
            assertTrue(user.getUserID().equals(userInitials));
        }
    }
    
    @When("project leader assigns {string} to an activity")
    public void projectLeaderAssignsToAnActivity(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
        controller.addUserToActivity(activity, user);
        for (ProjectActivity activity : controller.getUser(userInitials).getJoinedActivities()) {
            assertTrue(activity.getName().equals(activityName));
        }
    }

    @Then("{string} is assigned to the activity")
    public void is_assigned_to_the_activity(String userInitals) {
        // Write code here that turns the phrase above into concrete actions
        for (ProjectActivity activity : controller.getUser(user.getUserID()).getJoinedActivities()) {
            assertTrue(activity.getName().equals(activityName));
        }
    }



    @Then("{string} is not assigned to the activity")
    public void is_not_assigned_to_the_activity(String userInitials) {
        // Write code here that turns the phrase above into concrete actions
        for (ProjectActivity activity : controller.getUser(user.getUserID()).getJoinedActivities()) {
            assertFalse(activity.getName().equals(activityName));
        }
    }
}
