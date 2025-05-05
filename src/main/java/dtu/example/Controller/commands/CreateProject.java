package dtu.example.Controller.commands;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;

public class CreateProject implements CommandInterface<StatusMessage> {

    private final AppManager manager;

    public CreateProject(AppManager manager) {
        this.manager = manager;
    }
    
    public String getName(){
        return "createproject";
    }

    public String getDescription() {
        return "createproject <projectname> | Create a new project.";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 1) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return CommandResult.statusMessageResult(msg);
            //return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: createproject <projectname>");
        }
        String projectName = args[0];

        // if (manager.getProject(projectName) != null) {
        //     return new CommandResult<>(ReturnTypes.STRING, "Project " + projectName + " already exists.");
            
        // }

        StatusMessage result = manager.createProject(projectName);
        return CommandResult.statusMessageResult(result);
        //return new CommandResult<>(ReturnTypes.STRING, "Project " + projectName + " successfully created.");
    }
}
