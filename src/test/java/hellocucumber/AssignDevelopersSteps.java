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

    public AssignDevelopersSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    @Given("user {string} is available in week {string}")
    public void userIsAvailable(String userID, String weekNumber){
        User user = controller.getUser(userID);
        assertTrue(controller.getAvailableUsers(weekNumber).contains(user));
    }

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

    @When("project leader assigns {string} to the activity {string}")
    public void projectLeaderAssignsToTheActivity(String userID, String activityName){
        //TODO: Lidt cursed men det virker forhåbentligt
        Project project = (sharedContext.getCurrentProject());
        project = project != null ? project : null;
        String projectName = project != null ? project.getProjectName() : null;
        result = controller.addUserToActivity(projectName, activityName, userID);
        sharedContext.setResult(result);
    }

    @Then("{string} is assigned to the activity")
    public void isAssignedToTheActivity(String userID){
        assertTrue(result.success);
    }

    }

//     private String activityName = "Metting";
//     private Project project;
//     private User user;
//     private ProjectActivity activity;

//     @Given("user {string} is unavailable")
//     public void isUnavailable(String userInitials) {
//         // Write code here that turns the phrase above into concrete actions
//         project = sharedContext.getCurrentProject();

//         for(int j = 0; j < 25; j++){
//             // Manually create activities for the project
//             // This is to skip login validation etc.
//             ProjectActivity activity = new ProjectActivity(activityName + "" + j);
//             project.addActivity(activity);


//             controller.addUserToActivity(controller.getProjectActivity(project.getProjectName(),
//             project.getActivity(activityName + "" + j).getName() + "" + j), controller.getUser(userInitials));
//         }
//         Boolean isAvailable = false;
//         for (User user : controller.getAllAvailableUsers()) {
//             if(user.getUserID().equals(userInitials)){
//                 isAvailable = true;
//             }
//         }
//         assertFalse(isAvailable);

//     }

//     @Given("user {string} is available")
//     public void is_available(String userInitials) {
//         // Write code here that turns the phrase above into concrete actions
//         Boolean userAvailable = false;
//         for (User user : controller.getAllAvailableUsers()) {
//             if(user.getUserID().equals(userInitials)){
//                 userAvailable = true;
//             }
            
//         }
//         assertTrue(controller.isUserAvailable(userInitials));
//         assertTrue(userAvailable);
//         //assertTrue(controller.getUser(userInitials).isAvailable());
//     }
    
//     @When("project leader assigns {string} to an activity")
//     public void projectLeaderAssignsToAnActivity(String userInitials) {
//         // Write code here that turns the phrase above into concrete actions
//         Boolean isAssign = false;
//         User us = controller.getUser(userInitials);
//         activity = sharedContext.getProjectActivity();
//         controller.addUserToActivity(activity, us);
//         for(ProjectActivity activity : controller.getUser(userInitials).getJoinedActivities()) {
//             if(activity.getName().equals(activityName)){
//                 isAssign = true;
//             }
//         }
//         assertTrue(isAssign);
//     }

//     @Then("{string} is assigned to the activity")
//     public void is_assigned_to_the_activity(String userInitals) {
//         // Write code here that turns the phrase above into concrete actions
//         for (ProjectActivity activity : controller.getUser(user.getUserID()).getJoinedActivities()) {
//             assertTrue(activity.getName().equals(activityName));
//         }
//     }



//     @Then("{string} is not assigned to the activity")
//     public void is_not_assigned_to_the_activity(String userInitials) {
//         // Write code here that turns the phrase above into concrete actions
//         for (ProjectActivity activity : controller.getUser(user.getUserID()).getJoinedActivities()) {
//             assertFalse(activity.getName().equals(activityName));
//         }
//     }

    
// }

