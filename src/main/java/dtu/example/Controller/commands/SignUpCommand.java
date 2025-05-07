package dtu.example.Controller.commands;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.User;

public class SignUpCommand implements CommandInterface<StatusMessage> { // TODO: USE GENERICS AND RESULT WRAPPER

    private final AppManager manager;

    public SignUpCommand(AppManager manager) {
        this.manager = manager;
    }

    public String getName() {
        return "signup";
    }

    public String getDescription() {
        return "signup <username> | Sign Up to the system with a user.";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 1) {
            var error = StatusMessage.error("Invalid number of arguments. Usage: " + getDescription());
            return CommandResult.statusMessageResult(error);
        }
        String userName = args[0];

        var result = manager.createUser(userName);
        return CommandResult.statusMessageResult(result);
    }

}
