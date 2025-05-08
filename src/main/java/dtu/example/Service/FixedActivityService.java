package dtu.example.Service;

import dtu.example.Controller.command_returns.StatusMessage;
import dtu.example.model.AppState;
import dtu.example.model.Project;
import dtu.example.model.FixedActivity;

import java.io.ObjectInputFilter.Status;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class FixedActivityService {
    private final AppState state;

    public FixedActivityService(AppState state) {
        this.state = state;
    }
    
    //Adam wrote this. Helps determine whether a date is of the date format
    public StatusMessage createFixedActivity(String startDate, String endDate, String typeCause){

        //TODO: Maybe arraylist of possible fixed activity types


        //String createFixedActivityCommand = "createFixedActivity";
        String createFixedActivityType = typeCause;
        //boolean commandPass = command.equalsIgnoreCase(createFixedActivityCommand);
        boolean startDatePass = DateHelper.isDateFormat(startDate);
        boolean endDatePass = DateHelper.isDateFormat(endDate);
        //boolean typeCausePass = typeCause.equalsIgnoreCase(createFixedActivityType);

        if (!startDatePass && !endDatePass){
            return StatusMessage.error("Error: Neither dates are valid dates!");
        }

        if(!startDatePass){
            return StatusMessage.error("Error: Start date is not a valid date!");
        }
        
        if(!endDatePass){
            return StatusMessage.error("Error: End date is not a valid date!");
        }

        getFixedActivities().add(new FixedActivity(startDate, endDate, typeCause));
        return new StatusMessage(true, "Arguments accepted");
        }

    public ArrayList<FixedActivity> getFixedActivities() {
        return state.getFixedActivities();
    }

    //Adam wrote this. Determines whether a fixed activity with given attributes exists
    public StatusMessage findFixedActivity(String type, String timeStart, String timeEnd){
        for (int i = 0; i < getFixedActivities().size(); i++){
            //if(getFixedActivities().get(i).getStartDate().equalsIgnoreCase(name)){//gets problematic once there are multiple fixed activities named the same e. g. sick
            if(getFixedActivities().get(i).getName().equalsIgnoreCase(type) && getFixedActivities().get(i).getStartDate().equalsIgnoreCase(timeStart) && getFixedActivities().get(i).getEndDate().equalsIgnoreCase(timeEnd)){//gets problematic once there are multiple fixed activities named the same e. g. sick
                    
                return new StatusMessage(true, "Fixed Activity " + getFixedActivities().get(i).getName() + " Exists.");
            }
        }
        return new StatusMessage(false, "Could not find fixed activity");
    }


    


    }