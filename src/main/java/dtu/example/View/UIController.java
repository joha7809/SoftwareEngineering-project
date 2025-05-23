package dtu.example.View;

import java.util.Arrays;
import java.util.Scanner;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_base.CommandRegistry;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.commands.LoginCommand;
import dtu.example.model.User;

//Nikolaj
public class UIController {
    private AppController controller;
    private CommandRegistry registry;
    private User activeUser;
    private View view = new View();
    private Scanner sc = new Scanner(System.in);
    

    public UIController(AppController controller) {
        this.controller = controller;
        this.registry = new CommandRegistry(controller);
    }

    public void run() {
        boolean running = true;
        String input;
        Input cmd;
        CommandInterface<?> command;

        //Display welcome message
        view.welcome();

        while (running) {
            input = sc.nextLine();
            cmd = readLine(input);

            if (cmd.name.toLowerCase().equals("exit")){
                sc.close();
                running = false;
                break;
            }

            command = registry.commands.get(cmd.name);

            if (command == null){
                // Suggest the closest matching command
                String suggestion = registry.suggestClosestCommand(cmd.name);
                if (suggestion != null) {
                    System.out.println("Command not found. Did you mean: " + suggestion + "?");
                } else {
                    System.out.println("Command not found.");
                }
                continue;
            }
            CommandResult<?> result;
            try {
                 result = command.execute(cmd.args);
            } catch (IllegalArgumentException e) {
                // TODO: handle exception
                result = new CommandResult<String>(ReturnTypes.STRING, e.getMessage()); 
            }
            
            view.render(result);
        }
    }

    //made by johannes. Splits input into two parts, first being commands, the rest are arguments. Returns Input
    public Input readLine(String input) {
        String[] parts = input.split("\\s+");
        String command = parts[0];
        String[] args = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0];
        return new Input(command, args);
    }
}
