package dtu.example.Controller.commands;

import java.util.Scanner;
import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.*;


public class AssignDeveloperCommand implements CommandInterface<StatusMessage> {

    private final AppController controller;
    private Scanner scanner = new Scanner(System.in); // Scanner for user input

    public AssignDeveloperCommand(AppController controller) {
        this.controller = controller;
    }

    public String getName() {
        return "assigndeveloper";
    }

    public String getDescription() {
        return "assigndeveloper <project> <activity> | Used to assign a developer to an activity, user is prompted for userIDs";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 2) {
            var msg = StatusMessage.error("Invalid number of arguments. Usage: assigndeveloper <project> <activity>");
            return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, msg);
        }
        String projectName = args[0];
        User user = controller.getActiveUser();

        if (user == null) {
            var msg = StatusMessage.error("No active user. Please log in first.");
            return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, msg);
        }
        Project project = controller.getProject(projectName);
        if (project == null) {
            var msg = StatusMessage.PROJECT_NOT_FOUND;
            return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, msg);
        }

        // check if activities exist for project.
        if (project.getAllActivities() == null || project.getAllActivities().isEmpty()) {
            var msg = StatusMessage.ACTIVITY_NOT_FOUND;
            return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, msg);
        }

        String activityName = args[1];
        ProjectActivity activity = controller.getProjectActivity(projectName, activityName);
        if (activity == null) {
            var msg = StatusMessage.USER_NOT_FOUND;
            return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, msg);
        }

        StatusMessage status = loop(project, activity);
        return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, status);
    }

    private StatusMessage loop(Project project, ProjectActivity activity) {
        System.out.println("Assigning user to activity " + activity + "...");
        StatusMessage result;
        while (true) {
            System.out.println("Enter userID (or 'exit' to finish): ");
            String developerID = scanner.nextLine();
            User developer = controller.getUser(developerID);
            if (developerID.equalsIgnoreCase("exit")) {
                result = StatusMessage.success("Assigning cancelled.");
                break;
            }
            if (developer == null) {
                System.out.println("User not found. Please try again.");
                continue;
            }
            var msg = controller.addUserToActivity(project.getProjectName(), activity.getName(), developerID);
            if (!msg.success) {
                System.out.println("Error: " + msg.message);
                continue;
            }
            //controller.createTimeRegistration(project.getProjectName(), activity, Float.valueOf(time));
            System.out.println("User: " + developerID + " assigned to project activity " + activity.getName());
        }

        //NOTE: Hvis man udkommenterer nedenstående sætning så crasher den hvis man svarer nej
        //scanner.close();
        return result;
    }

}
