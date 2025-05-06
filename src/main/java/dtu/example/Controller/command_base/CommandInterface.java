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
public interface CommandInterface<T> {

    /**
     * Returns the name of the command.
     * 
     * <p>
     * The name is used to identify the command and should be unique across all commands.
     * It is typically used in user input to invoke the command.
     * </p>
     * 
     * @return The name of the command as a {@code String}.
     */
    String getName();

    /**
     * Returns the description of the command.
     * 
     * <p>
     * The description provides information about the command's purpose and usage.
     * It is typically displayed in help menus or documentation to assist users.
     * </p>
     * 
     * @return The description of the command as a {@code String}.
     */
    String getDescription();

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
