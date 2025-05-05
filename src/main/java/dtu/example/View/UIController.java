package dtu.example.View;

import java.util.Scanner;

import dtu.example.Controller.AppManager;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.commands.CommandRegistry;
import dtu.example.Controller.commands.Login;
import dtu.example.model.User;


public class UIController {
    private AppManager appManager;
    private View view = new View();
    private CommandRegistry commands;
    private Scanner sc = new Scanner(System.in);
    private User activeUser;

    public UIController(AppManager manager) {
        this.appManager = manager;
        this.commands = new CommandRegistry(manager);
    }

    public void run() {
        this.UserPage();
    }

    public void UserPage() {
        String s = """
                Welcome! Please login by typing: login {username}
                If not registered use: signup {username}
                
                """;
        System.out.print(s);
        boolean running = true;
        while (running) {
            String input = sc.nextLine();
            Input cmd = readLine(input);

            switch (cmd.name) {
                case "exit":
                    System.out.println("Goodbyessss!");
                    running = false;
                    sc.close();
                    break; // exit the page
                case "login":
                    Login login = new Login(this.appManager);
                    String[] args = cmd.args.split(" ");
                    CommandResult<?> result = login.execute(args);
                    view.render(result);
                    break;

                default:
                    System.out.println("Not found, try again.");
                    break;
            }
        }
    }


    public Input readLine(String input) {
        String[] parts = input.split("\\s+", 2); // split into at most 2 parts
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";
        return new Input(command, args);
    }
}
