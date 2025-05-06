package dtu.example.Controller.command_returns;


public class StatusMessage{
    public boolean success;
    public String message;

    public StatusMessage(boolean status, String statusMessage){
        this.success = status;
        this.message = statusMessage;
    }

    @Override
    public String toString() {
        
        return "StatusMessage{" +
            "success=" + success +
            ", message='" + message + '\'' +
            '}';
    }
    
    public static StatusMessage uneexpectedArguments(String cmdDesc){
        return new StatusMessage(false, "Error: Unexpected amount of args" + "\n" + cmdDesc);
    }

    public static final StatusMessage PROJECT_NOT_FOUND = new StatusMessage(false, "Error: Project not found");
    public static final StatusMessage USER_NOT_FOUND = new StatusMessage(false, "Error: User not found");
    
}
