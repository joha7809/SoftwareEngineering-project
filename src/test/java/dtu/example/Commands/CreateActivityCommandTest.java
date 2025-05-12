package dtu.example.Commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.CreateActivityCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Kerem
public class CreateActivityCommandTest {

    private AppController mockController;
    private CreateActivityCommand createActivityCommand;

    @BeforeEach
    public void setUp() {
        // Mock the AppController
        mockController = mock(AppController.class);
        createActivityCommand = new CreateActivityCommand(mockController);
    }

    @Test
    public void testExecuteWithValidArguments() {
        // Arrange
        String[] args = {"TestProject", "TestActivity"};
        when(mockController.createProjectActivity("TestProject", "TestActivity"))
            .thenReturn(new StatusMessage(true, "Activity created successfully."));

        // Act
        CommandResult<StatusMessage> result = createActivityCommand.execute(args);

        // Assert
        assertTrue(result.getValue().success);
        assertEquals("Activity created successfully.", result.getValue().message);
        verify(mockController, times(1)).createProjectActivity("TestProject", "TestActivity");
    }

    @Test
    public void testExecuteWithInvalidArguments() {
        // Arrange
        String[] args = {}; // No arguments provided

        // Act
        CommandResult<StatusMessage> result = createActivityCommand.execute(args);

        // Assert
        assertFalse(result.getValue().success);
        assertEquals("Error: Unexpected amount of args" + "\n" + createActivityCommand.getDescription(), result.getValue().message);
        verify(mockController, never()).createProjectActivity(anyString(), anyString());
    }

    @Test
    public void testExecuteWithDuplicateActivityName() {
        // Arrange
        String[] args = {"TestProject", "DuplicateActivity"};
        when(mockController.createProjectActivity("TestProject", "DuplicateActivity"))
            .thenReturn(new StatusMessage(false, "Activity already exists."));

        // Act
        CommandResult<StatusMessage> result = createActivityCommand.execute(args);

        // Assert
        assertFalse(result.getValue().success);
        assertEquals("Activity already exists.", result.getValue().message);
        verify(mockController, times(1)).createProjectActivity("TestProject", "DuplicateActivity");
    }

    @Test
    public void testCommandName() {
        // Verify that the command returns the expected name
        String cmdName = createActivityCommand.getName();
        assertEquals("createactivity", cmdName);
    }

    @Test
    public void testCommandDescription() {
        // Verify that the command returns the expected description
        String description = createActivityCommand.getDescription();
        assertEquals("createactivity <project> <activityname> | Create a new Activity from a given project, can be either a ID or name.", description);
    }
}