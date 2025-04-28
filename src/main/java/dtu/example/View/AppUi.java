package dtu.example.View;

import java.util.Scanner;

import dtu.example.Controller.AppManager;
import dtu.example.model.StatusMessage;
import dtu.example.model.User;
import dtu.example.View.Command;

public class AppUi {
    private AppManager appManager;
    private Scanner sc = new Scanner(System.in);
    private User activeUser;

    public AppUi(AppManager manager){
        this.appManager = manager;
    }

    public void run(){
        this.UserPage();

    }

    public void UserPage(){
        String s = """
            Welcome! Please login by typing: login {username}
            If not registered use: signup {username}
            """;
        System.out.print(s);
        boolean running = true;
        while (running){
            String input = sc.nextLine();
            Command cmd = readLine(input);
            
            switch (cmd.name) {
                case "exit":
                    System.out.println("Goodbye!");
                    running = false;
                    return; // exit the page
                case "login":
                    User user = appManager.getUser(cmd.args);
                    if (user == null){
                        System.out.println("User not found try again!");
                        break;
                    }

                    this.appManager.setActiveUser(user);
                    System.out.println("You have now logged in as: " + user.getUserID());
                    break;
                
                case "signup":
                    //Ensure args only contains one argument.
                    String[] args = cmd.args.split("\\s+");
                    if (args.length != 1 || cmd.args == ""){
                        System.out.println("Unexpected amount of arguments, try again.");
                        break;
                    }

                    StatusMessage result = appManager.createUser(cmd.args);

                    if (result.success == false){
                        //UserID already exists
                        System.out.println(result.message);
                        break;
                    }
                    //User has been created
                    System.out.println(result.message);
                    break;


                    

                
                default:
                    System.out.println("Not found, try again.");
                    break;
            }
        }
    }

    public Command readLine(String input){
        String[] parts = input.split("\\s+", 2); // split into at most 2 parts
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";
        return new Command(command, args);
    }
}
