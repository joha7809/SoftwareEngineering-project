package dtu.example.Controller.commands;

import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.AppController;

//Nikolaj
public class ViewProjectCommand implements CommandInterface<String> {
    private final AppController controller;

    public ViewProjectCommand(AppController controller) {
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "view_project";
    }

    @Override
    public String getDescription() {
        return "view_project <project_id> | View details of a specific project.";
    }

    @Override
    public CommandResult<String> execute(String[] args) {
        if (args.length > 1) {
            return new CommandResult<>(ReturnTypes.STRING, "Invalid number of arguments. Usage: " + getDescription());
        }

        if (args.length == 0) {
            // Delegate to AppController to get all projects in a readable format
            String allProjects = controller.getAllProjectsSummary();
            return new CommandResult<>(ReturnTypes.STRING, allProjects);
        }

        String projectId = args[0];
        String projectDetails = controller.getProjectDetails(projectId);
        if (projectDetails == null) {
            return new CommandResult<>(ReturnTypes.STRING, "Project not found.");
        }

        return new CommandResult<>(ReturnTypes.STRING, projectDetails);
    }
}
