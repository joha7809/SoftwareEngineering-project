package dtu.example.Controller.commands;

import java.util.Map;
import java.util.function.Function;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.KeywordCommand;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;

//Johannes
public class EditActivityCommand extends KeywordCommand<StatusMessage> {
    private final AppController controller;
    private StringBuilder result;

    public EditActivityCommand(AppController controller) {
        this.controller = controller;
    }

    @Override
    public String getName() {
        return "editactivity";
    }

    @Override
    public String getDescription() {
        return "editactivity <projectname> <activityname> [attribute]=newvalue | Edit attributes of activity. \n"
                + "Attributes: startweek, endweek, description, name. \n"
                + "Example: editactivity project1 activity1 startweek=34 endweek=36 description=New_Description_test";
    }

    @Override
    public CommandResult<StatusMessage> execute(String[] args, Map<String, String> kwargs) {
        if (args.length < 2) {
            return CommandResult.statusMessageResult(StatusMessage.error("Invalid number of arguments. Usage: editactivity <projectname> <activityname> [attribute]=newvalue"));
        }
        String projectName = args[0];
        String activityName = args[1].replace("_", " ");

        Project project = controller.getProject(projectName);
        if (project == null) {
            return CommandResult.statusMessageResult(StatusMessage.error("Project not found."));
        }
        ProjectActivity activity = controller.getProjectActivity(projectName, activityName);
        if (activity == null) {
            return CommandResult.statusMessageResult(StatusMessage.error("Activity not found."));
        }
        this.result = new StringBuilder();
        for (Map.Entry<String, String> entry : kwargs.entrySet()) {
            String attribute = entry.getKey();
            String value = entry.getValue();

            switch (attribute) {
                case "startweek":
                    setStartWeek(activity, value);
                    break;
                case "endweek":
                    setEndWeek(activity, value);
                    break;
                case "description":
                    setDescription(activity, value.replace("_", " "));
                    break;
                case "name":
                    setName(activity, value);
                    break;
                case "time":
                    setAllottedTime(activity, value);
                    break;
                default:
                    return CommandResult.statusMessageResult(StatusMessage.error("Invalid attribute: " + attribute));
            }
        }
        return CommandResult.statusMessageResult(StatusMessage.success("Attributes updated successfully. " + activity.toString()));
    }

    private void setStartWeek(ProjectActivity activity, String startWeek) {
        activity.setStartWeek(startWeek);
        this.result.append("Start week set to ").append(startWeek).append(". ");
    }
    
    private void setEndWeek(ProjectActivity activity, String endWeek) {
        activity.setEndWeek(endWeek);
        this.result.append("End week set to ").append(endWeek).append(". ");
    }
    
    private void setDescription(ProjectActivity activity, String description) {
        activity.setActivtyDescription(description);
        this.result.append("Description set to ").append(description).append(". ");
    }
    
    private void setName(ProjectActivity activity, String name) {
        activity.setName(name);
        this.result.append("Name set to ").append(name).append(". ");
    }

    private void setAllottedTime(ProjectActivity activity, String time) {
        try {
            float allottedTime = Float.parseFloat(time);
            activity.setAllottedTime(allottedTime);
            this.result.append("Allotted time set to ").append(allottedTime).append(". ");
        } catch (NumberFormatException e) {
            this.result.append("Invalid allotted time: ").append(time).append(". ");
        }
    }
    
}
