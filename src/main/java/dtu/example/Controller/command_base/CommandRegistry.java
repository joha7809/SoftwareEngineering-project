package dtu.example.Controller.command_base;
import java.util.HashMap;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.commands.CreateActivityCommand;
import dtu.example.Controller.commands.CreateProjectCommand;
import dtu.example.Controller.commands.HelpCommand;
import dtu.example.Controller.commands.LoginCommand;
import dtu.example.Controller.commands.ProjectLeadCommand;
import dtu.example.Controller.commands.SignUpCommand;
import dtu.example.Controller.commands.TestKeywordCommand;

public class CommandRegistry {
    public HashMap<String, CommandInterface<?>> commands = new HashMap<>();

    public CommandRegistry(AppManager manager){
        // Init all commands
        LoginCommand login = new LoginCommand(manager);
        SignUpCommand signup = new SignUpCommand(manager);
        CreateProjectCommand createProject = new CreateProjectCommand(manager);
        ProjectLeadCommand projectLead = new ProjectLeadCommand(manager);
        HelpCommand help = new HelpCommand(this);
        TestKeywordCommand test = new TestKeywordCommand();
        CreateActivityCommand createActivity = new CreateActivityCommand(manager);
        //CreateActivity createActivity = new CreateActivity(controller);

        this.registerCommand(login);
        this.registerCommand(signup);
        this.registerCommand(createProject);
        this.registerCommand(help);
        this.registerCommand(projectLead);
        this.registerCommand(test);
        this.registerCommand(createActivity);
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
