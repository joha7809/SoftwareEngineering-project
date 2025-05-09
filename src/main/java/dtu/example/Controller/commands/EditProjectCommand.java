package dtu.example.Controller.commands;

import java.util.Map;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.KeywordCommand;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;

public class EditProjectCommand extends KeywordCommand<StatusMessage> {
    private final AppController controller;
    private StringBuilder result;

    public EditProjectCommand(AppController controller) {
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "editproject";
    }

    @Override
    public String getDescription() {
        return "editproject <projectname> [attribute]=newvalue | Edit attributes of a project. \n"
                + "Attributes: name, description. \n"
                + "Example: editproject project1 name=New_Project_Name description=New_Description";
    }

    @Override
    public CommandResult<StatusMessage> execute(String[] args, Map<String, String> kwargs) {
        if (args.length < 1) {
            return CommandResult.statusMessageResult(StatusMessage.error("Invalid number of arguments. Usage: editproject <projectname> [attribute]=newvalue"));
        }
        String projectName = args[0];

        Project project = controller.getProject(projectName);
        if (project == null) {
            return CommandResult.statusMessageResult(StatusMessage.error("Project not found."));
        }

        this.result = new StringBuilder();
        for (Map.Entry<String, String> entry : kwargs.entrySet()) {
            String attribute = entry.getKey();
            String value = entry.getValue();

            switch (attribute) {
                case "name":
                    setName(project, value.replace("_", " "));
                    break;
                case "description":
                    setDescription(project, value.replace("_", " "));
                    break;
                default:
                    return CommandResult.statusMessageResult(StatusMessage.error("Invalid attribute: " + attribute));
            }
        }
        return CommandResult.statusMessageResult(StatusMessage.success("Attributes updated successfully."));
    }

    private void setName(Project project, String name) {
        project.setName(name);
        this.result.append("Name set to ").append(name).append(". ");
    }

    private void setDescription(Project project, String description) {
        project.setDescription(description);
        this.result.append("Description set to ").append(description).append(". ");
    }
}

