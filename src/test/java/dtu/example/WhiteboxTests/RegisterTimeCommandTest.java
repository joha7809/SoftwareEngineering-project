package dtu.example.WhiteboxTests;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.RegisterTimeCommand;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.User;

import org.junit.jupiter.api.Test;

public class RegisterTimeCommandTest {
    // This is the unit tests for whitebox tests for the RegisterTimeCommand
    private RegisterTimeCommand command;
    private AppController mockController;

    @BeforeEach
    public void setUp() {
        mockController = mock(AppController.class);
        command = new RegisterTimeCommand(mockController);
    }

    @Test
    public void test1() {
        // First test for invalid amount of arguments
        String[] args = {"foo", "bar"};
        var result = command.execute(args);
        // Expect failure
        assert result.getValue().success == false;
        assert result.getValue().message.contains("Invalid number of arguments");
    }

    @Test
    public void test2() {
        // We test for the case where no user is logged in
        String[] args = {"foo"};
        when(mockController.getActiveUser()).thenReturn(null);
        var result = command.execute(args);
        // We expect a failure
        assert result.getValue().success == false;
        assert result.getValue().message.contains("No active user. Please log in first");
    }

    @Test
    public void test3() {
        // Test for the case where project is null
        String[] args = {"foo"};
        when(mockController.getActiveUser()).thenReturn(mock(User.class)); // Mock a user as active user
        when(mockController.getProject("foo")).thenReturn(null);
        var result = command.execute(args);
        // We expect a failure
        assert result.getValue().success == false;
        assert result.getValue().message.contains("Project not found");
    }

    @Test
    public void test4() {
        // Test for the case where no activities are found for the given project
        String[] args = {"foo"};
        when(mockController.getActiveUser()).thenReturn(mock(User.class)); // Mock a user as active user
        Project mockProject = mock(dtu.example.model.Project.class); // Mock a instance of a project

        when(mockController.getProject("foo")).thenReturn(mockProject); // Return this mocked instance
        when(mockProject.getAllActivities()).thenReturn(new ArrayList<>()); // Return an empty list of activities simulating no activities

        var result = command.execute(args);
        // We expect a failure
        assert result.getValue().success == false;
        assert result.getValue().message.contains("No activity found");
    }

    @Test
    public void test5() {
        // Test for the case where the project is valid and has activities
        // We have to mock the loop we are entered
        String[] args = {"foo"};

        // Simulated user input corresponding to registering 2.5 hours on activity "bar"
        // and then exiting the loop
        // The input is separated by new lines, simulating the user pressing enter after each input
        String userInput = "bar\n2.5\n\nno\nexit\n"; 
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream); // Redirect System.in to the simulated input

        // Override command as it needs to read from new System.in
        command = new RegisterTimeCommand(mockController);

        // Mock the datatypes needed for the loop
        Project mockProject = mock(dtu.example.model.Project.class);
        ProjectActivity mockActivity = mock(dtu.example.model.ProjectActivity.class);

        mockProject.setName("foo"); // Set the project name to "foo"
        User mockUser = mock(User.class);

        when(mockController.getActiveUser()).thenReturn(mock(User.class)); // Mock a user as active user
        when(mockController.getProject("foo")).thenReturn(mockProject); // Return this mocked instance

        // To simulate the project having activities, we add a mocked activity to the project
        when(mockProject.getAllActivities()).thenReturn(new ArrayList<ProjectActivity>() {
            {
                add(mockActivity);
            }
        });
        when(mockProject.getProjectName()).thenReturn("foo"); // Set the project name to "foo"
        when(mockActivity.getName()).thenReturn("bar"); // Set the activity name to "bar"

        when(mockController.getProjectActivity("foo", "bar")).thenReturn(mockActivity); // Return the mocked activity when we call getProjectActivity
        
        var result = command.execute(args);

        // We expect a success
        assert result.getValue().success == true;
        assert result.getValue().message.contains("Time registered successfully.");
    }


    
}
