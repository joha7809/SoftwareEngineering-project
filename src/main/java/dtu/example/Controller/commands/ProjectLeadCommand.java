package dtu.example.Controller.commands;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;

//August
public class ProjectLeadCommand implements CommandInterface<StatusMessage> {

    private final AppController controller;

    public ProjectLeadCommand(AppController controller) {
        this.controller = controller;
    }

    public String getName() {
        return "projectlead";
    }

    public String getDescription() {
        return "projectlead <project> <username> | Set a user as project lead for a given project.";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 2) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return CommandResult.statusMessageResult(msg);
        }

        String projectName = args[0];
        String userName = args[1];

        Project project = controller.getProject(projectName);
        if (project == null) {
            return CommandResult.statusMessageResult(StatusMessage.error("Project does not exist."));
        }

        if (controller.getUser(userName) == null) {
            return CommandResult.statusMessageResult(StatusMessage.error("User does not exist."));
        }

        // if (project.getProjectLead() != null) {
        //     return CommandResult.statusMessageResult(StatusMessage.error("Project already has a project lead."));
        // }

        project.setProjectLead(controller.getUser(userName));
        return CommandResult.statusMessageResult(StatusMessage.success("Project lead set successfully."));
    }
}
