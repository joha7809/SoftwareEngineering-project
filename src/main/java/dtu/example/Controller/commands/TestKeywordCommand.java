package dtu.example.Controller.commands;

import dtu.example.Controller.command_base.KeywordCommand;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;

import java.util.Map;

public class TestKeywordCommand extends KeywordCommand<String> {

    @Override
    public String getName() {
        return "testkeyword";
    }

    @Override
    public String getDescription() {
        return "testkeyword <arg1> [key=value] | A test command to validate KeywordCommand functionality.";
    }

    @Override
    public CommandResult<String> execute(String[] args, Map<String, String> kwargs) {
        // Build a response string to verify the parsed arguments
        StringBuilder response = new StringBuilder("Positional Arguments: ");
        response.append(String.join(", ", args));
        response.append("\nKeyword Arguments: ");
        kwargs.forEach((key, value) -> response.append(key).append("=").append(value).append(", "));

        // Return the response
        return new CommandResult<>(ReturnTypes.STRING, response.toString());
    }
}