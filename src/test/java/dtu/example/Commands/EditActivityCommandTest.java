package dtu.example.Commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.commands.EditActivityCommand;
import dtu.example.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EditActivityCommandTest {

    private AppController mockController;
    private EditActivityCommand editActivityCommand;

    @BeforeEach
    public void setUp() {
        mockController = mock(AppController.class);
        editActivityCommand = new EditActivityCommand(mockController);
    }

    @Test
    public void testExecuteWithValidArguments() {
        String[] args = {"project1", "activity1"};
        Map<String, String> kwargs = new HashMap<>();
        kwargs.put("startweek", "34");
        kwargs.put("endweek", "36");

        when(mockController.getProject("project1")).thenReturn(mock(Project.class));
        when(mockController.getProjectActivity("project1", "activity1")).thenReturn(mock(ProjectActivity.class));

        CommandResult<StatusMessage> result = editActivityCommand.execute(args, kwargs);

        assertTrue(result.getValue().success);
        verify(mockController, times(1)).getProject("project1");
        verify(mockController, times(1)).getProjectActivity("project1", "activity1");
    }

    @Test
    public void testExecuteWithInvalidArguments() {
        String[] args = {"project1"};
        Map<String, String> kwargs = new HashMap<>();

        CommandResult<StatusMessage> result = editActivityCommand.execute(args, kwargs);

        assertFalse(result.getValue().success);
        assertTrue(result.getValue().message.contains("Invalid number of arguments"));
    }
}
