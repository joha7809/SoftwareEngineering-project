package dtu.example.Controller.commands;
import java.util.HashMap;

import dtu.example.Controller.AppManager;

public class CommandRegistry {
    public HashMap<String, CommandInterface<?>> commands = new HashMap<>();

    public CommandRegistry(AppManager controller){
        // Init all commands
        Login login = new Login(controller);
        CreateProject createProject = new CreateProject(controller);

        this.registerCommand(login);
        this.registerCommand(createProject);
    }

    public void registerCommand(CommandInterface<?> command){
        this.commands.put(command.getName(), command);
    }

}
