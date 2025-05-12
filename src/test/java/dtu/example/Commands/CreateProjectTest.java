package dtu.example.Commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.CreateProjectCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Kerem
public class CreateProjectTest {

    private AppController mockManager;
    private CreateProjectCommand createProjectCommand;

    @BeforeEach
    public void setUp() {
        // Mock the AppManager
        mockManager = mock(AppController.class);
        createProjectCommand = new CreateProjectCommand(mockManager);
    }

    @Test
    public void testExecuteWithValidArguments() {
        // Arrange
        String[] args = {"TestProject"};
        when(mockManager.createProject("TestProject"))
            .thenReturn(new StatusMessage(true, "Project created successfully."));

        // Act
        CommandResult<StatusMessage> result = createProjectCommand.execute(args);

        // Assert
        assertTrue(result.getValue().success);
        assertEquals("Project created successfully.", result.getValue().message);
        verify(mockManager, times(1)).createProject("TestProject");
    }

    @Test
    public void testExecuteWithInvalidArguments() {
        // Arrange
        String[] args = {}; // No arguments provided

        // Act
        CommandResult<StatusMessage> result = createProjectCommand.execute(args);

        // Assert
        assertFalse(result.getValue().success);
        assertEquals("Error: Unexpected amount of args" + "\n" + createProjectCommand.getDescription(), result.getValue().message);
        verify(mockManager, never()).createProject(anyString());
    }

    @Test
    public void testExecuteWithDuplicateProjectName() {
        // Arrange
        String[] args = {"DuplicateProject"};
        when(mockManager.createProject("DuplicateProject"))
            .thenReturn(new StatusMessage(false, "Project already exists."));

        // Act
        CommandResult<StatusMessage> result = createProjectCommand.execute(args);

        // Assert
        assertFalse(result.getValue().success);
        assertEquals("Project already exists.", result.getValue().message);
        verify(mockManager, times(1)).createProject("DuplicateProject");
    }

    @Test
    public void testCommandName() {
        // Verify that the command returns expected name
        String cmdName = createProjectCommand.getName();
        assertEquals(cmdName, "createproject");
    }
}