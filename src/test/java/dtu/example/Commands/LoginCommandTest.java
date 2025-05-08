package dtu.example.Commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.LoginCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginCommandTest {

    private AppController mockController;
    private LoginCommand loginCommand;

    @BeforeEach
    public void setUp() {
        // Mock the AppController
        mockController = mock(AppController.class);
        loginCommand = new LoginCommand(mockController);
    }

    @Test
    public void testExecuteWithValidUsername() {
        // Arrange
        String[] args = {"validUser"};
        when(mockController.setActiveUser("validUser"))
            .thenReturn(new StatusMessage(true, "User logged in successfully."));

        // Act
        CommandResult<StatusMessage> result = loginCommand.execute(args);

        // Assert
        assertTrue(result.getValue().success);
        assertEquals("User logged in successfully.", result.getValue().message);
        verify(mockController, times(1)).setActiveUser("validUser");
    }

    @Test
    public void testExecuteWithInvalidArguments() {
        // Arrange
        String[] args = {}; // No arguments provided

        // Act
        CommandResult<StatusMessage> result = loginCommand.execute(args);

        // Assert
        assertFalse(result.getValue().success);
        assertEquals("Invalid number of arguments. Usage: login <username>", result.getValue().message);
        verify(mockController, never()).setActiveUser(anyString());
    }

    @Test
    public void testExecuteWithNonExistentUser() {
        // Arrange
        String[] args = {"nonExistentUser"};
        when(mockController.setActiveUser("nonExistentUser"))
            .thenReturn(new StatusMessage(false, "Error: User not found."));

        // Act
        CommandResult<StatusMessage> result = loginCommand.execute(args);

        // Assert
        assertFalse(result.getValue().success);
        assertEquals("Error: User not found.", result.getValue().message);
        verify(mockController, times(1)).setActiveUser("nonExistentUser");
    }

    @Test
    public void testCommandName() {
        // Verify that the command returns the expected name
        String cmdName = loginCommand.getName();
        assertEquals("login", cmdName);
    }

    @Test
    public void testCommandDescription() {
        // Verify that the command returns the expected description
        String description = loginCommand.getDescription();
        assertEquals("login <username> | Login to the system with a user.", description);
    }
}