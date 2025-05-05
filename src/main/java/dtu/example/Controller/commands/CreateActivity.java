package dtu.example.Controller.commands;

import dtu.example.model.Project;
import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;


public class CreateActivity implements CommandInterface<String> {
    
    private final AppManager controller;

    public CreateActivity(AppManager manager){
        this.controller = manager;
    }

    public String getName(){
        return "ActivityName";
    }

    public String getDecription(){
        return "createactivity <ActivityName> | Create a new Activity";
    }

    public CommandResult<String> execute(String[] args) {
        if (args.length != 1) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: createActivity <ActivityName>");
        }
        String ActivityName = args[0];

        if (controller.getAllActivities(ActivityName) != null) {
            return new CommandResult<>(ReturnTypes.STRING, "Project " +  + " already exists.");
            
        }

        manager.createProject(projectName);
        return new CommandResult<>(ReturnTypes.STRING, "Project " + projectName + " successfully created.");
    }




}
