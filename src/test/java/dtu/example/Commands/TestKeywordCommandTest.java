package dtu.example.Commands;

import static org.junit.jupiter.api.Assertions.*;

import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.commands.TestKeywordCommand;
import dtu.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestKeywordCommandTest {

    private TestKeywordCommand testKeywordCommand;

    @BeforeEach
    public void setUp() {
        testKeywordCommand = new TestKeywordCommand();
    }

    @Test
    public void testExecuteWithValidArguments() {
        String[] args = {"arg1"};
        Map<String, String> kwargs = new HashMap<>();
        kwargs.put("key", "value");

        CommandResult<String> result = testKeywordCommand.execute(args, kwargs);

        assertNotNull(result);
        assertTrue(result.getValue().contains("Positional Arguments: arg1"));
        assertTrue(result.getValue().contains("Keyword Arguments: key=value"));
    }

    @Test
    public void testExecuteWithNoArguments() {
        String[] args = {};
        Map<String, String> kwargs = new HashMap<>();

        CommandResult<String> result = testKeywordCommand.execute(args, kwargs);

        assertNotNull(result);
        assertTrue(result.getValue().contains("Positional Arguments: "));
        assertTrue(result.getValue().contains("Keyword Arguments: "));
    }
}
