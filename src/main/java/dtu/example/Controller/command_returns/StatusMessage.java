package dtu.example.Controller.command_returns;


public class StatusMessage{
    public boolean success;
    public String message;

    public StatusMessage(boolean status, String statusMessage){
        this.success = status;
        this.message = statusMessage;
    }
    
}


