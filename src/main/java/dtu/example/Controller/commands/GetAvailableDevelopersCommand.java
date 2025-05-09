package dtu.example.Controller.commands;

import dtu.example.model.Project;
import dtu.example.model.User;
import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.command_base.CommandInterface;

public class GetAvailableDevelopersCommand implements CommandInterface<StatusMessage> {
    
    private final AppController controller;

    public GetAvailableDevelopersCommand(AppController controller) {
        this.controller = controller;
    }

    public String getName() {
        return "getavailabledevelopers";
    }

    public String getDescription() {
        return "getavailabledevelopers <week> | Gets a list of available developers for a week. Weeks are whole numbers";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 1) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return CommandResult.statusMessageResult(msg);
        }
        
        String week = args[0];

        // Validate that the input is numeric
        if (!isNumeric(week)) {
            var msg = StatusMessage.error("Warning: Please enter a valid numeric week!");
            return CommandResult.statusMessageResult(msg);
        }

        if (Double.valueOf(week) % 1 != 0) {
            var msg = StatusMessage.error("Warning: Please only use whole numbers!");
            return CommandResult.statusMessageResult(msg);
        }

        if (Integer.valueOf(week) > 52) {
            var msg = StatusMessage.error("Warning: Please enter a week from 1 - 52!");
            return CommandResult.statusMessageResult(msg);
        }
        StringBuilder users = new StringBuilder();
        for (User user: controller.getAvailableUsers(week)) {
            users.append(user.getUserID()).append("\n");
        }

        StatusMessage result = StatusMessage.success("Available users for week " + week + ":" + "\n" + users.toString());
        //System.out.println(controller.getAllUsers());
        return CommandResult.statusMessageResult(result);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
