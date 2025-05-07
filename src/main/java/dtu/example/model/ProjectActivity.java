package dtu.example.model;

import java.util.ArrayList;

public class ProjectActivity implements ActivityInterface {
    private String name;
    private String startDate;
    private String endDate;
    private String activityDescription;
    private float allottedTime;
    private Boolean isActive;
    private ArrayList<TimeRegistration> registrations = new ArrayList();

    public ProjectActivity(String activityName){
        this.name = activityName;
        
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getStartDate() {
        return this.startDate;
    }
    @Override
    public String getEndDate() {
        return this.endDate;
    }
    @Override
    public Boolean isActive() {
        return isActive;
    }

    @Override 
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setActivtyDescription(String newDescription) {
        this.activityDescription = newDescription;
    }

    @Override
    public String getActivityDescription(){
        return this.activityDescription;
    }

    @Override
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    public void setAllottedTime(float allottedTime){
        this.allottedTime = allottedTime;
    }

    public float getAllottedTime(){
        return this.allottedTime;
    }

    public ArrayList<TimeRegistration> getRegistrations(){
        return registrations;
    }

    


}
