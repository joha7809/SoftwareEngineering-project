package dtu.example.Controller.command_returns;

//Johannes
public class StatusMessage{
    public boolean success;
    public String message;

    //Johannes
    public StatusMessage(boolean status, String statusMessage){
        this.success = status;
        this.message = statusMessage;
    }

    //Johannes
    @Override
    public String toString() {
        
        return "StatusMessage{" +
            "success=" + success +
            ", message='" + message + '\'' +
            '}';
    }
    
    //Johannes
    public static StatusMessage uneexpectedArguments(String cmdDesc){
        return new StatusMessage(false, "Error: Unexpected amount of args" + "\n" + cmdDesc);
    }

    
    public static final StatusMessage PROJECT_NOT_FOUND = new StatusMessage(false, "Error: Project not found");
    public static final StatusMessage USER_NOT_FOUND = new StatusMessage(false, "Error: User not found");
    public static final StatusMessage ACTIVITY_NOT_FOUND = new StatusMessage(false, "Error: Activity not found");

    //Johannes
    public static StatusMessage error(String message){
        return new StatusMessage(false, message);
    }
    
    //Johannes
    public static StatusMessage success(String message){
        return new StatusMessage(true, message);
    }
}
