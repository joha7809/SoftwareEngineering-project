package dtu.example.Controller.commands;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;

public class SignUpCommand implements CommandInterface<String> {

    private final AppController controller;

    public SignUpCommand(AppController controller) {
        this.controller = controller;
    }

    public String getName() {
        return "signup";
    }

    public String getDescription() {
        return "signup <username> | Create a new user in the system.";
    }

    public CommandResult<String> execute(String[] args) {
        if (args.length != 1) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return new CommandResult<>(ReturnTypes.STRING, msg.message);
        }

        String userName = args[0];
        var result = controller.createUser(userName);
        return new CommandResult<>(ReturnTypes.STRING, result.message);
    }
}
