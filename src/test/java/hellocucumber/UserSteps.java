package hellocucumber;

import static org.junit.jupiter.api.Assertions.*;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserSteps {
    private final SharedContext sharedContext;
    private final AppController controller;
    private StatusMessage result;

    public UserSteps(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
        this.controller = sharedContext.getController();
    }

    @When("a user with the ID {string} is created")
    public void a_user_with_the_ID_is_created(String userID) {
        result = controller.createUser(userID);
        sharedContext.setResult(result);
    }

    @Then("the user with the ID {string} exists")
    public void the_user_with_the_ID_exists(String userID) {
        User user = controller.getUser(userID);
        assertNotNull(user);
    }
}