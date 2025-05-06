package dtu.example.Controller.commands;
import java.util.HashMap;

import dtu.example.Controller.AppManager;

public class CommandRegistry {
    public HashMap<String, CommandInterface<?>> commands = new HashMap<>();

    public CommandRegistry(AppManager controller){
        // Init all commands
        LoginCommand login = new LoginCommand(controller);
        SignUpCommand signup = new SignUpCommand(controller);
        CreateProjectCommand createProject = new CreateProjectCommand(controller);
        ProjectLeadCommand projectLead = new ProjectLeadCommand(controller);
        HelpCommand help = new HelpCommand(this);
        //CreateActivity createActivity = new CreateActivity(controller);

        this.registerCommand(login);
        this.registerCommand(signup);
        this.registerCommand(createProject);
        this.registerCommand(help);
        this.registerCommand(projectLead);
        // TODO: uncomment
        //this.registerCommand(createActivity);

    }

    public void registerCommand(CommandInterface<?> command){
        this.commands.put(command.getName(), command);
    }

    public void registerCommand(CommandInterface<?> command, String[] names){
        for (String name : names) {
            this.commands.put(name, command);
        }
    }

}
