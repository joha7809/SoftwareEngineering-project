package dtu.example.Controller.commands;

import dtu.example.model.Project;
import dtu.example.Controller.AppController;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.command_base.CommandInterface;

public class CreateActivityCommand implements CommandInterface<StatusMessage> {
    
    private final AppController controller;

    public CreateActivityCommand(AppController controller) {
        this.controller = controller;
    }

    public String getName() {
        return "createactivity";
    }

    public String getDescription() {
        return "createactivity <project> <activityname> | Create a new Activity from a given project, can be either a ID or name.";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 2) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return CommandResult.statusMessageResult(msg);
        }
        
        String projectName = args[0];
        String activityName = args[1];
        
        var result = controller.createProjectActivity(projectName, activityName);
        return CommandResult.statusMessageResult(result);
    }
}
