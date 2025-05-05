package dtu.example.Controller.commands;
import java.util.HashMap;

import dtu.example.Controller.AppManager;

public class CommandRegistry {
    public HashMap<String, CommandInterface<?>> commands = new HashMap<>();

    public CommandRegistry(AppManager controller){
        // Init all commands
        Login login = new Login(controller);
        CreateProject createProject = new CreateProject(controller);
        //ProjectLeadCommand projectLead = new ProjectLeadCommand(controller);
        //CreateActivity createActivity = new CreateActivity(controller);

        this.registerCommand(login);
        this.registerCommand(createProject);
        // TODO: uncomment
        //this.registerCommand(projectLead);
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
