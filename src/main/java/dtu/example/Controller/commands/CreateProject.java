package dtu.example.Controller.commands;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;

public class CreateProject implements CommandInterface<String> {

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

    public CommandResult<String> execute(String[] args) {
        if (args.length != 1) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: createproject <projectname>");
        }
        String projectName = args[0];

        if (manager.getProject(projectName) != null) {
            return new CommandResult<>(ReturnTypes.STRING, "Project " + projectName + " already exists.");
            
        }

        manager.createProject(projectName);
        return new CommandResult<>(ReturnTypes.STRING, "Project " + projectName + " successfully created.");
    }
}
