package dtu.example.Controller.commands;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.model.Project;
import dtu.example.model.User;


public class ProjectLeadCommand implements CommandInterface<String> {

    private final AppManager manager;

    public ProjectLeadCommand(AppManager manager) {
        this.manager = manager;
    }
    
    public String getName(){
        return "assignprojectlead";
    }

    public String getDescription() {
        return "assignprojectlead <projectID or projectName> <username> | Assign a project lead.";
    }

    public CommandResult<String> execute(String[] args) {
        if (args.length != 2) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: assign_projectlead <project> <username>");
        }
        String projectInput = args[0];
        String projectLead = args[1];
        Project project;
        User user;


        boolean searchByID = true;

        // Determine if project provided is of type 5xint or a project name
        if (!projectInput.matches("\\d{5}")) {
            searchByID = false;
        }

        if (searchByID){
            project = manager.getProjectById(projectInput);
        } else {
            project = manager.getProjectByName(projectInput);
        }

        user = manager.getUser(projectLead);

        if(project == null && user == null) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid argument, neither user nor project does exist.");
        }

        if(project == null){
            return new CommandResult<>(ReturnTypes.STRING, "Invalid argument, project does not exist.");
        }

        if(user == null){
            return new CommandResult<>(ReturnTypes.STRING, "Invalid argument, user does not exist.");
        }

  
        
        if(project.getProjectLead() != null){
            if (project.getProjectLead().getUserID().equals(projectLead)) {
                return new CommandResult<>(ReturnTypes.STRING, "Project lead " + projectLead + " already assigned.");
            }   
        }
        
        project.setProjectLead(manager.getUser(projectLead));
        return new CommandResult<>(ReturnTypes.STRING, "Project lead " + projectLead + " successfully assigned.");
    }
}
