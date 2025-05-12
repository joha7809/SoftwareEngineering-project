package dtu.example.Controller.commands;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.User;

//Kerem wrote this
public class LoginCommand implements CommandInterface<StatusMessage> { // TODO: USE GENERICS AND RESULT WRAPPER

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

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 1) {
            var msg = StatusMessage.error("Invalid number of arguments. Usage: login <username>");
            return CommandResult.statusMessageResult(msg);
        }
        String userName = args[0];
        var result = controller.setActiveUser(userName);

        return CommandResult.statusMessageResult(result);
    }

}
