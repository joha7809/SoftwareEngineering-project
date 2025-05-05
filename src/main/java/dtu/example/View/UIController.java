package dtu.example.View;

import java.util.Arrays;
import java.util.Scanner;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.commands.CommandInterface;
import dtu.example.Controller.commands.CommandRegistry;
import dtu.example.Controller.commands.Login;
import dtu.example.model.User;


public class UIController {
    private AppManager appManager;
    private View view = new View();
    private CommandRegistry registry;
    private Scanner sc = new Scanner(System.in);
    private User activeUser;

    public UIController(AppManager manager) {
        this.appManager = manager;
        this.registry = new CommandRegistry(manager);
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
                // TODO: implement error 
                System.out.println("COMMAND NOT FOUND");
                continue;
            }

            CommandResult<?> result = command.execute(cmd.args);
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
