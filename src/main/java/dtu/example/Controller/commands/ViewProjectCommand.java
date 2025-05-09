package dtu.example.Controller.commands;

import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.AppController;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;

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
            // List all projects in a limited readable way
            StringBuilder allProjects = new StringBuilder("All projects:\n");
            for (Project project : controller.getAllProjects()) {
                allProjects.append("- Name: ").append(project.getProjectName())
                           .append(", ID: ").append(project.getProjectID())
                           .append("\n");
            }
            return new CommandResult<>(ReturnTypes.STRING, allProjects.toString());
        }

        String projectId = args[0];
        Project project = controller.getProject(projectId);
        if (project == null) {
            return new CommandResult<>(ReturnTypes.STRING, "Project not found.");
        }

        // Elaborate details of the project in a readable way
        StringBuilder projectDetails = new StringBuilder();
        projectDetails.append("Project Details:\n")
                      .append("Name: ").append(project.getProjectName()).append("\n")
                      .append("ID: ").append(project.getProjectID()).append("\n")
                      .append("Description: ").append(project.getDescription() != null ? project.getDescription() : "No description").append("\n")
                      .append("Project Lead: ").append(project.getProjectLead() != null ? project.getProjectLead().getUserID() : "No project lead").append("\n")
                      .append("Activities:\n");

        for (ProjectActivity activity : project.getAllActivities()) {
            projectDetails.append("  - Activity Name: ").append(activity.getName()).append("\n")
                          .append("    Allotted Time: ").append(activity.getAllottedTime()).append(" hours\n")
                          .append("    Total Hours Spent: ").append(activity.getTotalHoursSpent()).append(" hours\n");
        }

        return new CommandResult<>(ReturnTypes.STRING, projectDetails.toString());
    }
}
