package dtu.example.Controller.commands;

import dtu.example.model.Project;
import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.Controller.command_base.CommandInterface;


public class CreateActivityCommand implements CommandInterface<StatusMessage> {
    
    private final AppManager manager;

    public CreateActivityCommand(AppManager manager){
        this.manager = manager;
    }

    public String getName(){
        return "createactivity";
    }

    public String getDescription(){
        return "createactivity <project> <activityname> | Create a new Activity from a given project, can be either a ID or name.";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 2) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return CommandResult.statusMessageResult(msg);
        }
        
        String projectName = args[0];
        String activityName = args[1];
        

        if (manager.getProject(projectName).getActivity(activityName) != null) {
            var result = StatusMessage.error("Activity " + activityName + " already exists in project.");
            return CommandResult.statusMessageResult(result);
            //return new CommandResult<StatusMessage>(ReturnTypes.STATUS_MESSAGE, result);
        }

        var result = manager.createProjectActivity(projectName, activityName);

        return CommandResult.statusMessageResult(result);
        //return new CommandResult<StatusMessage>(ReturnTypes.STRING, "Project " + projectName + " successfully created.");
    }
}
