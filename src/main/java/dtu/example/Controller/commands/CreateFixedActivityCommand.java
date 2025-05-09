package dtu.example.Controller.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.StatusMessage;

public class CreateFixedActivityCommand implements CommandInterface<StatusMessage> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    private String type;
    private LocalDate myStartTime;
    private LocalDate myEndTime;
    private final AppController controller;

    public CreateFixedActivityCommand(AppController controller){
        // myStartTime = LocalDate.parse(startTime, formatter);
        // myEndTime = LocalDate.parse(endTime, formatter);
        // this.type = type;
        this.controller = controller;
    }

      public String getName(){
        return "createfixedactivity";
    }

        public String getDescription() {
        return "createfixedactivity <startdate> <enddate> <type> | Create a new fixed activity of the type.";
    }

        public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 3) {
            var msg = StatusMessage.uneexpectedArguments(this.getDescription());
            return CommandResult.statusMessageResult(msg);
        }

        String startDate = args[0];
        String endDate = args[1];
        String type = args[2];
        



        StatusMessage result = controller.createFixedActivity(startDate, endDate, type);
        return CommandResult.statusMessageResult(result);

    }
}

