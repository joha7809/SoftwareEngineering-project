package dtu.example.Controller.commands;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.model.User;

public class LoginCommand implements CommandInterface<String> { // TODO: USE GENERICS AND RESULT WRAPPER

    private final AppController controller;

    public LoginCommand(AppController controller) {
        this.controller = controller;
    }

    public String getName() {
        return "login";
    }

    public String getDescription() {
        return "login <username> | Login to the system with a user.";
    }

    public CommandResult<String> execute(String[] args) {
        if (args.length != 1) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: login <username>");
        }
        String userName = args[0];

        if (controller.getUser(userName) == null) {
            return new CommandResult<>(ReturnTypes.STRING, "User not found. Please create a user first.");
        }

        controller.setActiveUser(controller.getUser(userName));
        return new CommandResult<>(ReturnTypes.STRING, "User " + userName + " logged in successfully.");
    }

}
