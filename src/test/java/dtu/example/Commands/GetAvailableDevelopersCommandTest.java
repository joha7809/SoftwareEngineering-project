package dtu.example.Commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.GetAvailableDevelopersCommand;
import dtu.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Adam
public class GetAvailableDevelopersCommandTest {

    private AppController mockController;
    private GetAvailableDevelopersCommand getAvailableDevelopersCommand;

    @BeforeEach
    public void setUp() {
        mockController = mock(AppController.class);
        getAvailableDevelopersCommand = new GetAvailableDevelopersCommand(mockController);
    }

    @Test
    public void testExecuteWithValidWeek() {
        String[] args = {"34"};
        when(mockController.getAvailableUsers("34")).thenReturn(new ArrayList<>());

        CommandResult<StatusMessage> result = getAvailableDevelopersCommand.execute(args);

        assertTrue(result.getValue().success);
        verify(mockController, times(1)).getAvailableUsers("34");
    }

    @Test
    public void testExecuteWithInvalidWeek() {
        String[] args = {"invalidWeek"};

        CommandResult<StatusMessage> result = getAvailableDevelopersCommand.execute(args);

        assertFalse(result.getValue().success);
        assertTrue(result.getValue().message.contains("Please enter a valid numeric week"));
    }
}
