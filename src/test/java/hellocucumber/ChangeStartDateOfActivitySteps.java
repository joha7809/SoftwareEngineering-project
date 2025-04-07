package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ChangeStartDateOfActivitySteps {

    private boolean userLoggedIn;
    private boolean activityExists;
    private String startDate;
    private String errorMessage;

    @Given("user is logged in")
    public void userIsLoggedIn() {
        this.userLoggedIn = true;
    }

    @Given("an activity exists")
    public void anActivityExists() {
        this.activityExists = true;
    }

    @Given("start date is set")
    public void startDateIsSet() {
        this.startDate = "2025-04-01"; // Example initial start date
    }

    @Given("start date is not set")
    public void startDateIsNotSet() {
        this.startDate = null;
    }

    @When("user updates the start date")
    public void userUpdatesTheStartDate() {
        if (userLoggedIn && activityExists && startDate != null) {
            startDate = "2025-04-10"; // Example updated start date
        } else {
            errorMessage = "Error: Cannot update start date";
        }
    }

    @When("the user updates the start date to {string}")
    public void theUserUpdatesTheStartDateTo(String newStartDate) {
        if (userLoggedIn && activityExists) {
            if (newStartDate.isEmpty()) {
                errorMessage = "Error: Start date cannot be empty";
            } else {
                startDate = newStartDate;
            }
        } else {
            errorMessage = "Error: Cannot update start date";
        }
    }

    @Then("the start date is updated")
    public void theStartDateIsUpdated() {
        assert startDate.equals("2025-04-10"); // Ensure the start date was updated
    }

    @Then("a error message is printed")
    public void aErrorMessageIsPrinted() {
        assert errorMessage != null && !errorMessage.isEmpty();
    }
}
