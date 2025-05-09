package dtu.example.Controller.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.Project;

public class GetReportCommand implements CommandInterface<StatusMessage>{
    
    private final AppController controller;

    public GetReportCommand(AppController controller){
        this.controller = controller;
    }

    @Override
    public String getName(){
        return "getreport";
    }

    public String getDescription() {
        return "getreport <projectname> | Get the report for the current project.";
    }
    
    public CommandResult<StatusMessage> execute(String[] args) {
        if (args.length != 1) {
            var msg = StatusMessage.error(("Invalid number of arguments. Usage: getreport <projectname>"));
            return new CommandResult<>(ReturnTypes.STATUS_MESSAGE, msg);
        }
        
        String projectName = args[0];
        
        Project project = controller.getProject(projectName);

        if (project == null) {
            return CommandResult.statusMessageResult(StatusMessage.error("Project does not exist."));
        }

        var result = controller.getProjectStatus(projectName);
        
        return new CommandResult<StatusMessage>(ReturnTypes.STATUS_MESSAGE, result);
    }

}
