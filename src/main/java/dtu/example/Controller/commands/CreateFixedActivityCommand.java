package dtu.example.Controller.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateFixedActivityCommand {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    private String type;
    private LocalDate myStartTime;
    private LocalDate myEndTime;

    public CreateFixedActivityCommand(String startTime, String endTime, String type){
        myStartTime = LocalDate.parse(startTime, formatter);
        myEndTime = LocalDate.parse(endTime, formatter);
        this.type = type;
    }
}
