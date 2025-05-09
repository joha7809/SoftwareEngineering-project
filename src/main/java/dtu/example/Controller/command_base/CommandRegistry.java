package dtu.example.Controller.command_base;
import java.util.HashMap;

import dtu.example.Controller.AppController;
import dtu.example.Controller.commands.*;


public class CommandRegistry {
    public HashMap<String, CommandInterface<?>> commands = new HashMap<>();

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
        GetReportCommand getReport = new GetReportCommand(controller);

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
        this.registerCommand(getReport);
    }

    public void registerCommand(CommandInterface<?> command) {
        this.commands.put(command.getName(), command);
    }

    public void registerCommand(CommandInterface<?> command, String[] names) {
        for (String name : names) {
            this.commands.put(name, command);
        }
    }
}
