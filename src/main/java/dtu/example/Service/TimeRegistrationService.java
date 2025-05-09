package dtu.example.Service;

import java.io.ObjectInputFilter.Status;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dtu.example.Controller.command_returns.StatusMessage;

import dtu.example.model.AppState;
import dtu.example.model.Project;
import dtu.example.model.ProjectActivity;
import dtu.example.model.TimeRegistration;
import dtu.example.model.User;

public class TimeRegistrationService {
    private final AppState state;

    public TimeRegistrationService() {
        this.state = new AppState();
    }

    public TimeRegistrationService(AppState state) {
        this.state = state;
    }
    
    public StatusMessage createTimeRegistration(Project project, ProjectActivity activity, User user, String workHours){

        if (project == null) {
            return StatusMessage.PROJECT_NOT_FOUND;
        }
        
        if (activity == null) {
            return StatusMessage.ACTIVITY_NOT_FOUND;
        }

        //TODO: Idk if works: 
        float hours = Float.valueOf(workHours);

        System.out.println("CreateTimeRegistration's value hours: " + hours);

        if (hours % 0.5 != 0){
            return new StatusMessage(false, "Error: Hours not in half-hour interval.");
        }

        //TODO: Hvordan skal useren passes?
        System.out.println(hours);

        TimeRegistration registration = new TimeRegistration(hours, user);
        activity.addRegistration(registration);

        return new StatusMessage(true, workHours + "Work hours registered on activity.");
    }


    
}