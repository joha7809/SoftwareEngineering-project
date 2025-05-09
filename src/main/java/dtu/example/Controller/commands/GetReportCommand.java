package dtu.example.Controller.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dtu.example.Controller.AppController;
import dtu.example.Controller.command_base.CommandInterface;
import dtu.example.Controller.command_returns.CommandResult;
import dtu.example.Controller.command_returns.ReturnTypes;
import dtu.example.Controller.command_returns.StatusMessage;

public class GetReportCommand implements CommandInterface<String>{
    
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
    
    public CommandResult<String> execute(String[] args) {
        return new CommandResult<String>(ReturnTypes.STRING, controller.getProject(args[0]).getProjectStatus());
    }
}
