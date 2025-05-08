// package hellocucumber;

// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.Then;
// import io.cucumber.java.en.When;
// import dtu.example.Controller.AppController;
// import dtu.example.Controller.command_returns.StatusMessage;
// import dtu.example.model.User;


// import static org.junit.jupiter.api.Assertions.*;

// public class CreateFixedActivitySteps {
//     private final SharedContext sharedContext;
//     private final AppController controller;
//     private StatusMessage result;

//     public CreateFixedActivitySteps(SharedContext sharedContext)
//     {
//         this.sharedContext = sharedContext;
//         this.controller = sharedContext.getController();

//     }
    

    // @When("the user types the command {string} and arguments timeStart {string} timeEnd {string} type {string}")
    // public void the_user_types_the_command_and_arguments_timeStart_timeEnd_type(String command, String timeStart, String timeEnd, String type) {
    //     // Write code here that turns the phrase above into concrete actions
    //     result = controller.createFixedActivity(command, timeStart, timeEnd, type);
    //     sharedContext.setResult(result);
    // }

    @When("the user creates a fixed activity with timeStart {string} timeEnd {string} type {string}")
    public void the_user_types_the_command_and_arguments_timeStart_timeEnd_type(String timeStart, String timeEnd, String type) {
        // Write code here that turns the phrase above into concrete actions
        //result = controller.createFixedActivity(command, timeStart, timeEnd, type);
        result = controller.createFixedActivity(timeStart, timeEnd, type);
        sharedContext.setResult(result);
    }

//     @Then("a fixed activity is created with the start date {string}, end date {string} and type {string}")
//     public void a_fixed_activity_is_created_with_the_user_start_date_end_date_and_type(String timeStart, String timeEnd, String type) {
//         // TODO: Implement logic to verify the fixed activity creation
        
        
        for (int i = 0; i < controller.getFixedActivities().size(); i++){
            if(controller.getFixedActivities().get(i).getStartDate().equalsIgnoreCase(timeStart) && controller.getFixedActivities().get(i).getEndDate().equalsIgnoreCase(timeEnd) && controller.getFixedActivities().get(i).getName().equalsIgnoreCase(type)){
            //if(controller.getFixedActivities().get(i).getActivityName().equalsIgnoreCase(type)){
                assertTrue(sharedContext.getResult().success);
            }
            
        }
        assertTrue(sharedContext.getResult().success);
    }

//     @Then("the system displays an error message: {string}")
//     public void the_system_displays_an_error_message(String expectedMessage) {
//         // TODO: Implement logic to verify the error message
//     }

// }
