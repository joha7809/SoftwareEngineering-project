package dtu.example.Controller.command_base;

import dtu.example.Controller.command_returns.CommandResult;

/**
 * Represents a command interface that defines the structure for commands
 * to be executed within the application.
 * 
 * <p>
 * Each command is expected to have a unique name, a description for help purposes,
 * and an implementation of the {@code execute} method to perform the command's logic.
 * The result of the command execution is encapsulated in a {@link CommandResult} object.
 * </p>
 * 
 * @param <T> The type of the result produced by the command execution. This allows
 *            commands to return different types of results while maintaining type safety.
 */

 //Johannes wrote this
public interface CommandInterface<T> {

    String getName(); // Mame of the function: used to match input string to function name
    String getDescription(); // String for printing in the help command

    /**
     * Executes the command with the given arguments.
     * 
     * <p>
     * The {@code execute} method contains the logic for performing the command's action.
     * It takes an array of arguments as input and returns a {@link CommandResult} object
     * encapsulating the result of the execution.
     * </p>
     * 
     * @param args An array of {@code String} arguments passed to the command.
     *             The arguments are typically parsed from user input.
     * @return A {@link CommandResult} object containing the result of the command execution.
     */
    CommandResult<T> execute(String[] args);
}
