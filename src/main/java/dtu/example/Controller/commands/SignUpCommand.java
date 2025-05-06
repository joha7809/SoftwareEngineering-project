package dtu.example.Controller.commands;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.model.User;

public class SignUpCommand implements CommandInterface<String> { // TODO: USE GENERICS AND RESULT WRAPPER

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

    public CommandResult<String> execute(String[] args) {
        if (args.length != 1) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: signup <username>");
        }
        String userName = args[0];

        if (manager.getUser(userName) != null) {
            return new CommandResult<>(ReturnTypes.STRING, "User already exists. Please choose a new user.");
        }

        manager.createUser(userName);
        manager.setActiveUser(manager.getUser(userName));
        return new CommandResult<> (ReturnTypes.STRING, "User " + userName + " successfully created and logged insign.");
    }

}
