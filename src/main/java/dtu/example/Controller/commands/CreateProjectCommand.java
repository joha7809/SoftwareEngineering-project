package dtu.example.Controller.commands;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;

public class CreateProjectCommand implements CommandInterface<StatusMessage> {

    private final AppController controller;

    public CreateProjectCommand(AppController controller) {
        this.controller = controller;
    }
    
    public String getName(){
        return "createproject";
    }

    public String getDescription() {
        return "createproject <projectname> | Create a new project with a given name.";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 1) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return CommandResult.statusMessageResult(msg);
        }
        String projectName = args[0];

        // if (manager.getProject(projectName) != null) {
        //     return new CommandResult<>(ReturnTypes.STRING, "Project " + projectName + " already exists.");
            
        // }

        StatusMessage result = controller.createProject(projectName);
        return CommandResult.statusMessageResult(result);
        //return new CommandResult<>(ReturnTypes.STRING, "Project " + projectName + " successfully created.");
    }
}
