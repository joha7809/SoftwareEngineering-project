package dtu.example.Controller.commands;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;


public class ProjectLeadCommand implements CommandInterface<String> {

    private final AppManager manager;

    public ProjectLeadCommand(AppManager manager) {
        this.manager = manager;
    }
    
    public String getName(){
        return "assignprojectlead";
    }

    public String getDescription() {
        return "assignprojectlead <username> | Assign a project lead.";
    }

    public CommandResult<String> execute(String[] args) {
        if (args.length != 2) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: assign_projectlead <project> <username>");
        }
        String project = args[0];
        String projectLead = args[1];

        if (manager.getProject(project).getProjectLead().getUserID() == projectLead) {
            return new CommandResult<>(ReturnTypes.STRING, "Project lead " + projectLead + " already assigned.");
            
        }

        manager.getProject(project).setProjectLead(manager.getUser(projectLead));;
        return new CommandResult<>(ReturnTypes.STRING, "Project lead " + projectLead + " successfully assigned.");
    }
}
