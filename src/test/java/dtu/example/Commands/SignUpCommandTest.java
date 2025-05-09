package dtu.example.Commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.SignUpCommand;
import dtu.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignUpCommandTest {

    private AppController mockController;
    private SignUpCommand signUpCommand;

    @BeforeEach
    public void setUp() {
        mockController = mock(AppController.class);
        signUpCommand = new SignUpCommand(mockController);
    }

    @Test
    public void testExecuteWithValidUsername() {
        String[] args = {"newUser"};
        when(mockController.createUser("newUser"))
            .thenReturn(new StatusMessage(true, "User created successfully."));

        CommandResult<String> result = signUpCommand.execute(args);

        assertTrue(result.getValue() != null);
        assertEquals("User created successfully.", result.getValue());
        verify(mockController, times(1)).createUser("newUser");
    }

    @Test
    public void testExecuteWithInvalidArguments() {
        String[] args = {};

        CommandResult<String> result = signUpCommand.execute(args);

        assertTrue(result.getValue() != null);
        assertTrue(result.getValue().contains(StatusMessage.uneexpectedArguments(signUpCommand.getDescription()).message));
    }
}
