package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.User;


import static org.junit.jupiter.api.Assertions.*;

public class CreateFixedActivitySteps {
    @Given("user {string} exists")
    public void user_exists(String username) {
        // TODO: Implement logic to ensure the user exists
    }

    @When("the user types the command {string} and arguments {string} {string} {string}")
    public void the_user_types_the_command_createFixedActivity(String command, String timeStart, String timeEnd, String type) {
        // TODO: Implement logic to handle the command
    }

    @Then("a fixed activity is created with the user {string}, start date {string}, end date {string} and type {string}")
    public void a_fixed_activity_is_created_with_the_user_start_date_end_date_and_type(String username, String timeStart, String timeEnd, String type) {
        // TODO: Implement logic to verify the fixed activity creation
    }

    @Then("the system displays an error message: {string}")
    public void the_system_displays_an_error_message(String expectedMessage) {
        // TODO: Implement logic to verify the error message
    }

}
