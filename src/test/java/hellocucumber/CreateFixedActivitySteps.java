package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import dtu.example.model.User;


import static org.junit.jupiter.api.Assertions.*;


public class CreateFixedActivitySteps {
    public User user;
    public String enteredType;

    @Given("Given user {string} exists")
    public void Given_user_exists(String s) {
        // Write code here that turns the phrase above into concrete actions
        user = new User(s);
        assertTrue(user.getUserID() == s);
    }

    @When("user enters the type {string} for the fixed activity")
    public void user_enters_the_type_for_the_fixed_activity(String s) {
        // Write code here that turns the phrase above into concrete actions
        enteredType = s;
        assertTrue(enteredType != null);
    }

    @When("user enters the timeEnd {string} for the fixed activity")
    public void user_enters_the_timeEnd_for_the_fixed_activity(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("user enters the timeStart {string} for the fixed activity")
    public void user_enters_the_timeStart_for_the_fixed_activity(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("user enters the timeEnd {int} for the fixed activity")
    public void user_enters_the_timeEnd_for_the_fixed_activity(int i) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("user enters the timeStart {int} for the fixed activity")
    public void user_enters_the_timeStart_for_the_fixed_activity(int i) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("a fixed activity is created with the user {string}, start date {string}, end date {string} and type {string}")
    public void a_fixed_activity_is_created_with_the_user_start_date_end_date_and_type(String s, String s2, String s3, String s4) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("a string code will state {string}")
    public void aStringCodeWillState(String string) {
    // Write code here that turns the phrase above into concrete actions
    
    }

}
