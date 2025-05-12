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
//Nikolaj
public class AssignDevelopersSteps {

    private final SharedContext sharedContext;
    private final AppController controller;
    private StatusMessage result;
    //Nikolaj
    public AssignDevelopersSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }
    //Nikolaj
    @Given("user {string} is available in week {string}")
    public void userIsAvailable(String userID, String weekNumber){
        User user = controller.getUser(userID);
        assertTrue(controller.getAvailableUsers(weekNumber).contains(user));
    }
    //Nikoalj
    @Given("the activity {string} starts in week {string} and ends in week {string}")
    public void theActivityStartsInWeekAndEndsInWeek(String activityName, String startWeek, String endWeek){

        //TODO: Lidt scuffed måde at få fat på activity / project etc men øh...

        String projectName = sharedContext.getCurrentProject().getProjectName();

        var res1 = controller.setActivityStartWeek(sharedContext.getCurrentProject().getProjectName(), sharedContext.getProjectActivity().getName(), startWeek); 
        var res2 = controller.setActivityEndWeek(sharedContext.getCurrentProject().getProjectName(), sharedContext.getProjectActivity().getName(), endWeek);
        
        assertTrue(res1.success, res1.message);
        assertTrue(res2.success, res2.message);


        assertTrue(controller.getProjectActivity(projectName, activityName).getStartWeek().equals(startWeek));
        assertTrue(controller.getProjectActivity(projectName, activityName).getEndWeek().equals(endWeek));

    }
    //Nikolaj
    @When("project leader assigns {string} to the activity {string}")
    public void projectLeaderAssignsToTheActivity(String userID, String activityName){
        //TODO: Lidt cursed men det virker forhåbentligt
        Project project = (sharedContext.getCurrentProject());
        project = project != null ? project : null;
        String projectName = project != null ? project.getProjectName() : null;
        result = controller.addUserToActivity(projectName, activityName, userID);
        sharedContext.setResult(result);
    }
    //Nikolaj
    @Then("{string} is assigned to the activity")
    public void isAssignedToTheActivity(String userID){
        assertTrue(result.success);
    }

}

