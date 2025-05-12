package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lu.a;
import io.cucumber.java.lu.ugeholl.ugeholls;

//Adam
public class GetAvailableDevelopersSteps {

    //TODO: make test handle start AND end week, currently we pass same argument to both
    
    private final SharedContext sharedContext;
    private final AppController controller;
    private ArrayList<User> result;

    public GetAvailableDevelopersSteps(SharedContext sharedContext)
    {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    @Given("there are {string} users in the system")
    public void there_are_users_in_the_system(String numberOfUsers) {
        int number = Integer.valueOf(numberOfUsers);

        for (int i = 0; i < number; i++) {
            controller.createUser("use" + (char) ('A' +i));
        }

    }

    @Given("each user has fewer than {string} activities assigned in week {string}")
    public void each_user_has_fewer_than_activities_assigned_in_week(String maxActivities, String week) {
        Project project = sharedContext.getCurrentProject();
        HashMap<String, User> users = controller.getAllUsers();
        int maxActivityCount = Integer.valueOf(maxActivities);

        // Create a fixed set of activities for the project
        ArrayList<ProjectActivity> activities = new ArrayList<>();
        for (int i = 1; i <= maxActivityCount; i++) {
            ProjectActivity activity = new ProjectActivity("Activity" + i);
            activity.setStartWeek(week);
            activity.setEndWeek(week);
            project.addActivity(activity);
            activities.add(activity);
        }

        // Assign each user to the activities
        for (User user : users.values()) {
            if (user.getUserID().equals(controller.getActiveUser().getUserID())) {
                continue; // Ignore the logged-in user from the background
            }

            for (int i = 0; i < maxActivityCount - 1; i++) { // Ensure fewer than maxActivities
                ProjectActivity activity = activities.get(i);
                var msg = controller.addUserToActivity(project.getProjectName(), activity.getName(), user.getUserID());
                assertTrue(msg.success);
            }
        }
    }

    @Given("{string} users have {string} or more activities assigned in week {string}")
    public void users_have_or_more_activities_assigned_in_week(String numUsers, String minActivities, String week) {
        Project project = sharedContext.getCurrentProject();
        HashMap<String, User> users = controller.getAllUsers();
        int numberOfUsers = Integer.parseInt(numUsers);
        int minimumActivities = Integer.parseInt(minActivities);

        // Create a fixed set of activities for the project
        ArrayList<ProjectActivity> activities = new ArrayList<>();
        for (int i = 1; i <= minimumActivities + 5; i++) { // Ensure we have enough activities
            ProjectActivity activity = new ProjectActivity("Activity" + i);
            activity.setStartWeek(week);
            activity.setEndWeek(week);
            project.addActivity(activity);
            activities.add(activity);
        }

        // Assign the specified number of users to the activities
        int assignedUsers = 0;
        for (User user : users.values()) {
            if (assignedUsers >= numberOfUsers) {
                break; // Stop once we've assigned the required number of users
            }

            if (user.getUserID().equals(controller.getActiveUser().getUserID())) {
                continue; // Ignore the logged-in user from the background
            }

            for (int i = 0; i < minimumActivities; i++) { // Assign the minimum number of activities
                ProjectActivity activity = activities.get(i);
                var msg = controller.addUserToActivity(project.getProjectName(), activity.getName(), user.getUserID());
                assertTrue(msg.success);
            }

            assignedUsers++;
        }

    // Ensure we assigned the correct number of users
    assertTrue(assignedUsers == numberOfUsers, "Expected " + numberOfUsers + " users to be assigned, but got " + assignedUsers);
    }

    @Given("each user has {string} or more activities assigned in week {string}")
    public void each_user_has_or_more_activities_assigned_in_week(String minActivities, String week) {
        // Write code here that turns the phrase above into concrete actions
        Project project = sharedContext.getCurrentProject();
        HashMap<String, User> users = controller.getAllUsers();
        int maxActivityCount = Integer.valueOf(minActivities)+5;

        // Create a fixed set of activities for the project
        ArrayList<ProjectActivity> activities = new ArrayList<>();
        for (int i = 0; i <= maxActivityCount; i++) { // ensure we are above number
            ProjectActivity activity = new ProjectActivity("Activity" + i);
            activity.setStartWeek(week);
            activity.setEndWeek(week);
            project.addActivity(activity);
            activities.add(activity);
        }

        // Assign each user to the activities
        for (User user : users.values()) {
            if (user.getUserID().equals(controller.getActiveUser().getUserID())) {
                continue; // Ignore the logged-in user from the background
            }

            for (int i = 0; i <= maxActivityCount; i++) { 
                ProjectActivity activity = activities.get(i);
                var msg = controller.addUserToActivity(project.getProjectName(), activity.getName(), user.getUserID());
                assertTrue(msg.success);
            }
        }
    }

    @Given("user {string} has {string} activities assigned in week {string}")
    public void user_has_activities_assigned_in_week(String userName, String numActivities, String week) {
        // Write code here that turns the phrase above into concrete actions
        User user = controller.getUser(userName);
        assertTrue(user != null, "User " + userName + " not found");
        Project project = sharedContext.getCurrentProject();
        int number = Integer.valueOf(numActivities);
        for (int i = 0; i < number; i++) {
            ProjectActivity activity = new ProjectActivity("Activity" + i);
            activity.setStartWeek(week);
            activity.setEndWeek(week);
            project.addActivity(activity);
            var msg = controller.addUserToActivity(project.getProjectName(), activity.getName(), user.getUserID());
            assertTrue(msg.success);
        }
        // Check that the user has the correct number of activities
        int activityCount = 0;
        for (ProjectActivity activity : user.getJoinedActivities()) {
            if (activity.getStartWeek().equals(week)) {
                activityCount++;
            }
        }
        assertTrue(activityCount == number, "User " + userName + " does not have " + numActivities + " activities assigned in week " + week);
    }
    
    @When("the user requests the list of available users for week {string}")
    public void the_user_requests_the_list_of_available_users_for_week(String week) {
        result = controller.getAvailableUsers(week);
    }

    @Then("the user receives a list of {string} available users")
    public void the_user_receives_a_list_of_available_users(String s) {
        // Write code here that turns the phrase above into concrete actions
        int number = Integer.valueOf(s);
        assertTrue(result.size() == number, "Expected " + number + " available users, but got " + result.size());
    }

    @Then("the user receives an empty list of available users")
    public void the_user_receives_an_empty_list_of_available_users() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(result.isEmpty(), "Expected an empty list of available users, but got " + result.size());
    }

    @Then("user {string} is not included in the list of available users")
    public void user_is_not_included_in_the_list_of_available_users(String s) {
        // Write code here that turns the phrase above into concrete actions
        boolean userFound = false;
        for (User user : result) {
            if (user.getUserID().equals(s)) {
                userFound = true;
                break;
            }
        }
        assertFalse(userFound, "User " + s + " should not be in the list of available users");
    }

    @Then("user {string} is included in the list of available users")
    public void user_is_included_in_the_list_of_available_users(String s) {
        // Write code here that turns the phrase above into concrete actions
        boolean userFound = false;
        for (User user : result) {
            if (user.getUserID().equals(s)) {
                userFound = true;
                break;
            }
        }
        assertTrue(userFound, "User " + s + " should be in the list of available users");
    }
}

    