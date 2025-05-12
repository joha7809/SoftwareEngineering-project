package dtu.example.Controller.command_base;
import java.util.HashMap;

import dtu.example.Controller.AppController;
import dtu.example.Controller.commands.*;
import dtu.example.View.Levenshtein;

//Johannes
public class CommandRegistry {
    public HashMap<String, CommandInterface<?>> commands = new HashMap<>();

    //Mobprogrammed this
    public CommandRegistry(AppController controller) {
        // Init all commands
        LoginCommand login = new LoginCommand(controller);
        SignUpCommand signup = new SignUpCommand(controller);
        CreateProjectCommand createProject = new CreateProjectCommand(controller);
        ProjectLeadCommand projectLead = new ProjectLeadCommand(controller);
        HelpCommand help = new HelpCommand(this);
        TestKeywordCommand test = new TestKeywordCommand();
        CreateActivityCommand createActivity = new CreateActivityCommand(controller);
        RegisterTimeCommand registerTime = new RegisterTimeCommand(controller);
        CreateFixedActivityCommand createFixedActivity = new CreateFixedActivityCommand(controller);
        EditActivityCommand editActivity = new EditActivityCommand(controller);
        AssignDeveloperCommand assignDeveloper = new AssignDeveloperCommand(controller);
        GetAvailableDevelopersCommand getAvailableUsers = new GetAvailableDevelopersCommand(controller);
        ViewProjectCommand viewProject = new ViewProjectCommand(controller);
        GetReportCommand getReport = new GetReportCommand(controller);
        EditProjectCommand editProject = new EditProjectCommand(controller);

        this.registerCommand(login);
        this.registerCommand(signup);
        this.registerCommand(createProject);
        this.registerCommand(help);
        this.registerCommand(projectLead);
        this.registerCommand(test);
        this.registerCommand(createActivity);
        this.registerCommand(registerTime);
        this.registerCommand(createFixedActivity);
        this.registerCommand(editActivity);
        this.registerCommand(assignDeveloper);
        this.registerCommand(getAvailableUsers);
        this.registerCommand(viewProject);
        this.registerCommand(editProject);
        this.registerCommand(getReport);
    }

    //Johannes
    public void registerCommand(CommandInterface<?> command) {
        this.commands.put(command.getName(), command);
    }

    //Johannes
    public void registerCommand(CommandInterface<?> command, String[] names) {
        for (String name : names) {
            this.commands.put(name, command);
        }
    }

    //Johannes
    // Find the closest matching command using Levenshtein distance
    public String suggestClosestCommand(String input) {
        Levenshtein levenshtein = new Levenshtein();
        String closestCommand = null;
        int minDistance = Integer.MAX_VALUE;

        for (String commandName : commands.keySet()) {
            int distance = levenshtein.distance(input, commandName);
            if (distance < minDistance) {
                minDistance = distance;
                closestCommand = commandName;
            }
        }

        return closestCommand;
    }
    
}
