package dtu.example.Controller.commands;

import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_base.CommandRegistry;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;

public class HelpCommand implements CommandInterface<String>{
    private final CommandRegistry cmds;

    public HelpCommand(CommandRegistry cmds){
        this.cmds = cmds;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Lists all command names and their usage";
    }

    @Override
    public CommandResult<String> execute(String[] args) {
        StringBuilder helpMessage = new StringBuilder("Available commands:\n");

        cmds.commands.forEach((name, command) -> {
            helpMessage.append("- ").append(name).append(": ").append(command.getDescription()).append("\n");
        });

        return new CommandResult<String>(ReturnTypes.STRING, helpMessage.toString());
    }
    
}
