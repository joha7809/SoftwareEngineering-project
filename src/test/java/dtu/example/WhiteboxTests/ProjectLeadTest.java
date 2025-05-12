package dtu.example.WhiteboxTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.ProjectLeadCommand;
import dtu.example.model.Project;
import dtu.example.model.User;

//August
public class ProjectLeadTest {
    // This is a test from tbe white box testing of the ProjectLead class
    private AppController mockController;
    private ProjectLeadCommand command;
    

    @BeforeEach
    public void setUp() {
        // Sets up mockito and the command we want to test
        mockController = mock(AppController.class);
        command = new ProjectLeadCommand(mockController);
    }

    @Test
    public void test1() {
        // We test for invalid length of arguments
        String[] args = {"projectName", "huba", "test"}; // Keep in mind this is the args, so it should be of the form <projct> <user>

        var result = command.execute(args);
        // We expect the result to be a failure
        assert result.getValue().success == false;
        System.out.println(result.getValue().message);
        assert result.getValue().message.contains("Error: Unexpected amount of args");
    }

    @Test
    public void test2() {
        // we test for the case where the project does not exist
        String[] args  = {"projectName", "huba"};
        // Mock that project is empty when controller.getProject(projectname) is called
        when(mockController.getProject("projectName")).thenReturn(null);
        
        var result = command.execute(args);
        // We expect the result to be a failure
        assert result.getValue().success == false;
        assert result.getValue().message.contains("Project does not exist");
    }

    @Test
    public void test3() {
        // in this test the project exists, but huba doesnt exist
        String[] args = {"projectName", "huba"};
        when(mockController.getProject("projectName")).thenReturn(new Project("projectName", "25001")); //Project exists
        when(mockController.getUser("huba")).thenReturn(null); // User does not exist
        var result = command.execute(args);
        // We expect the result to be a failure
        assert result.getValue().success == false;
        assert result.getValue().message.contains("User does not exist");
    }

    @Test
    public void test4() {
        // In this test both the project and user exists, and is succesfully assigned as lead
        String[] args = {"projectName", "huba"};
        when(mockController.getProject("projectName")).thenReturn(new Project("projectName", "25001")); //Project exists
        when(mockController.getUser("huba")).thenReturn(new User("huba")); // User exists

        var result = command.execute(args);
        // We expect the result to be a success
        assert result.getValue().success == true;
        assert result.getValue().message.contains("Project lead set successfully");
        // Verify that the project lead is set
        Project project = mockController.getProject("projectName");
        assert project.getProjectLead().getUserID().equals("huba");
    }
}
