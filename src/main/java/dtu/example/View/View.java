package dtu.example.View;

import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;

public class View {
    public <T> void render(CommandResult<?> result){
        switch (result.getType()){
            case STRING:
                //Call string print method
                System.out.println((String) result.getValue());
                break;
            case STATUS_MESSAGE:
                StatusMessage message = (StatusMessage) result.getValue();
                System.out.println(message.message);
                break;
        }

    }

    public void welcome(){
        System.out.println(
            """
                Welcome! Please login by typing: login {username}
                If not registered use: signup {username}
            """);
    }
}
