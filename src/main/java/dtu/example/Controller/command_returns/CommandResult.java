package dtu.example.Controller.command_returns;

import java.io.ObjectInputFilter.Status;

//Written by Johannes

/**
 * A generic class representing the result of a command execution.
 * 
 * @param <T> The type of the value returned by the command.
 * 
 * @author Johannes
 */

 //Johannes
public class CommandResult<T> {

    ReturnTypes type;
    T value;

    /**
     * Constructs a new CommandResult with the specified type and value.
     * 
     * @param type  The type of the result.
     * @param value The value returned by the command.
     */

     //Johannes
    public CommandResult(ReturnTypes type, T value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Returns the type of the CommandResult. 
     * The type comes from the ReturnTypes enum, and is used for handling in the View Clas.
     * @return The type of the result as a {@link ReturnTypes} enum
     */

     //Johannes
    public ReturnTypes getType() {
        return this.type;
    }

    /**
     * Returns the value of the CommandResult.
     * The value is of a generic type and represents the possible returns of command execution
     * 
     * @return The value of the result as a generic type {@code T}
     */
    //Johannes
    public T getValue() {
        return this.value;
    }

    // Static factories for easier returns

    //Johannes
    public static CommandResult<StatusMessage> statusMessageResult(StatusMessage msg){
        return new CommandResult<StatusMessage>(ReturnTypes.STATUS_MESSAGE, msg);
    }
}
