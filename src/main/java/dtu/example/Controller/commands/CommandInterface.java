package dtu.example.Controller.commands;

import dtu.example.Controller.command_returns.CommandResult;

public interface CommandInterface<T> {
    String getName(); // function name

    String getDescription(); // For help
    
    CommandResult<T> execute(String[] args);
}
