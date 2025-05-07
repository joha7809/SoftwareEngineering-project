package dtu.example.Controller.commands;

import java.util.Scanner;
import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.*;
import dtu.example.model.User;

public class RegisterTimeCommand implements CommandInterface<StatusMessage> {

    private final AppController controller;
    private Scanner scanner = new Scanner(System.in); // Scanner for user input

    public RegisterTimeCommand(AppController manager) {
        this.controller = manager;
    }

    public String getName() {
        return "registertime";
    }

    public String getDescription() {
        return "registertime <project> | Used to register time on a project, user is prompted for activity, time";
    }

    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 1) {
            var msg = StatusMessage.error("Invalid number of arguments. Usage: registertime <project>");
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
            var msg = StatusMessage.error("Project not found.");
            return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, msg);
        }

        StatusMessage status = loop(project);
        return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, status);
    }

    private StatusMessage loop(Project project) {
        System.out.println("Registering time for project: " + project.getProjectName());
        StatusMessage result;
        while (true) {
            System.out.println("Enter activity name (or 'exit' to finish): ");
            String activity = scanner.nextLine();
            ProjectActivity projectActivity = controller.getProjectActivity(project.getProjectName(), activity);
            if (projectActivity == null) {
                System.out.println("Activity not found. Please try again.");
                continue;
            }
            if (activity.equalsIgnoreCase("exit")) {
                result = StatusMessage.success("Time registration cancelled.");
                break;
            }
            System.out.println("Enter time spent on activity as decimal: ");
            String time = scanner.nextLine();
            if (time.equalsIgnoreCase("exit")) {
                result = StatusMessage.success("Time registration cancelled.");
                break;
            }
            //controller.registerTime(activity, time);
            System.out.println("Time registered for activity: " + activity + " with time: " + time);
            System.out.println("Do you want to register more time? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                result = StatusMessage.success("Time registered successfully.");
                break;
            } else if (!response.equalsIgnoreCase("yes")) {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        }
        return result;
    }

}
